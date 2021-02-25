package com.ws.person.websocket;

import com.ws.person.websocket.repository.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @NonNull
    private final PersonRepository repository;

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        this.sessions.add(session);
        System.out.println("Connection established from " + session.toString() +
                " @ " + Instant.now().toString());
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session,
                                     final TextMessage message) throws Exception {
        try {
            System.out.println("Message received: '" +
                    message + "', from " + session.toString());

            for (final WebSocketSession sessionInList : this.sessions) {
                if (sessionInList != session) {
                    sessionInList.sendMessage(message);
                    System.out.println("--> Sending message '"
                            + message + "' to " + sessionInList.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception handling message: " +
                    e.getLocalizedMessage());
        }
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println("Connection closed by " + session.toString() +
                " @ " + Instant.now().toString());
    }

}
