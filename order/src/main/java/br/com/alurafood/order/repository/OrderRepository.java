package br.com.alurafood.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurafood.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	Page<Order> findAll(Pageable pageable);
}
