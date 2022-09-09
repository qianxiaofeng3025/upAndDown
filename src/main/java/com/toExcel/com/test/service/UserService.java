package com.toExcel.com.test.service;

import com.toExcel.com.test.pojo.AoaUser;

import java.util.List;

public interface UserService{
    public List<AoaUser> selectUserById(int id);
    public List<AoaUser> selectUserBySex(String sex);
    public boolean insertUserById(AoaUser aoaUser);
    public boolean updateInfoByUser(AoaUser aoaUser);
}
