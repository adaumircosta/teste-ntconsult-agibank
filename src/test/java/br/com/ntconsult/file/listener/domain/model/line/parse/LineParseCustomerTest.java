package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineParseCustomerTest {

    private LineParseCustomer lineParseCustomer;
    private String[] lineData;

    @Before
    public void setup(){
        lineParseCustomer = new LineParseCustomer();
        lineData = new String[]{"002", "2345675434544345", "Jose da Silva", "Rural"};
    }

    @Test
    public void testLineParseCustomer(){
        LineCustomerFactory lineCustomerFactory = (LineCustomerFactory) lineParseCustomer.parse(lineData);
        assertEquals("2345675434544345", lineCustomerFactory.getCnpj());
        assertEquals("Jose da Silva", lineCustomerFactory.getName());
        assertEquals("Rural", lineCustomerFactory.getBusinessArea());
    }
}
