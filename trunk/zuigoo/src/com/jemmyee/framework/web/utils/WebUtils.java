package com.jemmyee.framework.web.utils;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Description: TODO
 * @author:jemmyee@gmail.com
 * @date:2010-11-16
 * @version:v1.0
 */
public class WebUtils {
	
	/**
	* @Description: 语言加载
	* @author:jemmyee@gmail.com
	* @date:2010-11-16
	*/
	public static void initViewParam(ServletContext application,String mode)
    {
        File file = new File(application.getRealPath(""),"");
        SAXReader reader = new SAXReader(); 
        reader.setEncoding("UTF8");
        try {
            Document doc = reader.read(file);
            Element root = doc.getRootElement();
            List<Element> eles = root.selectNodes("attr");
            for(Element n:eles){
                String key = n.attributeValue("ID");
                Element e = n.element(mode);
                String val = e.getTextTrim();
                application.setAttribute(key, (val==null||val.length()<=0)?key:val);
                System.out.println(key+" = "+((val==null||val.length()<=0)?key:val));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
//		String s="{data:'北京',attr : { id :'2'}}";
//		System.out.println(s.charAt(0));
//		System.out.println(s.charAt(1));
//		System.out.println(s.charAt(2));
	}


}
