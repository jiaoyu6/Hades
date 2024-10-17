package com.haders.util;

import com.alibaba.excel.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class to generate response data
 *
 * @author Peikaiqiang
 */
@Log4j2
public class ResponseData {

    public static final String RES_HEADER_CODE = "code";
    public static final String RES_HEADER_MESSAGE = "message";
    public static final String RESULT = "result";
    public static final String DURATION = "duration";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Gson gson = null;

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    // the map to store response data
    private Map<Object, Object> data = new HashMap<Object, Object>();
    

    // parse the map to json format string
    public String toJson() {
        if (gson != null) {
            return gson.toJson(data);
        } else {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).create();
            return gson.toJson(data);
        }
    }


    public String toJsonWithLong() {
        if (gson != null) {
            return gson.toJson(data);
        } else {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat(DATE_FORMAT).setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
            return gson.toJson(data);
        }
    }

    public String toJsonWithoutNull() {
        if (gson != null) {
            return gson.toJson(data);
        } else {
            Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
            return gson.toJson(data);
        }
    }

    public String toDateTimeJson() {
        if (gson != null) {
            return gson.toJson(data);
        } else {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat(DATE_TIME_FORMAT).create();
            return gson.toJson(data);
        }
    }


}