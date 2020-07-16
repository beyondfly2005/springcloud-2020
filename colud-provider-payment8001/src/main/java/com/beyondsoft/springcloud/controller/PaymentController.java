package com.beyondsoft.springcloud.controller;

import com.beyondsoft.springcloud.entities.CommonResult;
import com.beyondsoft.springcloud.entities.Payment;
import com.beyondsoft.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ParameterMetaData;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult  create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**** 插入结果："+result);
        if(result>0){
            return new CommonResult(200,"保存成功",result);
        }
        return new CommonResult(444,"保存失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*** 查询结果+"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }
        System.out.println("AC");
        return new CommonResult(444,"查询失败");
    }
}
