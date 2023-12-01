package br.com.alurafood.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dateHour;
	
	@Column(name = "column_status")
	private Status status; 
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
	private List<OrderItem> itens = new ArrayList<>();

	public Order(LocalDateTime dateHour, Status status, List<OrderItem> itens) {
		super();
		this.dateHour = dateHour;
		this.status = status;
		this.itens = itens;
	}

	

	
}
