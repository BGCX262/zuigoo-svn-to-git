package com.jemmyee.framework.web.bind;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;


/**
 * @Description:表单日期类型包装
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public class DateBindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 匹配的日期格式自动生效绑定
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // 可以設定任意的日期格式
		dateFormat.setLenient(false);
		dateFormat1.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat1, true));
		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));
	}

}
