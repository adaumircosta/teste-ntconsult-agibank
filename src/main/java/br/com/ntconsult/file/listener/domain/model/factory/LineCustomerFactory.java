package br.com.ntconsult.file.listener.domain.model.factory;

import br.com.ntconsult.file.listener.domain.model.line.Line;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LineCustomerFactory extends Line {
	
	private String cnpj;
	private String name;
	private String businessArea;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("cnpj: ");
		builder.append(cnpj);
		builder.append(", name: ");
		builder.append(name);
		builder.append(", businessArea: ");
		builder.append(businessArea);
		return builder.toString();
	}

}
