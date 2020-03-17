package br.com.ntconsult.file.listener.domain.services.file;

import br.com.ntconsult.file.listener.domain.constants.FileConstant;
import br.com.ntconsult.file.listener.domain.model.line.CalcReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FileService {

    private void createDirectory(Path path) throws IOException {
        Files.createDirectories(path);
    }

    private void filePersist(Path path, String data) {
        try {
            Files.write(path, Arrays.asList(data));
        } catch (IOException e) {
            log.info("Erro ao persistir arquivo no disco local: {}", path);
        }
    }

    public List<String> getLinesFile(String path) throws IOException {
        //Testa se o arquivo pode ser lido, se o arquivo existe e se a Java virtual machine
        //tem privilégios necessários para abrir e ler o mesmo.
        if (Files.isReadable(Paths.get(path).toAbsolutePath())) {
            return Files.readAllLines(Paths.get(path));
        } else {
            log.info("Não foi possivel ler o arquivo do diretótio: {}", path);
        }
        return null;
    }

    public void buildPath(Path in, Path out) throws IOException {
        createDirectory(in);
        createDirectory(out);
    }

    public void buildReport(File file, CalcReport report) {
        filePersist(
                FileConstant.PATH_OUT
                        .resolve(file.getName().replace(FileConstant.EXTENSION_DAT, FileConstant.EXTENSION_DONE_DAT)),
                report.toString());
    }


}
