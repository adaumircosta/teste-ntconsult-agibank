/**
 * 
 */
package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.services.Line.LineParse;
import org.springframework.stereotype.Component;

@Component
public class LineParseBuyer implements LineParse {

	@Override
	public Line parse(String[] data) {
		String cnpj = data[FileConstant.PLACE_BUYER_CNPJ];
		String name = data[FileConstant.PLACE_BUYER_NAME];
		String businessArea = data[FileConstant.PLACE_BUYER_BUSINESS_AREA];
		
		return new LineCustomerFactory(cnpj, name, businessArea);
		
	}

}
