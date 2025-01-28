package com.example.resto.cart.model;


import  com.example.resto.cart.enums.OrderStatus;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long order_id;

    @Column(nullable = true)
    private Long user_id;

    private String name; // username

    private String address;
    @Column(nullable = true)
    private Long restaurant_id;

    @Column(columnDefinition = "TEXT")
    private String items;

    private String addressLatLng;

    private float totalPrice;
    private String status;

    private Date createdAt;
    private Date updatedAt;

    private Long paymentId;

    public Order() {
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getAddressLatLng() {
        return addressLatLng;
    }

    public void setAddressLatLng(String addressLatLng) {
        this.addressLatLng = addressLatLng;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", restaurant_id=" + restaurant_id +
                ", items='" + items + '\'' +
                ", addressLatLng='" + addressLatLng + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", paymentId=" + paymentId +
                '}';
    }
}
