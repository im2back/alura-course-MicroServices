package br.com.alurafood.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurafood.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	Page<OrderItem> findAll(Pageable pageable);
}
