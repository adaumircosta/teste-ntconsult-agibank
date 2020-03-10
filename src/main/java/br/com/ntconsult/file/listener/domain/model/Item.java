package br.com.ntconsult.file.listener.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Item {

	private Long idItem;
	private Integer quantity;
	private BigDecimal price;

	public Item(String idItem, String quantity, BigDecimal price) {
		super();
		this.idItem = Long.parseLong(idItem);
		this.quantity = Integer.parseInt(quantity);
		this.price = price;
	}

	public BigDecimal getCost() {
		return this.price.multiply(new BigDecimal(this.quantity));
	}

}
