package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestWrite {
    public static void main(String[] args) {
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data=new UserData();
            data.setUid(i);
            data.setUsername("第"+i);
            list.add(data);
        }
        //设置Excel文件路径和文件名称
        String fileName = "D:\\1随便删\\测试.xlsx";

        //调用方法写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                .doWrite(list);
    }
}
