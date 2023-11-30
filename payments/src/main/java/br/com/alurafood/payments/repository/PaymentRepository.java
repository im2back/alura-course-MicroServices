package br.com.alurafood.payments.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alurafood.payments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

	Page<Payment> findAll(Pageable pageable);
	
}
