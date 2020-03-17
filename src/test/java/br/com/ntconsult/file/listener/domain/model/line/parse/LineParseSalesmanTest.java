package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesmanFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class LineParseSalesmanTest {

    private LineParseSalesman lineParseSalesman;
    private String[] lineData;

    @Before
    public void setup() {
        lineParseSalesman = new LineParseSalesman();
        lineData = new String[]{"001", "05318939287", "Joãozinho da Sapucai", "50000"};
    }

    @Test
    public void testLineParseCustomer() {
        LineSalesmanFactory lineSalesmanFactory = (LineSalesmanFactory) lineParseSalesman.parse(lineData);
        assertEquals("Joãozinho da Sapucai", lineSalesmanFactory.getName());
        assertEquals("05318939287", lineSalesmanFactory.getCpf());
        assertEquals(new BigDecimal("50000"), lineSalesmanFactory.getSalary());
    }
}
