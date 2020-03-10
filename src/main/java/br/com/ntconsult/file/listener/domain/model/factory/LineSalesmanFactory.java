package br.com.ntconsult.file.listener.domain.model.factory;

import br.com.ntconsult.file.listener.domain.model.line.Line;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LineSalesmanFactory extends Line {

	private String cpf;
	private String name;
	private BigDecimal salary;

	public LineSalesmanFactory(String cpf, String name, String salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = new BigDecimal(salary);
	}
	
	@Override
	public String toString() {
		return "Nome: " + name + ", CPF: " + cpf + ", Salário: R$ " + salary.toString().replace(".", ",");
	}

}
