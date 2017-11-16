import javax.swing.*;
import java.util.Random;

import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] arg){
        Subject subject=new ConcreteSubject();
        Observer cOT=new ConcreteObserverT(subject);
        Observer cOK=new ConcreteObserverK(subject);

        JFrame frame = new JFrame("ConcreteObserverT");

        frame.getContentPane().add(((ConcreteObserverT)cOT).getDataReceiver());
        // Set frame's close operation and location in the screen.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLocation(150,120);
        frame.setVisible(true);
        JFrame framek = new JFrame("ConcreteObserverk");

        framek.getContentPane().add(((ConcreteObserverK)cOK).getDataReceiver());
        // Set frame's close operation and location in the screen.
        framek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framek.setSize(500, 500);
        framek.setLocationRelativeTo(null);
        framek.setVisible(true);
        framek.setLocation(700,120);
        double oprice=100;
        Random r=new Random();
//        System.out.println(r.nextDouble());

        int startT=10800;
        MyData myData=new MyData(startT,oprice);
        subject.giveNewData(myData);
        subject.notifyObservers();

        double pprice=oprice;
        for (int i=1;i<60*4*15;i++){
            if(i%(60*4)==1){
                oprice=pprice;
//                System.out.println(oprice);
            }

            int t3=i/(60*4);
            int t2=i%(60*4)/60;
            int t1=i%60;
            double a;
            do{
             a= (1+r.nextDouble()*0.2-0.1)*pprice;}
            while(Math.abs(a-oprice)/oprice>=0.1||Math.abs(a-pprice)/oprice>0.01);//

            pprice=a;

            myData=new MyData(startT+t3*10000+t2*100+t1,a);
            subject.giveNewData(myData);
            subject.notifyObservers();

//            System.out.println(a);
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
