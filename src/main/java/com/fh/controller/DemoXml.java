package com.fh.controller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DemoXml {
    public static void main(String[] args) {
        /*
         * 解析XML的大致步骤
         * 1:创建SAXReader
         * 2:使用SAXReader读取XML文档并生成Document对象
         *   这一步就是DOM解析耗时耗资源的地方 因为要先将XML文档内容全部读取完毕
         *   并生成一个Document对象.
         * 3：通过Document获取根元素
         * 4：按照XML文档的结构从根元素开始 逐级获取子元素以达到遍历XML文档数据的目的.
         */

        /*
         * 该集合用于保存从emplist.xml文档中解析出来的员工信息
         */
        List<Emp> list=new ArrayList<Emp>();
        try{
            //1.创建SAXReader
            SAXReader reader=new SAXReader();
            //2.使用SAXReader读取XML文档并生成Document对象
            Document doc=reader.read(new FileInputStream("D:\\13213456\\emplist.xml"));
            /*
             * Document提供了获取根元素的方法：
             * Element getRootElement()
             *
             * Element 的每一个实例用于表示XML文档中的一个元素（一对标签）
             *
             * Element 提供了获取其元素的相关信息的方法：
             *
             * String getName()
             * 获取标签的名字
             *
             * String getText()
             * 获取标签中间的文本信息（开始标签与结束标签之间的文本）
             *
             * Elment element(String name)
             * 获取指定名字的子元素
             *
             * List elements(String name)
             * 获取所有同名子元素
             *
             * Attribute attribute(String name)
             * 获取指定名字的属性
             */
            Element root=doc.getRootElement();
            /*
             * 获取根元素<list>下面所有子元素
             * 若干个<emp>元素.而每一个<emp>元素也是使用Element实例表示，
             * 并存入集合后返回.
             */
            List<Element> empList= root.elements();
            for(Element empEle:empList){
                //获取员工姓名
                Element nameEle=empEle.element("name");
                String name=nameEle.getText();
                //获取age
                int age=Integer.parseInt(empEle.elementText("age"));
                /*
                 * Element有一个可以快速获取子标签中间的文本方法：
                 * String elementText(String name);
                 * 例如：
                 * empEle.elementText("gender")
                 * 等同于
                 * empEle.element("gender").getText()
                 *
                 */
                //获取gender
                String gender=empEle.elementText("gender");
                //获取salary
                int salary=Integer.parseInt(empEle.elementText("salary"));
                int id=Integer.parseInt(empEle.attributeValue("id"));
                Emp emp=new Emp(id,name,age,gender,salary);
                list.add(emp);
            }
            System.out.println("解析完毕！");
            for(Emp e:list){
                System.out.println(e);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
