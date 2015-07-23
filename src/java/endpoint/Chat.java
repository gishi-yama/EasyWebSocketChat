package endpoint;

import java.nio.ByteBuffer;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/Chat")
public class Chat {

  @OnMessage
  public void onMessage(String message, Session onSession) {
    onSession.getOpenSessions()
            .stream()
            .forEach(s -> s.getAsyncRemote().sendText(message));
  }

  @OnMessage
  public void processUpload(ByteBuffer msg, boolean last, Session session) {
    System.out.println("Binary Data");

    while (msg.hasRemaining()) {
      
    }

  }
}
