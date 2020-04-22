import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {  //Создаем новый поток, для приема сообщений с консоли сервера и отправки их клиенту
                @Override
                public void run() {
                    try {
                        while (true) {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                            String serverMsg = reader.readLine();
                            out.writeUTF("Сервер: " + serverMsg);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();}
                }
            }).start();


            while (true) {
                String str = in.readUTF();
                    if (str.equals("/end")) {
                        break;
                    }
                System.out.println("Клиент: " + str);    //Выводим текст, написанный клиентом в консоль сервера
                out.writeUTF("Клиент: " + str);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
