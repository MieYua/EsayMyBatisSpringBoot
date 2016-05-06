/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.util;

import org.springframework.ui.ModelMap;

/**
 * RESTful JSON返回
 */
public class HTTPResponse {
    public ModelMap getResponse(int code, String msg, Object o) {
        ModelMap result = new ModelMap();
        ModelMap meta = new ModelMap();
        meta.put("code", code);
        meta.put("message", msg);
        result.put("meta", meta);
        result.put("data", o);
        return result;
    }
}
