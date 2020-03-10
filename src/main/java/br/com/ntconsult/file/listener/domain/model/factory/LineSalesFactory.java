package br.com.ntconsult.file.listener.domain.model.factory;

import br.com.ntconsult.file.listener.domain.model.Item;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
public class LineSalesFactory extends Line {

	private long idSale;
	private List<Item> items;
	private String salesman;
	private BigDecimal priceBetterSale;

	public LineSalesFactory(String idSale, List<Item> items, String salesman) {
		super();
		this.idSale = Long.parseLong(idSale);
		this.items = items;
		this.salesman = salesman;
		this.priceBetterSale = this.getCost();
	}

	public final BigDecimal getCost() {
		return Collections.max(this.items, Comparator.comparing(Item::getCost)).getCost();
	}

}
