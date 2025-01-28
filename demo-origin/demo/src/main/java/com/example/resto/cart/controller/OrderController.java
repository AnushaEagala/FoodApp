package com.example.resto.cart.controller;

import  com.example.resto.cart.model.Order;
import  com.example.resto.cart.service.OrderService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();

    }




    @GetMapping("/newOrderForCurrentUser/{name}")
    public ResponseEntity<Order> findOrder(@PathVariable String name) {
        Order order = orderService.findOrder(name);
        if(order != null) return new ResponseEntity<>(order, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> addOrder(@RequestBody Map<String, Object> order) throws IOException {
//        if(order == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Order newOrder = orderService.addOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PostMapping("/pay")
    public ResponseEntity<Long> pay(@RequestBody Map<String, Object> order) throws IOException {
//        if(order == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String name = order.get("name").toString();
        Order orderFound = orderService.findOrder(name);
        if(order == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(orderService.pay(name).getOrder_id(), HttpStatus.OK);
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity<Order> trackOrder(@PathVariable Long orderId) throws IOException {
        Order order = orderService.trackOrder(orderId);
        if(order != null) return new ResponseEntity<>(order, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




    @PostMapping("/grab")
    public ResponseEntity<Order> grabOrder(@RequestBody Map<String, Object> request) {
        Long orderId = Long.valueOf(request.get("orderId").toString());
        Long userId = Long.valueOf(request.get("userId").toString());
        Long riderId = Long.valueOf(request.get("riderId").toString());
        Double amount = Double.valueOf(request.get("amount").toString()); // 确保使用 Double
        Double estimatedTime = Double.valueOf(request.get("estimatedTime").toString());

        Order updatedOrder = orderService.updateOrderStatus(orderId, "SHIPPED");
        orderService.createDeliveryOrder(orderId, userId, riderId, amount, estimatedTime);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
