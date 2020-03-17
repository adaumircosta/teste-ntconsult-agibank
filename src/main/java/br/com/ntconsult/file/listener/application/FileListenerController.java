package br.com.ntconsult.file.listener.application;

import br.com.ntconsult.file.listener.domain.services.ListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@EnableScheduling
public class FileListenerController {

    private final ListenerService listenerService;

    public FileListenerController(
            final ListenerService listenerService){
        this.listenerService = listenerService;
    }

    @Scheduled(fixedDelay = 1000)
    public void process() {
        listenerService.processFile();
    }

}
