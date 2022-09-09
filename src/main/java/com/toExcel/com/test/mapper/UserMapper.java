package com.toExcel.com.test.mapper;

import com.toExcel.com.test.pojo.AoaUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "userMapper")
public interface UserMapper {
//    根据用户id查询用户
    public List<AoaUser> selectUserById(int id);
//    根据用户性别查询用户
    public List<AoaUser> selectUserBySex(String sex);
//    插入数据
    public boolean insertUserById(AoaUser aoaUser);
//    更新数据
    public boolean updateInfoByUser(AoaUser aoaUser);
}
