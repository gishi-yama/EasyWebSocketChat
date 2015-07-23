package endpoint;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;
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
  public void processUpload(ByteBuffer bytes, boolean last, Session onSession) {
    System.out.println("Binary");
//    onMessage(Base64.encode(bytes.array()), onSession);
    onSession.getOpenSessions()
            .stream()
            .forEach(s -> s.getAsyncRemote().sendBinary(bytes));
  }
}
