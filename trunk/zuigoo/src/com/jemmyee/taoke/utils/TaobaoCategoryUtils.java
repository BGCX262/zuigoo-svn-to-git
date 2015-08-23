package com.jemmyee.taoke.utils;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.service.ITaokeCategoryService;

/**
 * @Description:解析抓取淘宝目录结构
 * @author:jemmyee@gmail.com
 * @date:2011-8-14
 * @version:v1.0
 */
public class TaobaoCategoryUtils {
     public static String URL="http://list.taobao.com/browse/cat-0.htm?spm=1.47806.91825.1&ad_id=&am_id=&cm_id=&pm_id=";
	
	 public static void main(String[] args){
		  String[] configs = new  String[] {
					"/com/jemmyee/core/config/spring/spring-dao.xml",
					"/com/jemmyee/core/config/spring/spring-service.xml",
					"/com/jemmyee/core/config/spring/spring-datasource.xml",
					"/com/jemmyee/core/config/spring/spring-hibernate.xml"
					};
			ApplicationContext act = new ClassPathXmlApplicationContext(configs);
	        ITaokeCategoryService<TaokeCategory> taokeCategoryService=(ITaokeCategoryService<TaokeCategory>)act.getBean("taokeCategoryService");
		 
		 Document doc;
		try {
			doc = Jsoup.connect(URL).get();
		
		 Elements es = doc.select("div.market-cat");
		 for(Element e:es)
		 {
			 Element eroot=e.select("h4").first();//一级目录
			 System.out.println(" ");
			 if(!eroot.text().equals("虚拟票务")&&!eroot.text().equals("生活服务"))
			 {
				 System.out.println("一级目录:"+eroot.text());
				 TaokeCategory tcroot=new TaokeCategory();
				 tcroot.setAddTime(new Date());
				 tcroot.setParentId(0);
				 tcroot.setType((short)1);
				 tcroot.setSortOrder(50);
				 tcroot.setIsDelete((short)0);
				 tcroot.setIsRecommend((short)0);
				 tcroot.setIsLast((short)0);
				 tcroot.setName(eroot.text().trim());
				 int rootkey=(Integer)taokeCategoryService.save(tcroot);
				 Elements eseconds=e.select("li.g-u");
				 for(Element ee:eseconds)
				 {
					 Element esecond=ee.select("li.sub").first();//一级目录
					 if(esecond!=null&&!esecond.text().contains("价格")&&!esecond.text().contains("尺码")&&!esecond.text().contains("人群"))
					 {
						 TaokeCategory tcsec=new TaokeCategory();
						 tcsec.setAddTime(new Date());
						 tcsec.setParentId(rootkey);
						 tcsec.setName(esecond.text().trim());
						 if(esecond.text().contains("品牌"))
						 {
							 tcsec.setType((short)2);
						 }else
						 {
							 tcsec.setType((short)1);
						 }
						 tcsec.setSortOrder(50);
						 tcsec.setIsDelete((short)0);
						 tcsec.setIsRecommend((short)0);
						 tcsec.setIsLast((short)0);
						 int seckey=(Integer)taokeCategoryService.save(tcsec);
						 String strsecond=esecond.text().trim();
						 System.out.println("二级目录:"+strsecond+" 2  ");
		                 Elements els=ee.select("li");	
						 for(Element el:els)
						 {
//							 System.out.println(el.html());
							 String strlast=el.text().trim();
						     if(!strsecond.equals(strlast)&&strlast.length()<30){
								 TaokeCategory tclast=new TaokeCategory();
								 tclast.setAddTime(new Date());
								 tclast.setParentId(seckey);
								 tclast.setType((short)1);
								 tclast.setName(strlast);
								 tclast.setSortOrder(50);
								 tclast.setIsDelete((short)0);
								 tclast.setIsLast((short)1);
								 tclast.setIsRecommend((short)1);
								 int lastkey=(Integer)taokeCategoryService.save(tclast);
								 System.out.print("["+strlast+"]   3  ");//三级目录
							 }
						 }
						 System.out.println("  ");
					 }
					
				 }
			 }
		
		 }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
