package com.wandoujia.hackday.apppath.service;

import com.wandoujia.hackday.apppath.constant.Constant;
import com.wandoujia.hackday.apppath.dao.AppTimelineQueryDao;
import com.wandoujia.hackday.apppath.model.AppPointModel;
import com.wandoujia.hackday.apppath.utils.DateTimeUtil;
import com.wandoujia.hackday.apppath.utils.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Jerry on 2/6/14.
 */
@Service
public class AppTimelineQueryServiceImpl implements AppTimelineQueryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppTimelineQueryDao appTimelineQueryDao;

    @Autowired
    private HttpUtils httpUtils;

    public static final String FREE_TRAFFIC = "";

    private final static String appsUrl = "http://apps.wandoujia.com/api/v1/apps";

    @Override
    public List<AppPointModel> listAppPoint(String udid, Long uid, Date date) {
        List<AppPointModel> list = new LinkedList<AppPointModel>();
        Date endDate = DateTimeUtil.getDateBeforeOrAfter(date, 1);
        endDate = DateUtils.truncate(endDate, Calendar.DAY_OF_MONTH);

        List<AppPointModel> trafficList = appTimelineQueryDao.listFreeTraffics(uid, date, endDate);
        if (trafficList != null) {
            mergeAppsData(trafficList);
            this.processAppPointData(trafficList, AppPointModel.Type.FREETRAFFIC);
            list.addAll(trafficList);
        }
        List<AppPointModel> amountList = appTimelineQueryDao.listFreeAmounts(uid, date, endDate);
        if (amountList != null) {
            this.processAppPointData(amountList, AppPointModel.Type.FREEAMOUNT);
            list.addAll(amountList);
        }
        List<AppPointModel> payOrderList = appTimelineQueryDao.listPayOrders(uid, date, endDate);
        if (payOrderList != null) {
            this.processAppPointData(payOrderList, AppPointModel.Type.PAYORDER);
            mergeAppsData(payOrderList);
            list.addAll(payOrderList);
        }
        List<AppPointModel> pointList = this.listAppPointsByUdid(udid, uid, date);
        if (pointList != null) {
            list.addAll(pointList);
        }
        Collections.sort(list, new Comparator<AppPointModel>() {
            @Override
            public int compare(AppPointModel o1, AppPointModel o2) {
                return o1.getStart().compareTo(o2.getStart());
            }
        });
        return list;

    }

    @Override
    public HashMap<String, Object> sumPointsByWeek(String udid, Long uid, Date date) {
        Date start = DateUtils.truncate(DateTimeUtil.getDateBeforeOrAfter(date, -7), Calendar.DAY_OF_MONTH);
        Date end = DateUtils.truncate(DateTimeUtil.getDateBeforeOrAfter(date, 1), Calendar.DAY_OF_MONTH);
        List<AppPointModel> list = appTimelineQueryDao.listAppPoints(udid, uid, start, end);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list == null || list.isEmpty()) {
            return map;
        }
        Long duration = 0L;
        HashMap<String, AppData> tempMap = new HashMap<String, AppData>();
        this.mergeAppsData(list);
        int photoCnts = 0;
        for (AppPointModel model: list) {
            String pn = model.getPackage_name();
            if (model.getDuration() != null) {
                AppData appData = tempMap.get(pn);
                if (appData == null) {
                    appData = new AppData();
                }
                appData.setPn(pn);
                // 总时长
                duration += model.getDuration();
                // 分 app 统计时长

                Long pnDuration = model.getDuration();
                appData.setDuration(appData.getDuration() + pnDuration);

                // 使用次数
                appData.setCnt(appData.getCnt() + 1l);
                appData.setIcon(model.getIcon());
                tempMap.put(pn, appData);
            }
            // 解析是否有拍照
            photoCnts += this.processPhotos(model);
        }

        List<AppData> dataList = new LinkedList<AppData>(tempMap.values());
        // 时长最久
        Collections.sort(dataList, new Comparator<AppData>() {
            @Override
            public int compare(AppData o1, AppData o2) {
                return o2.getDuration().compareTo(o1.getDuration());
            }
        });
        AppData appData = dataList.get(0);
        appData.setDuration(appData.getDuration() / 7);
        map.put("duration", duration / 7);
        map.put("appduration", appData);
        // 次数最多
        Collections.sort(dataList, new Comparator<AppData>() {
            @Override
            public int compare(AppData o1, AppData o2) {
                return o2.getCnt().compareTo(o1.getCnt());
            }
        });
        List<AppData> appDataList = null;
        if (dataList != null && dataList.size() > 5) {
            appDataList = dataList.subList(0, 5);
        } else {
            appDataList = dataList;
        }

        map.put("appList", appDataList);
        map.put("photos", photoCnts / 7);
        return map;
    }

    private int processPhotos(AppPointModel point) {
        if (point == null) {
            return 0;
        }
        if (StringUtils.isBlank(point.getData())) {
            return 0;
        }
        JSONObject jsonObject = JSONObject.fromObject(point.getData());
        int cnt = jsonObject.optInt("photosIncrease", 0);
        return cnt;
    }

    class AppData implements Serializable {

        private static final long serialVersionUID = -7557773884883699298L;

        public AppData() {
            this.pn = "";
            this.cnt = 0l;
            this.duration = 0l;
            this.icon = "";
        }

        private String pn;

        private Long cnt;

        private Long duration;

        private String icon;

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public Long getCnt() {
            return cnt;
        }

        public void setCnt(Long cnt) {
            this.cnt = cnt;
        }

        public Long getDuration() {
            return duration;
        }

        public void setDuration(Long duration) {
            this.duration = duration;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    private void processAppPointData(List<AppPointModel> list, AppPointModel.Type type) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (AppPointModel model: list) {
            processComments(model, type);
        }
    }

    private void processComments(AppPointModel appPointModel, AppPointModel.Type type) {
        appPointModel.setType(type);
        switch (type) {
            case FREETRAFFIC:
                appPointModel.setComment(String.format("获得 %s M流量", appPointModel.getTraffic()));
                break;
            case FREEAMOUNT:
                appPointModel.setComment(String.format("兑换 %s 元话费", appPointModel.getAmount()));
                break;
            case PAYORDER:
                appPointModel.setComment(String.format("支付 %s 个豌豆币", appPointModel.getAmount()));
                break;
            default:
                break;
        }
    }

    private List<AppPointModel> listAppPointsByUdid(String udid, Long uid, Date date) {
        Date endDate = DateTimeUtil.getDateBeforeOrAfter(date, 1);
        endDate = DateUtils.truncate(endDate, Calendar.DAY_OF_MONTH);

        List<AppPointModel> list = appTimelineQueryDao.listAppPoints(udid, uid, date, endDate);
        if (list == null || list.isEmpty()) {
            return null;
        }
        mergeAppsData(list);
        return list;
    }

    private void mergeAppsData(List<AppPointModel> list) {
        Set pnSet = new HashSet();
        for (AppPointModel model: list) {
            pnSet.add(model.getPackage_name());
        }
        JSONArray jsonArray = getAppsData(pnSet);
        HashMap<String, JSONObject> appsMap = this.convertJsonArrayToMap(jsonArray);
        for (AppPointModel model: list) {
            JSONObject jsonObject = appsMap.get(model.getPackage_name());
            if (jsonObject == null) {
                continue;
            }
            model.setIcon(jsonObject.getJSONObject("icons").optString("px78"));
        }
    }

    private JSONArray getAppsData(Set pnSet) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Constant.APPS_PARAMS_OPT_FIELDS, Constant.OPT_FIELDS);
        return listApps(new ArrayList<String>(pnSet), params);
    }

    public JSONArray listApps(List<String> packageNameList, Map<String, String> params) {
        Map<String, String> parameters = new HashMap<String, String>();
        if (packageNameList == null || packageNameList.isEmpty()) {
            return null;
        }
        parameters.put(Constant.APPS_PARAMS_FROM, "pmt");
        if (params != null && !params.isEmpty()) {
            parameters.putAll(params);
        }
        String pns = "";
        int count = 0;
        JSONArray jsonArray = new JSONArray();
        for (String pn: packageNameList) {
            pns += Constant.COMMA + pn;
            count++;
            if (count % 50 == 0) {
                parameters.put(Constant.APPS_PARAMS_PNS, pns);
                jsonArray.addAll(this.queryAppsDetail(parameters));
                pns = "";
                continue;
            }
        }
        if (StringUtils.isNotBlank(pns)) {
            parameters.put(Constant.APPS_PARAMS_PNS, pns);
            jsonArray.addAll(this.queryAppsDetail(parameters));
        }

        return jsonArray;
    }

    private JSONArray queryAppsDetail(Map<String, String> parameters) {
        String retStr = httpUtils.getUrl(appsUrl, parameters, HttpUtils.ENCODING_UTF8, 10000);
        if (StringUtils.isBlank(retStr)) {
            return new JSONArray();
        }
        JSONArray jsonArray = JSONArray.fromObject(retStr);

        return jsonArray;
    }

    private HashMap<String, JSONObject> convertJsonArrayToMap(JSONArray jsonArray) {
        HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
        if (jsonArray == null || jsonArray.isEmpty()) {
            return map;
        }
        Iterator<JSONObject> it = jsonArray.iterator();
        while (it.hasNext()) {
            JSONObject jsonObject = it.next();
            map.put(jsonObject.optString("packageName"), jsonObject);
        }

        return map;
    }
}
