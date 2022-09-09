package com.toExcel.com.test.controller;

import com.toExcel.com.test.mapper.UserMapper;
import com.toExcel.com.test.pojo.AoaUser;
import com.toExcel.com.test.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class UpLoad {

    @Autowired
    private ImportService importService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/upLoad")
    public String upLoad(){
        return "upLoadPage";
    }

    @PostMapping("/upLoadFile")
    @ResponseBody
    public String upLoadFile(@RequestParam("upFile") MultipartFile upFile) throws IOException {
        if(upFile.isEmpty()){
            return "文件名不能为空";
        }
//      获取文件名字
        String filename = upFile.getOriginalFilename();
        System.out.println(filename);
//      生成一个输入流
        InputStream inputStream = upFile.getInputStream();
//      返回一个泛型为集合的集合
        List<List<Object>> list= importService.getInfoByExcel(inputStream, filename);
        inputStream.close();
//      遍历集合，获取每一行的数据集合
        for(int i=0;i<list.size();i++){
            List<Object> lo= list.get(i);
            System.out.println(lo);
//          上传的用户id
            int userId = Integer.parseInt((String)lo.get(0));
//          上传的姓名
            String realName = (String) lo.get(1);
//          上传的性别
            String sex = (String) lo.get(2);
//          上传的学历
            String userEdu = (String) lo.get(3);
            List<AoaUser> aoaUsers = userMapper.selectUserById(userId);
            if(aoaUsers.size()!=0){
                System.out.println("有此ID");
                userMapper.updateInfoByUser(new AoaUser(userId, realName, sex, userEdu));
            }else{
                System.out.println("没有此条ID");
                userMapper.insertUserById(new AoaUser(userId, realName, sex, userEdu));
            }
        }
        return "success";
    }
}
