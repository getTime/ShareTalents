package com.kidney_hospital.base.model;

/**
 * Created by 风度侠客 on 2017/3/14.
 */

public class UserInfo {


    /**
     * result : 0000
     * db : {"WXNickName":"","phone":"18617599820","QQName":"","registrationId":"111111","creatTime":1489377689000,"type":0,"password":"7c4a8d09ca3762af61e59520943dc26494f8941b","id":5,"QQUid":"","source":1,"userId":"1df3b196b28e4c92bf39e68074645b5a","userName":"","headUrl":"","WXHeadUrl":"","QQUrl":"","openId":""}
     * retMessage : 恭喜您，登录成功
     */

    private String result;
    private DbBean db;
    private String retMessage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DbBean getDb() {
        return db;
    }

    public void setDb(DbBean db) {
        this.db = db;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public static class DbBean {
        /**
         * WXNickName :
         * phone : 18617599820
         * QQName :
         * registrationId : 111111
         * creatTime : 1489377689000
         * type : 0
         * password : 7c4a8d09ca3762af61e59520943dc26494f8941b
         * id : 5
         * QQUid :
         * source : 1
         * userId : 1df3b196b28e4c92bf39e68074645b5a
         * userName :
         * headUrl :
         * WXHeadUrl :
         * QQUrl :
         * openId :
         */

        private String WXNickName;
        private String phone;
        private String QQName;
        private String registrationId;
        private String creatTime;
        private String type;
        private String password;
        private int id;
        private String QQUid;
        private String source;
        private String userId;
        private String userName;
        private String headUrl;
        private String WXHeadUrl;
        private String QQUrl;
        private String openId;

        public String getWXNickName() {
            return WXNickName;
        }

        public void setWXNickName(String WXNickName) {
            this.WXNickName = WXNickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQQName() {
            return QQName;
        }

        public void setQQName(String QQName) {
            this.QQName = QQName;
        }

        public String getRegistrationId() {
            return registrationId;
        }

        public void setRegistrationId(String registrationId) {
            this.registrationId = registrationId;
        }

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQQUid() {
            return QQUid;
        }

        public void setQQUid(String QQUid) {
            this.QQUid = QQUid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getWXHeadUrl() {
            return WXHeadUrl;
        }

        public void setWXHeadUrl(String WXHeadUrl) {
            this.WXHeadUrl = WXHeadUrl;
        }

        public String getQQUrl() {
            return QQUrl;
        }

        public void setQQUrl(String QQUrl) {
            this.QQUrl = QQUrl;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }
    }
}
