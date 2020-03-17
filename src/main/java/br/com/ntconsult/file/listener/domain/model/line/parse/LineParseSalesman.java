package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesmanFactory;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.services.line.LineParse;
import org.springframework.stereotype.Component;

@Component
public class LineParseSalesman implements LineParse {

    @Override
    public Line parse(String[] data) {
        String cpf = data[FileConstant.PLACE_SALESMAN_CPF];
        String name = data[FileConstant.PLACE_SALESMAN_NAME];
        String salary = data[FileConstant.PLACE_SALESMAN_SALARY];

        return new LineSalesmanFactory(cpf, name, salary);
    }

}
