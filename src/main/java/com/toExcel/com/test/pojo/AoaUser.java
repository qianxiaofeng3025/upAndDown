package com.toExcel.com.test.pojo;

public class AoaUser {
    private int userId;
    private String realName;
    private String sex;
    private String userEdu;

    public AoaUser() {
    }

    public AoaUser(int userId, String realName, String sex, String userEdu) {
        this.userId = userId;
        this.realName = realName;
        this.sex = sex;
        this.userEdu = userEdu;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserEdu() {
        return userEdu;
    }

    public void setUserEdu(String userEdu) {
        this.userEdu = userEdu;
    }

    @Override
    public String toString() {
        return "AoaUser{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", userEdu='" + userEdu + '\'' +
                '}';
    }
}
