package com.hu.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一：在进入Controller之前，譬如请求一个不存在的地址，404错误。
 */
@RestController
public class FinalExceptionHandler implements ErrorController {
    /**
     	* 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
	
	@Override
    public String getErrorPath() {
        // 错误处理逻辑
        return "404.html";
    }
}
