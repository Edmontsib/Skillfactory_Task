import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    // создаем удобные средства ввода и вывода
    Scanner in ;
    PrintStream out ;
    ChatServer server;
    public Client(Socket socket, ChatServer server){

        this.socket = socket;
        this.server = server;
        //запускаем поток
        new Thread(this).start();
    }
    void receive(String message){
        out.println(message);
    }
//NoSuchElementException
    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to chat!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAll(input);
                    input = in.nextLine();
            }
            socket.close();
        }
        catch (NoSuchElementException e){
            System.out.println(server.getClients());
            if(!socket.isClosed()){
            System.out.println("Exit chat..."+server.clients);
                if(socket.isInputShutdown()|| socket.isOutputShutdown())     {

                    System.out.println("reading...");
                    System.out.println(Thread.currentThread().getName());
                }
            }
            else{
                System.out.println("Not found...");
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
