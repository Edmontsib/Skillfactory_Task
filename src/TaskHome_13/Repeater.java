package TaskHome_13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Repeater {
    static final List<String> buffer = Collections.synchronizedList(new ArrayList<>());
    static final List<String> log = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) throws InterruptedException {
        new Operator().start();
        new MachineLog().start();
    }
    static class Operator extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true){
                synchronized (buffer) {
                    buffer.add(scanner.nextLine());
                    buffer.notify();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    static class MachineLog extends Thread {
        @Override
        public void run() {
            while(buffer.isEmpty()){
                synchronized (buffer){
                    try {
                        buffer.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    log.add(buffer.get(0));
                    if(buffer.get(0).equals("exit")|buffer.get(0).equals("quit")){
                        System.out.println("Last:  "+buffer.remove(0));
                        System.out.println("Log write: " + log);
                        System.exit(0);
                    }
                    System.out.println("Last:  "+buffer.remove(0));
                    System.out.println("Log write: " + log);

                }
            }
        }
    }
}
