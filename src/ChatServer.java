
import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
   // private ArrayList<Client> clients = new ArrayList<Client>();
    ServerSocket serverSocket;
    ChatServer() throws IOException{
        // создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket(1234);
    }
    void sendAll(String message){
        for (Client client:clients){
            client.receive(message);
        }
    }
public void run(){
    while(true) {
        System.out.println("Waiting...");

        try {
            // ждем клиента из сети
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            //Client client = new Client(socket);
            // создаем клиента на своей стороне
            clients.add(new Client(socket, this));
            for (int i = 0; i < clients.size(); i++) {
                System.out.println(clients.get(i));
            }
            System.out.println("Client writing channel = "+clients.size());
        //getClients();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

    public static void main(String[] args) throws InterruptedException, IOException {

    new ChatServer().run();

    }
    public String getClients() {
        return Thread.currentThread().getName();
    }
}

