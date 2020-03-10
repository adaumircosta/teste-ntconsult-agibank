package br.com.ntconsult.file.listener.domain.services;

import br.com.ntconsult.file.listener.domain.model.factory.LineCustomerFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesFactory;
import br.com.ntconsult.file.listener.domain.model.factory.LineSalesmanFactory;
import br.com.ntconsult.file.listener.domain.model.line.CalcReport;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportBuilderService {

    public CalcReport dataProcess(List<Line> lineParsed) {
        long sumBuyers = sumBuyers(lineParsed);
        long sumSalesman = sumSalesman(lineParsed);
        long idMmostExpensiveSale = idMmostExpensiveSale(lineParsed);
        Line worstSalesman = worstSalesman(lineParsed);
        return new CalcReport(idMmostExpensiveSale, sumBuyers, sumSalesman, worstSalesman);
    }

    private long sumSalesman(List<Line> lineParsed) {
        return lineParsed.stream().filter(LineSalesmanFactory.class::isInstance).count();
    }

    private long sumBuyers(List<Line> lineParsed) {
        return lineParsed.stream().filter(LineCustomerFactory.class::isInstance).count();
    }

    private long idMmostExpensiveSale(List<Line> lineParsed) {
        List<LineSalesFactory> sales = getSales(lineParsed);

        if (sales.isEmpty()) return 0l;

        return Collections.max(sales, Comparator.comparing(LineSalesFactory::getCost)).getIdSale();
    }

    private List<LineSalesFactory> getSales(List<Line> lineParsed) {
        return lineParsed.stream().filter(obj -> {
            return LineSalesFactory.class.isInstance(obj);
        }).map(x -> (LineSalesFactory) x).collect(Collectors.toList());
    }

    private Line worstSalesman(List<Line> linesParsed) {

        List<LineSalesmanFactory> salesmans = getSalesman(linesParsed);
        List<LineSalesFactory> sales = getSales(linesParsed);
        Map<LineSalesmanFactory, BigDecimal> salesBySalesman = new HashMap<>();

        sales.forEach((lineSalesFactory) -> {
            salesmans.stream().filter((salesman) -> (lineSalesFactory.getSalesman().equals(salesman.getName())))
                    .forEachOrdered((salesman) -> {
                        salesBySalesman.put(salesman, lineSalesFactory.getCost());
                    });
        });

        return Collections.min(salesBySalesman.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
    }

    private List<LineSalesmanFactory> getSalesman(List<Line> linesParsed) {
        return linesParsed.stream().filter(LineSalesmanFactory.class::isInstance).map(x -> (LineSalesmanFactory) x).collect(Collectors.toList());
    }

}
