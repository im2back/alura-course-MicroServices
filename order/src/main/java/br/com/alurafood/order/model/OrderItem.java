package br.com.alurafood.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;

	private Integer quantity;
	
	private String description;
	
	@ManyToOne(optional = false)
	private Order order;
}
