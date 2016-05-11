/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.util;

import top.mieyua.my.model.*;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * 分页器
 */
public class PageDivider {
    // 自定字段分页器
    public ModelMap getPageOfAccountAdditions(int p, int n, List<AccountAddition> accountAdditionList) {
        ModelMap modelMap = new ModelMap();
        AccountAddition ps[] = new AccountAddition[n];
        if (p <= 0 || n <= 0) {
            return modelMap;
        }
        int length = accountAdditionList.size();
        if (length <= n && p > 1) {
            return modelMap;
        } else {
            int end = p * n;
            int j = 0;
            if ((p * n) > length) {
                end = length;
            }
            ps = new AccountAddition[end - (p - 1) * n];
            for (int i = (p - 1) * n; i < end; i++) {
                ps[j] = accountAdditionList.get(i);
                j++;
            }
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(p);
        pageInfo.setItemsPerPage(n);
        int totalPage = length / n;
        if (length % n != 0) {
            totalPage++;
        }
        pageInfo.setTotalPage(totalPage);
        modelMap.put("page_info", pageInfo);
        modelMap.put("products", ps);
        return modelMap;
    }
}
