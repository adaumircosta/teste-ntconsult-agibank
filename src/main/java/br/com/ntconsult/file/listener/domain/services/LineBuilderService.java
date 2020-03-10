package br.com.ntconsult.file.listener.domain.services;

import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.model.line.LineTypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LineBuilderService {

    private static final int PLACE_BEGIN_ID = 0;
    private static final int PLACE_END_ID = 3;

    public List<Line> LineBuilder(List<String> lines) {
        List<Line> linesParse = new ArrayList<>();
        lines.forEach(s -> linesParse.add(lineParser(s)));

        return linesParse;
    }

    public Line lineParser(String line) {
        return (Line) LineTypeEnum.findById(line.substring(PLACE_BEGIN_ID, PLACE_END_ID)).getConverter().parse(line.split("ç"));
    }
}
