package com.jack.test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\mujj\\Downloads\\testtemplate\\src\\main\\resource\\template"));
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding("UTF-8");

            Template template = configuration.getTemplate("static.ftl");

            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("description","hello");

            List<String> nameList = new ArrayList<String>();
            nameList.add("陈靖仇");
            nameList.add("玉儿");
            nameList.add("宇文拓");
            paramMap.put("nameList", nameList);

            Map<String, Object> weaponMap = new HashMap<String, Object>();
            weaponMap.put("first", "轩辕剑");
            weaponMap.put("second", "崆峒印");
            weaponMap.put("third", "女娲石");
            weaponMap.put("fourth", "神农鼎");
            weaponMap.put("fifth", "伏羲琴");
            weaponMap.put("sixth", "昆仑镜");
            weaponMap.put("seventh", null);
            paramMap.put("weaponMap", weaponMap);

            Writer writer = new OutputStreamWriter(new FileOutputStream("success.html"),"UTF-8");
            template.process(paramMap,writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
