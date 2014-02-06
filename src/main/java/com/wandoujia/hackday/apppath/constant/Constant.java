/**
 * @(#)Constant.java, 2012-2-24. Copyright (c) 2011, Wandou Labs and/or its
 *                    affiliates. All rights reserved. WANDOU LABS
 *                    PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wandoujia.hackday.apppath.constant;

/**
 * 常量类
 * 
 * @author jerry
 */
public class Constant {

    public static final String PERIOD = ".";

    public static final String PERIOD_REGEX = "\\.";

    public static final String SLASH = "/";

    public static final String COMMA = ",";

    public static final String TILDE = "~";

    // 精确的小数位数
    public final static int CTR_POS = 10;

    public static final String UNDERLINE = "_";

    public static final String KEY_BIDID = "BIDID";

    public static final String KEY_APPID = "APPID";

    public static final String KEY_CATEGORY = "CATEGORY";

    public static final String KEY_UDID = "UDID";

    public static final String KEY_UID = "UID";

    // 导航的类别信息集合key
    public static final String KEY_PORTAL_CATEGORY_LIST = "PORTAL_CATEGORY_LIST";

    // 导航下载数量key（因为导航的下载量每天更新一次，所以缓存起来）
    public static final String KEY_PORTAL_DLCOUNT_LIST = "PORTAL_DLCOUNT_LIST";

    public static final String KEY_SEARCH_WORD = "SEARCH_WORD";

    public static final double KEY_SEARCH_RELEVANCE_RAIO = 1;

    public static final String KEY_DOWNLOAD_APP = "DOWNLOAD_APP";

    public static final String KEY_APP_GIFT = "APP_GIFT";

    public static final String KEY_WDJ_GAME = "WDJ_GAME";

    public static final String KEY_WDJ_ALL_GAME_SET = "WDJ_ALL_GAME_SET";

    public static final String KEY_WDJ_ALL_GAME_JSONARRAY = "WDJ_ALL_GAME_JSONARRAY";

    public static final String PAGE_INDEX = "INDEX";

    public static final String PAGE_MOBILE = "MOBILE";

    public static final String AREA_MOBILE_RISE = "RISE";

    public static final String AREA_MOBILE_RECOMMEND = "RECOMMEND";

    public static final String AREA_ALL = "all";

    public static final String AREA_ALLAPPS = "allapps";

    public static final String AREA_ALLGAMES = "allgames";

    public static final String PAGE_PARTNER = "partner";

    public static final String IMG_TYPE_END = ".gif";

    public static final String URL_PARAMS_BIDID = "?bid=";

    public static final String URL_PARAMS_TOKEN = "token=";

    public static final String URL_PARAMS_UDID = "&udid=";

    public static final String URL_PARAMS_SOURCE = "&source=";

    public static final String URL_PARAMS_POS = "&pos=";

    public static final String URL_PARAMS_QUERY = "&query=";

    public static final String URL_PARAMS_PCID = "&pcid=";

    public static final String PN_ALL = "PN_ALL";

    public static final String GIFT_ALL = "GIFT_ALL";

    // 不截断
    public static final String TRUNCFLAG_NOTRUNC = "notrunc";

    // 阶段一位
    public static final String TRUNCFLAG_TRUNCONE = "truncone";

    public static final String CLEARDATA = "clearData";

    public static final String SUCCESS = "success";

    public static final int ROBOT_PAGE_SIZE = 20;

    public static final String DEFAULT_CTR_SEARCH = "ctr.search.default";

    /**
     * 
     */
    public static final String OPTIMIZE_STATUS_SUGGESTPRICE = "SUGGESTPRICE";

    /**
     * 
     */
    public static final String OPTIMIZE_STATUS_OFFLINE = "OFFLINE";

    /**
     * 
     */
    public static final String OPTIMIZE_STATUS_NOSUGGESTION = "NOSUGGESTION";

    public static final String CDKEY = "CDKEY";

    public static final String SEARCH_WORD_SEPARATOR = ",";

    public static final String SEARCH_WORD_LEFT_DELIMITER = "[";

    public static final String SEARCH_WORD_RIGHT_DELIMITER = "]";

    public static final String SEARCH_WORD_PREFIX = "+";

    public static final String TOP_GIFT = "TOP_GIFT";

    public static enum OPTIMIZE_STATUS {
        SUGGESTPRICE, OFFLINE, NOSUGGESTION
    }

    public static enum BidType {
        NAV, SEARCH
    }

    /* ----基于UdidProfile的游戏推荐Begin---- */
    // Zip压缩使用
    public static final int BUF_SIZE = 1024;

    // 游戏App推荐所使用的Memcache的key前缀标记
    public static final String MEMCACHE_KEY_PREFIX_UDID = "UDID#";

    // 用户按游戏推荐分类的具体类型
    public static enum UserType {
        A, B, C, D, E, F, G;
    };

    // 游戏App推荐策略类型
    public static enum RecommendStrategyType {
        NoStragey, Interest, Rotation, Rule;
    };

    // 游戏App轮询推荐策略默认推荐游戏App个数
    public static final int DefaultGameAppSuggestNum = 1;

    /* ----基于UdidProfile的游戏推荐End---- */

    public static final String SENDSMS_SIGN = "【豌豆荚】";

    public static enum AppType {
        GAME, APP, NOTSURE;
    }

    public static final String APPS_PARAMS_OPT_FIELDS = "opt_fields";

    public static final String APPS_PARAMS_FROM = "from";

    public static final String APPS_PARAMS_PNS = "pns";

    public static final String JSON_KEY_PRICE = "price";

    public static final String JSON_KEY_BIDID = "bidId";

    public static final String ADNETWORK_V1 = "ADNETWORK_V1";

    public static final String OPT_FIELDS = "screenshots.small,apks.compatible,apks.incompatibleDetail,title,packageName,ad,icons.px78,installedCountStr,apks.downloadUrl.url,apks.bytes,apks.verified,apks.versionName,apks.versionCode,category.alias,category.name,detailParam,imprUrl,apks.md5,editorComment,description,apks.paidType,likesRate,apks.superior,extensionPacks.*,tags.*";

}
