package br.com.ntconsult.file.listener.domain.services;
;
import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.line.CalcReport;
import br.com.ntconsult.file.listener.domain.model.line.Line;
import br.com.ntconsult.file.listener.domain.services.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
@Slf4j
public class ListenerService {

    private final FileService fileService;
    private final LineBuilderService lineBuilder;
    private final ReportBuilderService reportBuilder;

    public ListenerService(
            final FileService fileService,
            final LineBuilderService lineBuilder,
            final ReportBuilderService reportBuilder){
        this.fileService = fileService;
        this.lineBuilder = lineBuilder;
        this.reportBuilder = reportBuilder;
    }

    public void processFile(){
        log.info("Aplicação iniciada!");
        try {
            //Cria os diretórios
            fileService.buildPath(FileConstant.PATH_IN, FileConstant.PATH_OUT);
        } catch (IOException exe) {
            log.error("Erro ao criar diretório local: {}", exe.getMessage());
            return;
        }

        log.info("Escutando diretório: {}, adicione algum arquivo para a aplicação iniciar o processamento.", FileConstant.PATH_IN.toString());

        FileSystem fs = FileSystems.getDefault();
        WatchService ws;

        try {
            // Cria o whatcher escutando eventos entry_create = criação de arquivo
            ws = fs.newWatchService();
            FileConstant.PATH_IN.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            log.error("Erro ao acessar diretorio local: {} --> {}", FileConstant.PATH_IN.toString(), e.getMessage());
            return;
        }

        // Implementação do loop infinito para aguardar eventos de entrada
        for (;;) {
            log.info("Aguardando arquivo ser adicionado para novo processamento...");
            // espera até o evento ocorrer
            WatchKey key;
            try {
                key = ws.take();
            } catch (InterruptedException exe) {
                log.error("Erro inesperado: {}", exe.getMessage());
                return;
            }

            // Varre o poll de eventos disparados
            for (WatchEvent<?> object : key.pollEvents()) {
                // Arquivo foi adicionado ao diretório
                if (object.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    // Caso o arquivo tenha extensão *.dat entra para o processamento
                    if (object.context().toString().endsWith(FileConstant.EXTENSION_DAT)) {
                        log.info("Arquivo com extensao .dat adicionado ao diretório de escuta, iniciando processamento...");
                        try {
                            List<String> lines = fileService
                                    .getLinesFile(FileConstant.PATH_IN.toString() + "//" + object.context().toString());
                            List<Line> linesParsed = lineBuilder.LineBuilder(lines);
                            CalcReport report = reportBuilder.dataProcess(linesParsed);
                            File file = new File(FileConstant.PATH_OUT.toString() + "//" + object.context().toString());
                            log.info("Gerando relatório em: {}", FileConstant.PATH_OUT.toString());
                            fileService.buildReport(file, report);
                            log.info("Relatório gerado com sucesso.");
                        } catch (IOException exe) {
                            log.error("Ocorreu erro ao ler arquivo: {}", exe.getMessage());
                            return;
                        }
                    }
                }
            }

            // Redefinindo a key -- Esse passo é crucial para continuar capturando eventos.
            // Se a chave não for mais válida o diretório esta inacessivel, então saia do
            // loop.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
        log.info("Aplicação finalizada! Diretório não esta mais acessivel.");
    }
    }
