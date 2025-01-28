package com.example.resto.cart.service;


import  com.example.resto.cart.enums.OrderStatus;
import  com.example.resto.cart.model.Order;
import  com.example.resto.cart.repository.OrderRepository;

import com.example.resto.delivery.exception.OrderNotFoundException;
import com.example.resto.delivery.model.DeliveryOrder;
import com.example.resto.delivery.repository.DeliveryOrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;



    @Autowired
    public OrderService(OrderRepository orderRepository, DeliveryOrderRepository deliveryOrderRepository) {
        this.orderRepository = orderRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    public Optional<DeliveryOrder> getDeliveryOrderById(Long id) {
        return deliveryOrderRepository.findById(id);
    }

    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public DeliveryOrder createDeliveryOrder(Long orderId, Long userId, Long riderId, Double amount, Double estimatedTime) {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setOrderId(orderId);
        deliveryOrder.setUserId(userId);
        deliveryOrder.setRiderId(riderId);
        deliveryOrder.setAmount(amount);
        deliveryOrder.setEstimatedTime(estimatedTime);
        deliveryOrder.setStatus("SHIPPED");
        deliveryOrder.setAcceptedAt(LocalDateTime.now());
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public DeliveryOrder updateDeliveryOrder(Long id, DeliveryOrder deliveryOrderDetails) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryOrder not found for this id :: " + id));

        deliveryOrder.setOrderId(deliveryOrderDetails.getOrderId());
        deliveryOrder.setUserId(deliveryOrderDetails.getUserId());
        deliveryOrder.setRiderId(deliveryOrderDetails.getRiderId());
        deliveryOrder.setAmount(deliveryOrderDetails.getAmount());
        deliveryOrder.setEstimatedTime(deliveryOrderDetails.getEstimatedTime());
        deliveryOrder.setStatus(deliveryOrderDetails.getStatus());
        deliveryOrder.setAcceptedAt(deliveryOrderDetails.getAcceptedAt());
        deliveryOrder.setDeliveredAt(deliveryOrderDetails.getDeliveredAt());

        return deliveryOrderRepository.save(deliveryOrder);
    }

    public void deleteDeliveryOrder(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryOrder not found for this id :: " + id));

        deliveryOrderRepository.delete(deliveryOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
        order.setStatus(status);
        return orderRepository.save(order);
    }




    public Order addOrder(Map<String, Object> order) throws IOException {
        System.out.println("Received order: " + order);

        Order newOrder = new Order();
        String items = order.get("items").toString();
        String addressLatLng = order.get("addressLatLng").toString();
        String name = order.get("name").toString();
        String address = order.get("address").toString();
        String totalPrice = order.get("totalPrice").toString();
        Long userId = Long.parseLong(order.get("userId").toString());

        // Extract restaurantId from items string
        Long restaurantId = extractRestaurantIdFromItems(items);

        newOrder.setName(name);
        newOrder.setAddress(address);
        newOrder.setItems(items);
        newOrder.setAddressLatLng(addressLatLng);
        newOrder.setTotalPrice(Float.parseFloat(totalPrice));
        newOrder.setUser_id(userId);
        newOrder.setRestaurant_id(restaurantId);
        newOrder.setStatus("NEW");
        newOrder.setCreatedAt(new Date());

        return orderRepository.save(newOrder);
    }

    private Long extractRestaurantIdFromItems(String items) {
        String prefix = "resturantId=";
        int startIndex = items.indexOf(prefix) + prefix.length();
        int endIndex = items.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = items.indexOf("}", startIndex);
        }
        if (startIndex > -1 && endIndex > startIndex) {
            try {
                return Long.parseLong(items.substring(startIndex, endIndex).trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Order findOrder(String username) {
        return orderRepository.findByUsername(username);
    }

    public Order pay(String name) {
        Order orderFound = orderRepository.findByUsername(name);
        orderFound.setPaymentId(Long.parseLong(String.valueOf((int)(Math.random() * 1000000))));
        orderFound.setStatus("PAYED");
        return orderRepository.save(orderFound);
    }

    public Order trackOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId.toString()));
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for this id :: " + orderId));
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for this id :: " + orderId));

        order.setName(orderDetails.getName());
        order.setAddress(orderDetails.getAddress());
        order.setItems(orderDetails.getItems());
        order.setAddressLatLng(orderDetails.getAddressLatLng());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());
        order.setCreatedAt(orderDetails.getCreatedAt());
        order.setPaymentId(orderDetails.getPaymentId());

        return orderRepository.save(order);
    }
}
