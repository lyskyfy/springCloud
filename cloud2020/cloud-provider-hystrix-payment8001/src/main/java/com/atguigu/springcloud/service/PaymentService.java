package com.atguigu.springcloud.service;


import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    //正常访问
    public String paymentInfo_ok(Integer id){
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_ok , id : " + id + "O(∩_∩)O哈哈~";
    }

    //非正常访问
    public String paymentInfo_TimeOut(Integer id){
        try {
            int timeNumber = 3;
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOut , id : " + id + "O(∩_∩)O哈哈~ 耗时3秒钟";
    }


}
