package io.freefair.dah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduleTask {
    private final SimpMessagingTemplate template;

    @Autowired
    public ScheduleTask(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRate = 5000)
    public void trigger() {
        this.template.convertAndSend("/topic/message", "Date: " + new Date());
    }
}
