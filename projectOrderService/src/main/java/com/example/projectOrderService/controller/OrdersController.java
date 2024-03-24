package com.example.projectOrderService.controller;

import com.example.projectOrderService.exception.NoSuchUserExceptions;
import com.example.projectOrderService.model.Orders;
import com.example.projectOrderService.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/get/{id}")
    public Orders getOrderById(@PathVariable Long id) {
        Orders orders = ordersService.getOneOrders(id);

        if (orders == null) {
            throw new NoSuchUserExceptions("Order with id = "+id+" not found!");
        }

        return orders;
    }

    @GetMapping("/get-orders")
    public List<Orders> getOrders() {
        return ordersService.getAllOrders();
    }

    @PostMapping("/create")
    public Orders createOrder(@RequestBody Orders orders){
        System.out.println(orders.toString());
        return ordersService.createOrders(orders);
    }

    @PutMapping("/upd/{id}")
    public Orders updOrder(@PathVariable Long id, @RequestBody Orders orders) {
        orders.setId(id);
        return ordersService.updateOrders(orders);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        Orders orders = ordersService.getOneOrders(id);

        if (orders == null) {
            throw new NoSuchUserExceptions("Order with id = "+id+" not found!");
        } else {
            ordersService.deleteOrders(id);
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(NoSuchUserExceptions exceptions){
        String msg = exceptions.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

}
