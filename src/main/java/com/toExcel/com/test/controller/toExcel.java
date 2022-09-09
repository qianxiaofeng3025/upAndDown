package com.toExcel.com.test.controller;

import com.toExcel.com.test.pojo.AoaUser;
import com.toExcel.com.test.service.serviceImpl.UserServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class toExcel {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/getById/{id}")
    @ResponseBody
    public List<AoaUser> toPage(@PathVariable("id") int id){
        List<AoaUser> aoaUsersById = userService.selectUserById(id);
        return aoaUsersById;
    }

    @RequestMapping("/getBySex/{sex}")
    @ResponseBody
    public List<AoaUser> toPage(@PathVariable("sex") String sex, HttpServletResponse response){
//        获取到查询结果集
        List<AoaUser> aoaUsersBySex = userService.selectUserBySex(sex);
//        创建一个excel模板
        HSSFWorkbook workbook = new HSSFWorkbook();
//        在excel中创建一个信息表的工作簿
        HSSFSheet sheet = workbook.createSheet("信息表");
//        指定excel名称
        String fileName="userInf"+".xls";
        int rowNum=1;
//        声明一个列名的数组
        String[] headers={"userId","realName","sex","userEdu"};
//        在工作簿中创建第一行
        HSSFRow row = sheet.createRow(0);
//        循环列名数组
        for(int i=0;i<headers.length;i++){
//        创建单元格
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//        每取一个值设置一个单元格内容
            cell.setCellValue(text);
        }
//        循环遍历结果集
        for (AoaUser user: aoaUsersBySex ) {
//        工作簿中创建行
            HSSFRow row1 = sheet.createRow(rowNum);
//        行设置内容
            row1.createCell(0).setCellValue(user.getUserId());
            row1.createCell(1).setCellValue(user.getRealName());
            row1.createCell(2).setCellValue(user.getSex());
            row1.createCell(3).setCellValue(user.getUserEdu());
//        加一行
            rowNum++;
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aoaUsersBySex;
    }
}
