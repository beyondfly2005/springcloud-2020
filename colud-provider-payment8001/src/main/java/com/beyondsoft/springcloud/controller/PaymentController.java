package com.beyondsoft.springcloud.controller;

import com.beyondsoft.springcloud.entities.CommonResult;
import com.beyondsoft.springcloud.entities.Payment;
import com.beyondsoft.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ParameterMetaData;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult  create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("**** 插入结果："+result);
        if(result>0){
            return new CommonResult(200,"保存成功，serverPort:"+serverPort,result);
        }
        return new CommonResult(444,"保存失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*** 查询结果+"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功，serverPort:"+serverPort,payment);
        }
        System.out.println("AC");
        return new CommonResult(444,"查询失败");
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获取微服务名列表
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****** element:" + element);
        }
        //一个微服务名下的多个实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}
