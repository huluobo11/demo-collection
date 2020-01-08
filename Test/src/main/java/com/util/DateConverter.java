
package com.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.WebRequest;

public class DateConverter implements Converter<String, Date> {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 日期转换器
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (source == null || "".equals(source.trim())) {
			return null;
		} else {
			try {
				Date parse = simpleDateFormat.parse(source);
				return parse;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

}
