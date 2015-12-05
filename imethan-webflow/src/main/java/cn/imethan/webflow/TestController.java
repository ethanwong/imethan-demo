package cn.imethan.webflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TestController.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月5日下午5:35:51
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value="")
	public String test(){
		return "test";
	}

}
