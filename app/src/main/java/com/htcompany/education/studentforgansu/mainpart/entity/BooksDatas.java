package com.htcompany.education.studentforgansu.mainpart.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书信息
 * Created by wrb on 2016/11/15.
 */
public class BooksDatas {
    public static List<String> createBookType(){
        List<String> bookTypes = new ArrayList<String>();
        bookTypes.add("计算机类");
        bookTypes.add("会计类");
        bookTypes.add("电子类");
        bookTypes.add("机械类");
        bookTypes.add("文学类");
        bookTypes.add("材料类");
        bookTypes.add("软件类");
        bookTypes.add("生物类");
        bookTypes.add("生态类");
        bookTypes.add("化学类");
        bookTypes.add("畜牧类");
        bookTypes.add("农业类");
        bookTypes.add("小说类");
        return bookTypes;
    }
}
