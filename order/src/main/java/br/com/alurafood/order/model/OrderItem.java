package br.com.alurafood.order.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alurafood.order.dto.OrderItemDto;
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
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItem(OrderItemDto dto, Order order) {
		this.quantity = dto.quantity();
		this.description = dto.description();
		this.order = order;	
	}
	
	public OrderItem(OrderItemDto dto) {
		this.quantity = dto.quantity();
		this.description = dto.description();

	}
	
}
