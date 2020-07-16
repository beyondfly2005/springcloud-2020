package com.beyondsoft.springcloud.service;

import com.beyondsoft.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {


    int create(Payment payment);

    Payment getPaymentById(Long id);

}
