package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.entities.UserInfo;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("**** insert result");

        if(result > 0 ){
            return new CommonResult(200 ,  "insert successful and serverPort : " + serverPort, result);
        }else{
            return new CommonResult(444,"insert failed and serverPort : " + serverPort,null);
        }
    }

   // @GetMapping(value = "/payment/get/{id}/{serial}")
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable ("id") int id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("lyskyfy ask for select result");
        if(payment != null ){
            return new CommonResult(200, "get successful and serverPort : " + serverPort, payment);
        }else{
            return  new CommonResult(444, "can not find user and serverPort : " + serverPort + id, null);
        }
    }

    @PostMapping(value = "/payment/registerUser")
    public CommonResult registerUser(@RequestBody UserInfo user){
        log.info("Register user " + user.getUserId());
        int isOk = paymentService.registerUser(user);
        if(isOk > 0){
            return  new CommonResult(200,"Register Successful and serverPort : " + serverPort, isOk);
        }else{
            return new CommonResult(404, "Register is failed and serverPort : " + serverPort, isOk);
        }

    }

    @GetMapping(value = "/payment/lb")
    public String gatPaymentLB(){
        return serverPort;
    }

}
