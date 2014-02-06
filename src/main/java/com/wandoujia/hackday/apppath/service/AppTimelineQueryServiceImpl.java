package com.wandoujia.hackday.apppath.service;

import com.wandoujia.hackday.apppath.constant.Constant;
import com.wandoujia.hackday.apppath.dao.AppTimelineQueryDao;
import com.wandoujia.hackday.apppath.model.AppPointModel;
import com.wandoujia.hackday.apppath.utils.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Jerry on 2/6/14.
 */
@Service
public class AppTimelineQueryServiceImpl implements AppTimelineQueryService {

    @Autowired
    private AppTimelineQueryDao appTimelineQueryDao;

    @Autowired
    private HttpUtils httpUtils;

    private final static String appsUrl = "http://apps.wandoujia.com/api/v1/apps";

    @Override
    public List<AppPointModel> listAppPoint(String udid, Long uid, Date date) {

        List<AppPointModel> list = appTimelineQueryDao.listAppPoint(udid, uid, date);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Set pnSet = new HashSet();
        for (AppPointModel model: list) {
            pnSet.add(model.getPackage_name());
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Constant.APPS_PARAMS_OPT_FIELDS, Constant.OPT_FIELDS);
        JSONArray jsonArray = listApps(new ArrayList<String>(pnSet), params);
        HashMap<String, JSONObject> appsMap = this.convertJsonArrayToMap(jsonArray);
        for (AppPointModel model: list) {
            JSONObject jsonObject = appsMap.get(model.getPackage_name());
            if(jsonObject == null){
                continue;
            }
            model.setIcon(jsonObject.getJSONObject("icons").optString("px78"));
        }
        return list;
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
