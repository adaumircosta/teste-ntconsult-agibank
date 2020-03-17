package br.com.ntconsult.file.listener.domain.services.line;

import br.com.ntconsult.file.listener.domain.model.line.Line;

public interface LineParse {

    public abstract Line parse(String[] data);
}
