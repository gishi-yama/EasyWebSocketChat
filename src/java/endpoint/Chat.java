package endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/Chat/{who}")
public class Chat {

  @OnOpen
  public void onMessage(@PathParam("who") String who, Session session) {
    push(who + "が接続しました", session);
  }

  @OnMessage
  public void onMessage(@PathParam("who") String who, String message, Session session) {
    push(who + "：" + message, session);
  }

  @OnClose
  public void onClose(@PathParam("who") String who, Session session) {
    push(who + "が切断しました", session);
  }

  public void push(String message, Session session) {
    session.getOpenSessions()
            .stream()
            .forEach(s -> s.getAsyncRemote().sendText(message));
  }
}
