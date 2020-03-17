package br.com.ntconsult.file.listener.domain.model.line;

import br.com.ntconsult.file.listener.domain.model.line.parse.LineParseCustomer;
import br.com.ntconsult.file.listener.domain.model.line.parse.LineParseSales;
import br.com.ntconsult.file.listener.domain.model.line.parse.LineParseSalesman;
import br.com.ntconsult.file.listener.domain.services.line.LineParse;

import java.util.Arrays;
import java.util.List;

public enum LineTypeEnum {

    SALEMAN("001", new LineParseSalesman()),
    BUYER("002", new LineParseCustomer()),
    SALE("003", new LineParseSales());

    private final String lineId;
    private final LineParse parse;

    private LineTypeEnum(String lineId, LineParse parse) {
        this.lineId = lineId;
        this.parse = parse;
    }

    public LineParse getConverter() {
        return parse;
    }

    public static LineTypeEnum findById(String id) {
        List<LineTypeEnum> kind = Arrays.asList(values());
        for (LineTypeEnum lineTypeEnum : kind) {
            if (lineTypeEnum.lineId.equals(id)) {
                return lineTypeEnum;
            }
        }
        return null;
    }

}
