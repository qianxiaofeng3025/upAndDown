package com.toExcel.com.test.service;

import com.toExcel.com.test.pojo.AoaUser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {

    public List getInfoByExcel(InputStream inputStream,String fileName) throws IOException {
        ArrayList list = new ArrayList();
//      根据后缀获取excel对象
        Workbook work = this.getWorkBook(inputStream, fileName);

        if(null==work){
            throw new RuntimeException("创建的工作簿为空");
        }
//      工作簿
        Sheet sheet=null;
//      行数
        Row row=null;
//      单元格
//        Cell cell=null;
//      循环工作簿
        for(int i=0;i<work.getNumberOfSheets();i++){
            sheet = work.getSheetAt(i);
            if(sheet==null){
                continue;
            }
//          循环行数
            for(int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++){
                row=sheet.getRow(j);
                if(row==null || row.getFirstCellNum()==j){
                    continue;
                }
//              读取每一列的数据，并存储进数组中
                List<Object> li = new ArrayList();
                for(int y=row.getFirstCellNum();y<row.getLastCellNum();y++){
//              设置每行第一个单元格的格式为整形
                    row.getCell(0).setCellType(CellType.STRING);
//              获取字符串类型
                    String cell = row.getCell(y).getStringCellValue();
//                    System.out.println("当前获取的值为："+cell);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
//      返回list数组
        return list;
    }

    public Workbook getWorkBook(InputStream inputStream,String fileName) throws IOException {
        Workbook workbook=null;
//      获取到文件后缀名
        String fileType = fileName.substring(fileName.lastIndexOf("."));
//      如果是xls后缀名的话创建HSSF对象
        if (".xls".equals(fileType)){
            workbook = new HSSFWorkbook(inputStream);
//      如果是xlsx的话创建XSSF对象
        }else if("xlxs".equals(fileType)){
            XSSFWorkbook sheets = new XSSFWorkbook(inputStream);
        }else{
            throw new RuntimeException("文件类型错误");
        }
//      返回excel对象
        return workbook;
    }
}
