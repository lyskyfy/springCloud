package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "-不好意思，我估计是8001服务挂了，请拨打客服电话咨询--PaymentHystrixServiceImpl--paymentInfo_Ok--";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---PaymentHystrixServiceImpl--paymentInfo_TimeOut--";
    }
}
