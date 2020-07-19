package com.beyondsoft.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulController {

    @GetMapping(value = "/echo")
    public String echo(String message) {
        return "Echo:" + message;
    }

    /**
     * consul 检查服务方法
     * @return
     */
    @GetMapping(value = "/info")
    public String info() {
        return "info";
    }
}