package com.toExcel.com.test.service.serviceImpl;

import com.toExcel.com.test.mapper.UserMapper;
import com.toExcel.com.test.pojo.AoaUser;
import com.toExcel.com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier(value = "userMapper")
    private UserMapper userMapper;

    @Override
    public List<AoaUser> selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<AoaUser> selectUserBySex(String sex) {
        return userMapper.selectUserBySex(sex);
    }

    @Override
    public boolean insertUserById(AoaUser aoaUser) {
        return userMapper.insertUserById(aoaUser);
    }

    @Override
    public boolean updateInfoByUser(AoaUser aoaUser) {
        return userMapper.updateInfoByUser(aoaUser);
    }

}
