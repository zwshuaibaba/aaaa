package com.fh.controller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteXMLDemo {
    public static void main(String[] args) {
        /*
         *生成XML文档的大致步骤
         *1：创建一个Document对象表示一个空白文档。
         *2：向Document对象中添加根元素
         *3：按照预定的XML文档结构从根元素开始逐级添加子元素
         *4：创建XMLWriter
         *5：通过XMLWriter将Document写出以形成XML文档
         */

        List<Emp> list=new ArrayList<Emp>();
        list.add(new Emp(1,"安其拉",25,"男",5000));
        list.add(new Emp(2,"妲己",15,"女",25000));
        list.add(new Emp(3,"亚瑟",35,"男",15000));
        list.add(new Emp(4,"典韦",55,"男",7000));

        try{
            //1.创建一个Document对象表示一个空白文档。
            Document doc= DocumentHelper.createDocument();

            /*
             * Document 提供了添加根源的方法
             * Element addElement(String name)
             * 向当前文档中添加给定名字的根元素，并将它以Element
             * 实例返回，以便继续向根元素中追加内容
             * 需要注意，该方法只能调用一次，因为一个文档中只能有一个根元素
             */

            Element root=doc.addElement("list");
            /*
             * Element提供了向标签中添加信息的相关方法：
             *
             * Element addElement(String name)
             * 添加给定名字的子标签并将其返回
             *
             * Element addText(String text)
             * 添加文本信息,返回值为当前标签，这样做便于连续追加操作
             *
             * Element addAttribute(String name,String value)
             * 添加给定名字及对应值的属性，返回值为当前标签
             *
             *
             * 将每一个emp实例以一个<emp>标签的形式 添加到根标签<list>中
             */
            for(Emp emp:list){
                Element empEle =root.addElement("emp");
                //添加<name>
                Element nameEle=empEle.addElement("name");
                nameEle.addText(emp.getName());
                //添加<age>
                Element ageEle=empEle.addElement("age");
                ageEle.addText(String.valueOf(emp.getAge()));
                //添加<gender>
                Element genderEle=empEle.addElement("gender");
                genderEle.addText(emp.getGender());
                //添加<salary>
                Element salaryEle=empEle.addElement("salary");
                salaryEle.addText(String.valueOf(emp.getSalary()));
                //添加id属性
                empEle.addAttribute("id",String.valueOf(emp.getId()));
            }
            //4.创建XMLWriter
            XMLWriter writer=new XMLWriter(new FileOutputStream("D:\\导出的xml\\myemp.xml"),
                    OutputFormat.createPrettyPrint());
            //5.通过XMLWriter将Document写出以形成XML文档
            writer.write(doc);
            System.out.println("写出完毕！");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
