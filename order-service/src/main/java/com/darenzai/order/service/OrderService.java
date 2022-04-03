package com.darenzai.order.service;

import com.darenzai.order.mapper.OrderMapper;
import com.darenzai.order.pojo.Order;
import com.darenzai.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 利用RestTemplate 发送HTTP请求
        String url = "http://localhost:8081/user/"+order.getUserId();
        order.setUser(restTemplate.getForObject(url, User.class));
        // 4.返回
        return order;
    }
}
