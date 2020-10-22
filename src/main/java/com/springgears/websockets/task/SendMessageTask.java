package com.springgears.websockets.task;

import com.springgears.websockets.handler.GreetingsMessageHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SendMessageTask implements ApplicationListener<ContextRefreshedEvent> {

    private GreetingsMessageHandler greetingsMessageHandler;

    @Async
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        while(true) {
            try {
                Thread.sleep(2000);
                greetingsMessageHandler.sendToAll("What's up?");
            } catch (InterruptedException ignored) {}
        }
    }
}
