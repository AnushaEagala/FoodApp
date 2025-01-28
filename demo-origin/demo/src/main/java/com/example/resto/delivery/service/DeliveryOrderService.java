package com.example.resto.delivery.service;

import com.example.resto.cart.service.OrderService;
import com.example.resto.delivery.model.DeliveryOrder;
import com.example.resto.delivery.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryOrderService {

    @Autowired
    private DeliveryOrderRepository deliveryOrderRepository;
    @Autowired
    private OrderService orderService;

    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    public Optional<DeliveryOrder> getDeliveryOrderById(Long id) {
        return deliveryOrderRepository.findById(id);
    }

    public List<DeliveryOrder> getDeliveryOrdersByRiderId(Long riderId) {
        return deliveryOrderRepository.findByRiderId(riderId);
    }

    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) {
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

    public DeliveryOrder updateDeliveryOrderStatusToCompleted(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryOrder not found for this id :: " + id));

        deliveryOrder.setStatus("COMPLETED");

        orderService.updateOrderStatus(deliveryOrder.getOrderId(),"COMPLETED");






        return deliveryOrderRepository.save(deliveryOrder);
    }

}
