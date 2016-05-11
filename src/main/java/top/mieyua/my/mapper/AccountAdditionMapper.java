/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.mapper;

import top.mieyua.my.model.AccountAddition;
import top.mieyua.my.util.MyMapper;

import java.util.List;

/**
 * 账号自定字段映射类
 */
public interface AccountAdditionMapper extends MyMapper<AccountAddition> {
    public List<AccountAddition> selectByAccountId(Integer accountId);
}
