import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class ConsoleClock extends Thread{
    @Override
    public void run() {
        System.out.println("The clock was start");
        while (!isInterrupted()) {
            Calendar myCalendar = new GregorianCalendar();
            DateFormat tf = new SimpleDateFormat("HH:mm:ss");
            System.out.println(tf.format(myCalendar.getTime()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("The clock was stopped");
    }
}

