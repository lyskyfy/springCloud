package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "consumer/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id) {
        log.info("**************** -----------  *************");
        String result =  paymentHystrixService.paymentInfo_Ok(id);
        return  result;
    }


    @GetMapping(value = "consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        try {
            TimeUnit.SECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result =  paymentHystrixService.paymentInfo_TimeOut(id);
        return  result;
    }

    //定制兜底方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "我是消费端80，对方支付系统繁忙，请稍后再试，实在抱歉";
    }

    //global fallback function
    public String payment_Global_FallbackMethod(){
        return "Global fallback ---- 服务端异常发生，请稍后再试";
    }


}
