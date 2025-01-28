package com.example.resto.delivery.controller;

import com.example.resto.delivery.model.DeliveryOrder;
import com.example.resto.delivery.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/deliveryOrders")
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @GetMapping
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOrder> getDeliveryOrderById(@PathVariable Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrderById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryOrder not found for this id :: " + id));
        return ResponseEntity.ok().body(deliveryOrder);
    }

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<List<DeliveryOrder>> getDeliveryOrdersByRiderId(@PathVariable Long riderId) {
        List<DeliveryOrder> deliveryOrders = deliveryOrderService.getDeliveryOrdersByRiderId(riderId);
        return ResponseEntity.ok().body(deliveryOrders);
    }

    @PostMapping
    public ResponseEntity<DeliveryOrder> createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        DeliveryOrder createdDeliveryOrder = deliveryOrderService.createDeliveryOrder(deliveryOrder);
        return new ResponseEntity<>(createdDeliveryOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryOrder> updateDeliveryOrder(@PathVariable Long id, @RequestBody DeliveryOrder deliveryOrderDetails) {
        DeliveryOrder updatedDeliveryOrder = deliveryOrderService.updateDeliveryOrder(id, deliveryOrderDetails);
        return ResponseEntity.ok(updatedDeliveryOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryOrder(@PathVariable Long id) {
        deliveryOrderService.deleteDeliveryOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<DeliveryOrder> confirmDeliveryOrder(@PathVariable Long id) {
        DeliveryOrder updatedDeliveryOrder = deliveryOrderService.updateDeliveryOrderStatusToCompleted(id);
        return ResponseEntity.ok(updatedDeliveryOrder);
    }
}
