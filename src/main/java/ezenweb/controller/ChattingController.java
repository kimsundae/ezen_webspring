package ezenweb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component // 스프링 컨테이너에 빈 등록
public class ChattingController extends TextWebSocketHandler {

    // 0. 서버소켓과 연동된 클라이언트 소켓들을 저장하는 리스트
    private static List<WebSocketSession> 접속명단 = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // * 접속 연동 성공 시 클라이언트 소켓의 세션정보를 접속명단 리스트에 저장
        접속명단.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for( WebSocketSession 세션 : 접속명단 ) {
            세션.sendMessage( message );
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("session = " + session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // * 접속 연동이 끊겼을 때 접속명단에서도 제거
        접속명단.remove(session);
    }
}
