/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import top.mieyua.my.util.HTTPResponse;
import top.mieyua.my.model.Account;
import top.mieyua.my.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * 账号控制器
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelMap getAll(Account account) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;
        try {
            List<Account> orderList = accountService.getAll(account);
            if (orderList.size() <= 0) {
                msg = "没有找到数据";
                code = 204;
                result = hp.getResponse(code, msg, null);
            } else {
                msg = "成功查找数据";
                code = 200;
                result = hp.getResponse(code, msg, orderList);
            }
        } catch (Exception e) {
            msg = "服务器错误：" + e.toString();
            code = 503;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelMap view(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;
        try {
            Account account = accountService.getById(id);
            if (account.getId() != id) {
                msg = "没有找到数据";
                code = 410;
                result = hp.getResponse(code, msg, null);
            } else {
                msg = "成功查找数据";
                code = 200;
                result = hp.getResponse(code, msg, account);
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelMap add(@RequestBody String jsonString) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;

        ObjectMapper mapper = new ObjectMapper();
        Account account = new Account();
        try {
            account = mapper.readValue(jsonString.getBytes(), Account.class);
        } catch (Exception e) {
            msg = "Json信息读取失败：" + e.toString();
            code = 410;
            result = hp.getResponse(code, msg, null);
            return result;
        }

        if (account.getId() != null && account.getId() != 0) {
            msg = "新增失败：该账号已经存在";
            code = 410;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        try {
            accountService.saveAccount(account);
            msg = "成功新增数据";
            code = 201;
            result = hp.getResponse(code, msg, null);
        } catch (Exception e) {
            msg = "服务器错误：" + e.toString();
            code = 503;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ModelMap save(@PathVariable Integer id, @RequestBody String jsonString) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;

        ObjectMapper mapper = new ObjectMapper();
        Account account = new Account();
        try {
            account = mapper.readValue(jsonString.getBytes(), Account.class);
        } catch (Exception e) {
            msg = "Json信息读取失败：" + e.toString();
            code = 410;
            result = hp.getResponse(code, msg, null);
            return result;
        }

        if (account.getId() == null && id <= 0) {
            msg = "更新失败：该账号不存在";
            code = 410;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        if (account.getId() != id) {
            msg = "更新失败：想要修改的账号与请求不符合";
            code = 410;
            result = hp.getResponse(code, msg, null);
            return result;
        }
        try {
            accountService.saveAccount(account);
            msg = "成功修改数据";
            code = 200;
            result = hp.getResponse(code, msg, null);
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        HTTPResponse hp = new HTTPResponse();
        String msg = "";
        int code = 200;
        try {
            accountService.deleteById(id);
            msg = "成功删除数据";
            code = 204;
            result = hp.getResponse(code, msg, null);
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
