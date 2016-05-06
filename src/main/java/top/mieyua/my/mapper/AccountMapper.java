/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.mapper;

import top.mieyua.my.model.Account;
import top.mieyua.my.util.MyMapper;

/**
 * 账号映射类
 */
public interface AccountMapper extends MyMapper<Account> {
    public void insertAccount(Account account);

    public void updateAccountByPrimaryKey(Account account);
}
