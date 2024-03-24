package com.example.projectOrderService.service;

import com.example.projectOrderService.model.Orders;
import com.example.projectOrderService.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders createOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOneOrders(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public Orders updateOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public void deleteOrders(Long id) {
        ordersRepository.deleteById(id);
    }

}
