package com.juststarinfo.SBAP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
    public String home(){
        return "index";
    }
	
	@GetMapping("/index.html")
    public String index(){
        return "index";
    }
	
	@GetMapping("/pages/ui-features/buttons.html")
	public String buttons() {
		return "pages/ui-features/buttons";
	}
	
}
