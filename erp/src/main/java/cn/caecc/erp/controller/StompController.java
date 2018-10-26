package cn.caecc.erp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {
	@MessageMapping("/hello")
    public String greeting(String message)
    {
       
        return null;
    }
}
