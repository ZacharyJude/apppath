package com.wandoujia.hackday.apppath.controller;

import java.util.*;

import com.wandoujia.hackday.apppath.service.PointService;
import com.wandoujia.hackday.apppath.protocol.OperationStatus;

import com.wandoujia.commons.httputils.utils.HttpUtils;
import com.wandoujia.commons.account.model.WdjUserDetails;
import com.wandoujia.commons.account.service.WdjUserDetailsService;
import com.wandoujia.commons.lang.SerializationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;

@Controller
@RequestMapping(value = "/apppath/point")
public class PointController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WdjUserDetailsService wdjUserDetailService;

    @Autowired
    private PointService pointService;

    @RequestMapping(method = RequestMethod.POST, value = "/feed", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String feedAction(
        @CookieValue(value = "wdj_auth", required = false) String wdj_auth,
        @RequestParam(value = "content", required = true) String contentJsonDataStr,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        OperationStatus opStatus = OperationStatus.UNKNOWN;

        String remoteAddr = HttpUtils.getRemoteIp(request);
        Long uid = null;
        if(null != wdj_auth) {
            WdjUserDetails wdjUserDetails = wdjUserDetailService.loadUserByAuthAndAddress(wdj_auth, remoteAddr);
            if(null == wdjUserDetails) {
                opStatus = OperationStatus.INVALID_USER;
                return SerializationUtils.toJson(ResponseUtil.createSimpleResponse(opStatus.getCode(), opStatus.getMsg()));
            }
            uid = wdjUserDetails.getUid();
        }

        Map<String, Object> contentJsonData = SerializationUtils.fromJson(contentJsonDataStr, Map.class);
        
        String udid = (String)contentJsonData.get("udid");
        List<Map<String, Object>> pointDatas = (List<Map<String, Object>>)contentJsonData.get("points");

        PointService.AddUdidPointArgModel arg = new PointService.AddUdidPointArgModel(uid, udid);
        for(Map<String, Object> pd : pointDatas) {
            arg.addPoint(
                (String)pd.get("packageName"),
                (String)pd.get("data"),
                new Date(((Number)pd.get("start")).longValue()),
                Long.valueOf(((Number)pd.get("duration")).longValue()),
                Long.valueOf(((Number)pd.get("longitude")).longValue()),
                Long.valueOf(((Number)pd.get("latitude")).longValue())
            );
        }

        opStatus = pointService.addUdidPoints(arg);

        return SerializationUtils.toJson(ResponseUtil.createSimpleResponse(opStatus.getCode(), opStatus.getMsg()));
    }
}
