package com.mydemo.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * webSocket
 * @author 1
 * @description 此处为jeecgboot项目代码 待完善
 *
 * <a>
 *     关于各种操作参数可参考 javax.websocket.Endpoint
 * </a>
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocket {

    private Session session;
    private String userId;


    /**
     * 作用未知，感觉无用
     * 另外 onOpen中set.add()感觉有问题，待排查
     */
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    /**
     * session集合
     */
    private static Map<String, Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        try {
            System.out.println(webSockets.size());
            System.out.println(this);
            a: {
            // 原代码

                this.session = session;
                this.userId = userId;
                webSockets.add(this);
            }


//            b: {
//            // 自己代码
//                WebSocket webSocket = new WebSocket(session, userId);
//                webSockets.add(webSocket);
//            }
            System.out.println(webSockets.size());
            System.out.println(this);

            sessionPool.put(userId, session);
            log.info("%s链接成功", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam(value = "userId") String userId, CloseReason reason) {
        try {
            // 若onOpen验证成功，此处需删除
            webSockets.remove(this);
            sessionPool.remove(userId);
            log.info("%s断开连接", userId);
            log.info(reason.getReasonPhrase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, @PathParam(value = "userId") String userId, String message) {
        Session ownedSession = sessionPool.get(userId);
        if (session == ownedSession) {
            System.out.println(userId + ":" + message);
        } else {
            log.error("消息错误, %s:%s", userId, message);
        }
    }

    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable throwable) {
        log.error("连接错误, %s:%s", userId, throwable.getMessage());
    }

    /**
     * 推送单人消息
     * @param session
     * @param message
     */
    public void pushSingleMessage(Session session, String message) {
        session.getAsyncRemote().sendText(message);
    }

    /**
     * 推送多人消息
     * @param collection
     * @param message
     */
    public void pushBunchMessage(Collection<Session> collection, String message) {
        collection.forEach(session -> pushSingleMessage(session, message));
    }
}
