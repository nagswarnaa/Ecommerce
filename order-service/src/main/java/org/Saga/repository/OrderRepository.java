package org.Saga.repository;

import org.Saga.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
