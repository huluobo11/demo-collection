package cn.itcast.ssm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.ItemsCustom;

/**
 * 
 * <p>Title: JsonTest</p>
 * <p>Description: json测试</p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-3-22上午11:24:12
 * @version 1.0
 */
@Controller
public class JsonTest {

		//请求的json响应json，请求商品信息，商品信息用json格式，输出商品信息
		@RequestMapping("/requestJson")
		public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom)throws Exception{
			
			
			return itemsCustom;
			
		}
		
	
		//请求key/value响应json
		@RequestMapping("/responseJson")
		public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom)throws Exception{
			
			return itemsCustom;
		}
}
