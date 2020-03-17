/**
 * 
 */
package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.services.line.LineParse;
import org.springframework.stereotype.Component;

@Component
public class LineParseCustomer implements LineParse {

	@Override
	public Line parse(String[] data) {
		String cnpj = data[FileConstant.PLACE_CUSTOMER_CNPJ];
		String name = data[FileConstant.PLACE_CUSTOMER_NAME];
		String businessArea = data[FileConstant.PLACE_CUSTOMER_BUSINESS_AREA];
		
		return new LineCustomerFactory(cnpj, name, businessArea);
		
	}

}
