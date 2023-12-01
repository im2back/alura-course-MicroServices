package br.com.alurafood.order.model;

import br.com.alurafood.order.dto.OrderItemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tb_order_item")
public class OrderItem {
	
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;

	private Integer quantity;
	
	@Column(name= "column_description")
	private String description;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItem(OrderItemDto dto, Order order) {
		this.quantity = dto.quantity();
		this.description = dto.description();
		this.order = order;	
	}
	
	
}
