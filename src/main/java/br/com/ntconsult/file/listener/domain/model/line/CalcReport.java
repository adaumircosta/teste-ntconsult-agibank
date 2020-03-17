package br.com.ntconsult.file.listener.domain.model.line;

import br.com.ntconsult.file.listener.domain.model.factory.LineSalesmanFactory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalcReport {

    private long idMmostExpensiveSale;
    private long sumCustomer;
    private long sumSaleman;
    private LineSalesmanFactory worstSalesman;

    public CalcReport(long idMmostExpensiveSale, long sumBuyer, long sumSaleman, Line worstSalesman) {
        super();
        this.idMmostExpensiveSale = idMmostExpensiveSale;
        this.sumCustomer = sumBuyer;
        this.sumSaleman = sumSaleman;
        this.worstSalesman = (LineSalesmanFactory) worstSalesman;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RELATÓRIO:");
        builder.append("\nID da venda mais cara: ");
        builder.append(idMmostExpensiveSale);
        builder.append("\nQuantidade de clientes no arquivo de entrada: ");
        builder.append(sumCustomer);
        builder.append("\nQuantidade de vendedores no arquivo de entrada: ");
        builder.append(sumSaleman);
        builder.append("\nVendedor com menos vendas: ");
        builder.append(worstSalesman.getName());
        return builder.toString();
    }

}