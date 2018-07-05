package com.newegg.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gl49 on 2018/6/23.
 */
@Controller
public class ManagerController {
    @RequestMapping("/manager")
    public String manager(Model model){
        return "manager";
    }
}
