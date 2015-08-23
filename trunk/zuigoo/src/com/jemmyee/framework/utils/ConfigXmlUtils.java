package com.jemmyee.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * @Description:解析系统参数配置文件
 * @author:jemmyee@gmail.com
 * @date:2011-9-8
 * @version:v1.0
 */
public class ConfigXmlUtils{
	//测试路径
	private static String filePath="E:/mywork/secs/WebRoot/WEB-INF/data/config/task.xml";
	
	private static String ROOT_NODE="/configs/config/";
	
	public static void main(String[] args) throws MalformedURLException, DocumentException {
	   editConfig(filePath, "user", "regMsg","我操作草");
       System.out.println(getConfig(filePath, "user", "regMsg"));
	}
	

    /**
    * @Description:读取xml，返回Document
    * @author:jemmyee@gmail.com
    * @date:2010-11-24
    */
    public static Document read(String fileName){
        SAXReader reader = new SAXReader();
        Document document=null;
		try {
			document = reader.read(new File(fileName));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
        return document;
     }    
    

    /**
    * @Description: 得到root节点
    * @author:jemmyee@gmail.com
    * @date:2010-11-24
    */
    public static Element getRootElement(Document doc){
        return doc.getRootElement();
    }
	

    /**
    * @Description:获取配置
    * @author:jemmyee@gmail.com
    * @date:2011-9-8
    */
    public static String getConfig(String fileName,String type,String ckey){
    	Document document=read(fileName);
    	String val="";
    	List list = document.selectNodes("/configs/config[@name='"+type+"']/"+ckey);
    	Iterator it= list.iterator();
    	while(it.hasNext()){
    		Element item =(Element)it.next();
//    		System.out.println("item:"+item.getName()+" val:"+item.getText());
            val=item.getTextTrim();
    	}
    	return val;
    }
    
    /**
    * @Description:修改配置
    * @author:jemmyee@gmail.com
    * @date:2011-9-8
    */
    public static void editConfig(String fileName,String type,String ckey,String cval){
    	Document document=read(fileName);
      	List list = document.selectNodes("/configs/config[@name='"+type+"']/"+ckey);
    	Iterator it= list.iterator();
    	while(it.hasNext()){
    		Element item =(Element)it.next();
            item.setText(cval);
            XMLWriter writer;
       		try {
       			writer = new XMLWriter(new FileOutputStream(new File(fileName)));
       			writer.write(document);
       		    writer.close();
       		} catch (IOException e) {
       			e.printStackTrace();
       		}
    	}
    }
	
}
