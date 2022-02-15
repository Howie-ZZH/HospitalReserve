package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

public class TestRead {
    public static void main(String[] args) {
        //读取文件路径
        String fileName = "D:\\1随便删\\测试.xlsx";
        //调用方法实现读操作
        EasyExcel.read(fileName,UserData.class,new ExcelListener())
                .sheet("用户信息").doRead();
    }
}
