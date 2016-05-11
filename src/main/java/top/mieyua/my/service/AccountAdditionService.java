/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.service;

import top.mieyua.my.mapper.AccountAdditionMapper;
import top.mieyua.my.mapper.AccountMapper;
import top.mieyua.my.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mieyua.my.model.AccountAddition;

import java.util.List;

/**
 * 账号自定字段服务
 */
@Service
public class AccountAdditionService {

    @Autowired
    private AccountAdditionMapper accountAdditionMapper;

    public List<AccountAddition> getAll(AccountAddition accountAddition) {
        return accountAdditionMapper.selectAll();
    }

    public AccountAddition getById(Integer id) {
        return accountAdditionMapper.selectByPrimaryKey(id);
    }

    public List<AccountAddition> getByAccountId(Integer accountId) { return accountAdditionMapper.selectByAccountId(accountId); }

    public void deleteById(Integer id) {
        accountAdditionMapper.deleteByPrimaryKey(id);
    }

    public void save(AccountAddition accountAddition) {
        if (accountAddition.getId() != null && accountAddition.getId() != 0) {
            accountAdditionMapper.updateByPrimaryKey(accountAddition);
        } else {
            accountAdditionMapper.insert(accountAddition);
        }
    }
}
