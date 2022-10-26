package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class TestWrite {
    public static void main(String[] args) {
        //设置文件的名称和路径
        String fileName = "D:\\Fever.xlsx";
        //调用方法
        EasyExcel.write(fileName,User.class)
                .sheet("写操纵")
                .doWrite(data());
    }
    private static List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("lucy"+i);
            list.add(data);
        }
        return list;
    }

}
