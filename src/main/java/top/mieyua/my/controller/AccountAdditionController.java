/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import top.mieyua.my.util.HTTPResponse;
import top.mieyua.my.util.PageDivider;
import top.mieyua.my.model.AccountAddition;
import top.mieyua.my.service.AccountAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * 产品库存控制器
 */
@RestController
@RequestMapping("/accountAddition")
public class AccountAdditionController {
    @Autowired
    private AccountAdditionService accountAdditionService;

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ModelMap viewStockByPage(@PathVariable Integer accountId, @Param("p") Integer p, @Param("n") Integer n) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;
        try {
            List<AccountAddition> accountAdditionList = accountAdditionService.getByAccountId(accountId);
            if (accountAdditionList.size() <= 0) {
                msg = "没有找到数据";
                code = 410;
                result = hp.getResponse(code, msg, null);
            } else {
                if (p == null && n == null) {
                    msg = "成功查找数据";
                    code = 200;
                    result = hp.getResponse(code, msg, accountAdditionList);
                } else if (p > 0 && n > 0) {
                    PageDivider pd = new PageDivider();
                    ModelMap modelMap = pd.getPageOfAccountAdditions(p, n, accountAdditionList);
                    msg = "成功查找数据";
                    code = 200;
                    result = hp.getResponse(code, msg, modelMap);
                } else {
                    msg = "传递参数出错";
                    code = 410;
                    result = hp.getResponse(code, msg, null);
                }
            }
        } catch (Exception e) {
            switch (e.toString()) {
                case "java.lang.NullPointerException":
                    msg = "没有对应数据";
                    break;
                default:
                    msg = "服务器错误：" + e.toString();
            }
            code = 503;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        return result;
    }
}
