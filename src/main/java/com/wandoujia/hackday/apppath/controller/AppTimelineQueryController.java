package com.wandoujia.hackday.apppath.controller;

import com.wandoujia.hackday.apppath.service.AppTimelineQueryService;
import com.wandoujia.hackday.apppath.utils.DateTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Date;

/**
 * Created by Jerry on 2/6/14.
 */
@Controller
public class AppTimelineQueryController {

    @Autowired
    private AppTimelineQueryService appTimelineQueryService;

    @RequestMapping(value = "/apppath/point/query")
    public @ResponseBody
    Object listAppPoints(Long uid, String udid, String date, ServletRequest request) {
        if(StringUtils.isBlank(date)){
            return null;
        }
        Date inputDate = DateTimeUtil.getFormatDate(date);
        return appTimelineQueryService.listAppPoint(udid, uid, inputDate);
    }
}
