package com.springgears.websockets.handler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class GreetingsMessageHandler extends TextWebSocketHandler {

    private List<WebSocketSession> establishedSessions = new CopyOnWriteArrayList<>();

    // Вызывается после установки соединения. Добавляет клиента в общий список.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        establishedSessions.add(session);
    }

    // Вызывается после прерывания соединения. Удаляет клиента из списка.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        establishedSessions.remove(session);
    }

    // Вызывается после получения сообщения. Рассылает его всем подключенным клиентам.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        establishedSessions.forEach(establishedSession
                -> sendMessageToClient(message, establishedSession));
    }

    private void sendMessageToClient(TextMessage message,
                                     WebSocketSession establishedSession) {
        try {
            establishedSession.sendMessage(new TextMessage(message.getPayload()));
        } catch (IOException e) {
            log.error("Failed to send message.", e);
        }
    }
}
