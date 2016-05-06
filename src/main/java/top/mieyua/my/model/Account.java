/*
 * Copyright (C) Mie Yua <mieyua@126.com>, 2016.
 * All rights reserved.
 */

package top.mieyua.my.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 账号类
 */
@Table(name = "my_account")
public class Account {
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private int id;
    @Column(name = "username")
    @JsonProperty("username")
    private String username;
    @Column(name = "password")
    @JsonProperty("password")
    private String password;
    @Column(name = "register_time")
    @JsonProperty(value = "register_time")
    private Date registerTime;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
