/**
 * @(#)HttpUtils.java, 2012-4-17. Copyright (c) 2011, Wandou Labs and/or its
 *                     affiliates. All rights reserved. WANDOU LABS
 *                     PROPRIETARY/CONFIDENTIAL. Use is subject to license
 *                     terms.
 */
package com.wandoujia.hackday.apppath.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jerryjiang
 */
@Component("httpUtils")
public class HttpUtils {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public HttpUtils() {
        if (httpClient == null) {
            // 高并发多线程环境下
            // 单一的httpclient存在线程安全问题，常见的是并发两个请求，一个请求执行完了就关闭http连接，而另外一个还没执行完，报错
            MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
            httpClient = new HttpClient(connectionManager);
        }
    }

    HttpClient httpClient;

    public final static String ENCODING_UTF8 = "UTF-8";

    public final static Integer MILLISECOND = 3000;

    public String postUrl(String url, Map<String, String> parameters, String encoding, int millisecond) {
        PostMethod method = new PostMethod(url);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(millisecond);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(millisecond);
        // add by aidi -start 增大httpclient链接池大小
        httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(512);
        httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(1024);
        // add by aidi -end

        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        String result = "";
        try {
            // 设置参数
            if (parameters != null) {
                for (Map.Entry<String, String> entry: parameters.entrySet()) {
                    method.setParameter(entry.getKey(), entry.getValue());
                }
            }

            // 访问url失败,返回空结果
            if (httpClient.executeMethod(method) != HttpStatus.SC_OK) {
                return result;
            }

            // 将返回的http响应传入解析方法进行解析
            result = method.getResponseBodyAsString();
            return result;
        } catch (Exception ex) {
            logger.error("访问url失败[url=" + url + "]", ex);
            throw new RuntimeException("访问url失败[url=" + url + "]", ex);
        } finally {
            method.releaseConnection();
        }
    }

    public String postUrl(String url, Map<String, String> parameters) {
        return postUrl(url, parameters, ENCODING_UTF8, MILLISECOND);
    }

    public String getUrl(String url, Map<String, String> parameters, String encoding, int millisecond) {

        String result = "";
        GetMethod method = null;
        try {
            // 设置参数
            if (parameters != null) {
                StringBuilder params = new StringBuilder();
                boolean isFirst = true;
                for (Map.Entry<String, String> entry: parameters.entrySet()) {
                    if (isFirst) {
                        params.append("?");
                    } else {
                        params.append("&");
                    }
                    params.append(entry.getKey() + "=" + entry.getValue());
                    isFirst = false;
                }
                url = url + params;
            }

            method = new GetMethod(url);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(millisecond);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(millisecond);
            // add by aidi -start 增大httpclient链接池大小
            httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(512);
            httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(1024);
            // add by aidi -end
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
            // 访问url失败,返回空结果
            if (httpClient.executeMethod(method) != HttpStatus.SC_OK) {
                return result;
            }

            // 将返回的http响应传入解析方法进行解析
            result = method.getResponseBodyAsString();
            return result;
        } catch (Exception ex) {
            logger.error("访问url失败[url=" + url + "]", ex);
            throw new RuntimeException("访问url失败[url=" + url + "]", ex);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
    }

    public String getUrl(String url, Map<String, String> parameters) {
        return getUrl(url, parameters, ENCODING_UTF8, MILLISECOND);
    }

}
