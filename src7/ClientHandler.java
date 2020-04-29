import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            }
// Проверка наличия /w  , получение ника адресата и отправка сообщения адресату через метод sendMsgToNick(String nick, String msg)
// Проверка неправильно введеного ника.

            String msgToNick = strFromClient.substring(0,3);
            if (msgToNick.equals("/w ")){
                String nickMsg = "";
                String MsgNickMsg = "";
                for(int i = 4; i <strFromClient.length(); i++) {
                    if (strFromClient.substring(i, i+1).equals((" "))){
                        nickMsg = strFromClient.substring(3, i);
                        MsgNickMsg = strFromClient.substring(i+1, strFromClient.length());
                        break;
                    }
                }
                if (nickMsg.equals("")){
                    myServer.sendMsgToNick(name, "Неправильный ник");
                }else {
                    myServer.sendMsgToNick(nickMsg, name + ": " + MsgNickMsg);
                    myServer.sendMsgToNick(name, name + ": " + MsgNickMsg);
                }
            }else{
                myServer.broadcastMsg(name + ": " + strFromClient);
            }

        }
    }



    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
