package br.com.alurafood.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.alurafood.order.model.Order;
import br.com.alurafood.order.model.Status;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@org.springframework.transaction.annotation.Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status = :status where o = :order")
    void updateStatus(Status status, Order order);
	
	
	  @Query(value = "SELECT o from Order o LEFT JOIN FETCH o.itens where o.id = :id")
	   Order findByIdAddItens(Long id);

}
