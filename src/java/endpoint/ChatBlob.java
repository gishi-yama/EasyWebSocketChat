package endpoint;

import java.nio.ByteBuffer;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatBlob/{who}")
public class ChatBlob extends Chat {

  @OnMessage
  public void processUpload(@PathParam("who") String who, ByteBuffer bytes, boolean last, Session session) {
    System.out.println("Binary");
    session.getOpenSessions()
            .stream()
            .forEach(s -> s.getAsyncRemote().sendBinary(bytes));
  }
}
