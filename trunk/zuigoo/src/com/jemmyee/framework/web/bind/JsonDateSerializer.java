package com.jemmyee.framework.web.bind;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * @Description: Jason自动包装日期格式化
 * @author:jemmyee@gmail.com
 * @date:2011-7-28
 * @version:v1.0
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	@Override
	public void serialize(Date date, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		String formattedDate = dateFormat.format(date); 
        jg.writeString(formattedDate); 
	}

}
