package com.wandoujia.hackday.apppath.controller;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> createSimpleResponse(int errorCode, String message) {
        Map<String, Object> r = new HashMap<String, Object>(2);
        r.put("error", errorCode);
        r.put("msg", message);
        return r;
    }

    public static Map<String, Object> createEmptyResponse() {
        return new HashMap<String, Object>();
    }
}
