package cn.wolfcode.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主界面相关视图
 */
@Controller
public class MainController {

    @GetMapping({"/","/main"})
    public String main(){
        return "main";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
