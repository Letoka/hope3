package com.icbc.zsyw.hope3.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName TcenterLog
 * @Description
 * @Author qinwankang
 * @Date 2020/5/13 21:35
 * @Version V1.0
 **/

public class TcenterLog {
    public  int id;
    public String Title;
    public String Fromtype;

    public int User;
    public Timestamp CreateDate;
    public int Item;
    public int LogAction;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getFromtype() {
        return Fromtype;
    }

    public int getUser() {
        return User;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public int getItem() {
        return Item;
    }

    public int getLogAction() {
        return LogAction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setFromtype(String fromtype) {
        Fromtype = fromtype;
    }

    public void setUser(int user) {
        User = user;
    }

    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }

    public void setItem(int item) {
        Item = item;
    }

    public void setLogAction(int logAction) {
        LogAction = logAction;
    }
}
