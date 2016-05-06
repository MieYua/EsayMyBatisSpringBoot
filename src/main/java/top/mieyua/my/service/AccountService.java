/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.service;

import top.mieyua.my.mapper.AccountMapper;
import top.mieyua.my.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号服务
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public List<Account> getAll(Account account) {
        return accountMapper.selectAll();
    }

    public Account getById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        accountMapper.deleteByPrimaryKey(id);
    }

    public void save(Account account) {
        if (account.getId() != null && account.getId() != 0) {
            accountMapper.updateByPrimaryKey(account);
        } else {
            accountMapper.insert(account);
        }
    }

    public void saveAccount(Account account) {
        if (account.getId() != null && account.getId() != 0) {
            accountMapper.updateAccountByPrimaryKey(account);
        } else {
            accountMapper.insertAccount(account);
        }
    }
}
