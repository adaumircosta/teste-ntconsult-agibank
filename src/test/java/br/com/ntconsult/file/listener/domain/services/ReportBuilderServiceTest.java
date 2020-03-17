package br.com.ntconsult.file.listener.domain.services;

import br.com.ntconsult.file.listener.domain.model.line.CalcReport;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReportBuilderServiceTest {

    private ReportBuilderService reportBuilderService;
    private LineBuilderService lineBuilderService;
    private List<String> lineList;

    @Before
    public void setup(){
        reportBuilderService = new ReportBuilderService();
        lineBuilderService = new LineBuilderService();
        createListLines();
    }

    @Test
    public void testCalcReport(){
        CalcReport report = reportBuilderService.dataProcess(lineBuilderService.LineBuilder(lineList));
        assertEquals(11, report.getIdMmostExpensiveSale());
        assertEquals(2, report.getSumCustomer());
        assertEquals(3, report.getSumSaleman());
        assertEquals("Nome: Pedro, CPF: 1234567891234, Salário: R$ 50000", report.getWorstSalesman().toString());
    }

    private void createListLines(){
        lineList = new ArrayList<>();
        lineList.add("001ç1234567891234çPedroç50000");
        lineList.add("001ç3245678865434çPauloç40000.99");
        lineList.add("001ç00104087200çGilbertoç15000.99");
        lineList.add("002ç2345675434544345çJose da SilvaçRural");
        lineList.add("002ç2345675433444345çEduardo PereiraçRural");
        lineList.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        lineList.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        lineList.add("003ç11ç[1-25-1000,2-60-15.45,3-1000-1.50]çPaulo");
    }
}
