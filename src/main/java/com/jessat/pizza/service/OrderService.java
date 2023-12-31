package com.jessat.pizza.service;

import com.jessat.pizza.persistence.entity.OrderEntity;
import com.jessat.pizza.persistence.projection.OrderSummary;
import com.jessat.pizza.persistence.repository.OrderRepository;
import com.jessat.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public static final String DELIVERY = "D";
    public static final String CARRYOUT = "C";
    public static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        //orders.forEach(o -> System.out.println(o.getCustomer().getName())); //FetchType.LAZY
        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int orderId) {
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    @Modifying(clearAutomatically = true)
    public Boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
