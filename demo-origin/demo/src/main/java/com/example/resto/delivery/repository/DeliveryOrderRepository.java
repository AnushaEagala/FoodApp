package com.example.resto.delivery.repository;

import com.example.resto.delivery.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findByRiderId(Long riderId);
    Optional<DeliveryOrder> findByOrderId(Long orderId);
}
