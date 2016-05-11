/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 账号自定义字段类
 */
@Table(name = "my_account_addition")
public class AccountAddition {
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private int id;
    @Column(name = "account_id")
    @JsonProperty("account_id")
    private int accountId;
    @Column(name = "addition_key")
    @JsonProperty("addition_key")
    private String additionKey;
    @Column(name = "addition_value")
    @JsonProperty("addition_value")
    private String additionValue;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAdditionKey() {
        return additionKey;
    }

    public void setAdditionKey(String additionKey) {
        this.additionKey = additionKey;
    }

    public String getAdditionValue() {
        return additionValue;
    }

    public void setAdditionValue(String additionValue) {
        this.additionValue = additionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
