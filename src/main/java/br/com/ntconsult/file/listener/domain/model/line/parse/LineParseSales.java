/**
 * 
 */
package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.Item;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesFactory;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.services.line.LineParse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LineParseSales implements LineParse {

	@Override
	public Line parse(String[] data) {
		String saleman = data[FileConstant.PLACE_SALE_SALESMAN];
		String idSale = data[FileConstant.PLACE_SALE_ID];
		String itens = data[FileConstant.PLACE_SALE_ITENS].replace("[", "").replace("]", "");
		List<String> itensSplit = Arrays.asList(itens.split(","));

		ArrayList<Item> itemList = new ArrayList<>();

		itensSplit.stream().map((string) -> string.split("-"))
				.map((stringSplit) -> 
				new Item(stringSplit[FileConstant.PLACE_SALE_ITEM_ID],
						stringSplit[FileConstant.PLACE_SALE_ITEM_QUANTITY],
						new BigDecimal(stringSplit[FileConstant.PLACE_SALE_ITEM_PRICE])))
				.forEachOrdered((item) -> {
					itemList.add(item);
				});

		return new LineSalesFactory(idSale, itemList, saleman);
	}

}
