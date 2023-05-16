package sseTest.demo.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        //sempre que uma conexão nova é realizada isso roda
        System.out.println("inicio id sessao: " + session.getId());
        //super.afterConnectionEstablished(session);

        //inproviso de loop
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    session.sendMessage(new TextMessage("OLA MALANDRO: Toma um random: " + UUID.randomUUID()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },2000L,2000L);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        //Quando recebemos mensagem
        System.out.println("Sessao: "+ session.getId() +"mensagem: " + message.getPayload());
        //super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        //Quando a conexão é fechada
        System.out.println("fim id sessao: " + session.getId());
        //super.afterConnectionClosed(session, status);
    }
}
