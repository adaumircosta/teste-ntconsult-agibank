package br.com.ntconsult.file.listener.domain.services;

import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesmanFactory;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LineBuilderServiceTest {

    private String lineSalesman;
    private String lineCustomer;
    private String lineSales;
    private Line lineParserSalesman;
    private Line lineParseCustomer;
    private Line lineParseSales;
    private String toStringSalesman;
    private String toStringCustomer;
    private String toStringSales;


    private LineBuilderService lineBuilderService;

    @Before
    public void setup() {
        lineSalesman = "001ç00104087200çCarlosç500";
        lineCustomer = "002ç05504878000188çVan HelsingçRural";
        lineSales = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        toStringSalesman = "Nome: Carlos, CPF: 00104087200, Salário: R$ 500";
        toStringCustomer = "cnpj: 05504878000188, name: Van Helsing, businessArea: Rural";
        toStringSales = "LineSalesFactory(idSale=10, items=[Item(idItem=1, quantity=10, price=100), Item(idItem=2, " +
                "quantity=30, price=2.50), Item(idItem=3, quantity=40, price=3.10)], salesman=Pedro, " +
                "priceBetterSale=1000)";
        lineBuilderService = new LineBuilderService();
    }

    @Test
    public void testTypeInstanceSalesmanAndData() {
        lineParserSalesman = lineBuilderService.lineParser(lineSalesman);
        assertEquals(LineSalesmanFactory.class, lineParserSalesman.getClass());
        assertEquals(toStringSalesman, lineParserSalesman.toString());
    }

    @Test
    public void testTypeInstanceCustomer() {
        lineParseCustomer = lineBuilderService.lineParser(lineCustomer);
        assertEquals(LineCustomerFactory.class, lineParseCustomer.getClass());
        assertEquals(toStringCustomer, lineParseCustomer.toString());
    }

    @Test
    public void testTypeInstanceSales() {
        lineParseSales = lineBuilderService.lineParser(lineSales);
        assertEquals(LineSalesFactory.class, lineParseSales.getClass());
        assertEquals(toStringSales, lineParseSales.toString());
    }


}
