package br.com.ntconsult.file.listener.domain.model.line.parse;

import br.com.ntconsult.file.listener.domain.model.Item;
import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LineParseSalesTest {

    private LineParseSales lineParseSales;
    private String[] lineData;
    private List<Item> items;

    @Before
    public void setup(){
        lineParseSales = new LineParseSales();
        lineData = new String[]{"003", "10", "[1-10-100,2-30-2.50]", "Pedro"};
        items = new ArrayList<>();
        addListItem();
    }

    @Test
    public void testLineParseSales(){
        LineSalesFactory lineSalesFactory = (LineSalesFactory) lineParseSales.parse(lineData);
        assertEquals(10, lineSalesFactory.getIdSale());
        assertEquals("Pedro", lineSalesFactory.getSalesman());
        assertEquals(items, lineSalesFactory.getItems());
    }

    private void addListItem(){

        Item item1= new Item("1", "10", new BigDecimal("100"));
        Item item2 = new Item("2", "30", new BigDecimal("2.50"));
        items.add(item1);
        items.add(item2);
    }
}
