import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DataReceiver extends JPanel {
    private ArrayList<Double> values;        // 保存接收到的数据的容器.


    public DataReceiver(double oprice) {
        values = new ArrayList<Double>();
        values.add(oprice);
    }
    public DataReceiver() {
        values = new ArrayList<Double>();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        double ox=(getWidth()*0.07);
        double oy=getHeight()/2+0.5*ox;

        double dx=(getWidth()-2*ox)/(4*60);//min
        double dy=(getHeight()-oy-ox)/10; //1%;
//坐标轴
        Line2D linei;

        for(int i=-10;i<=10;i++){
            g2d.setPaint(Color.green);
            linei = new Line2D.Double(ox,oy-i*dy,ox+4*60*dx,oy-i*dy);
            g2d.draw(linei);
            g2d.setPaint(Color.red);
            g2d.drawString(i+"%",(float) ox-25,(float)(oy-i*dy));
        }
        g2d.setPaint(Color.green);

        for(int i=0;i<=60*4;i+=30){
            g2d.setPaint(Color.green);
            linei = new Line2D.Double(ox+i*dx,oy+10*dy,ox+i*dx,oy-10*dy);
            g2d.draw(linei);
            g2d.setPaint(Color.BLACK);
            Integer x=9+i/60;

            Integer y=i%60;
            if(i>60*2)x+=2;
            g2d.drawString(String.format("%2d", x).replace(" ", "0")+":"
                    +String.format("%2d", y).replace(" ", "0"),(float)(ox+i*dx)-15,(float)(oy+10*dy+20));
        }
        g2d.setPaint(Color.BLACK);
        Line2D line = new Line2D.Double(ox,oy+10*dy,ox,oy-10*dy);//|
        g2d.draw(line);
        Line2D line2 = new Line2D.Double(ox,oy,ox+4*60*dx,oy);//-
        g2d.draw(line2);


//折线
        g2d.setPaint(Color.BLACK);
        double opPrice=values.get(0);
        for (int i = 1; i < values.size(); ++i) {
            Line2D iline = new Line2D.Double(ox+dx*(i-1),oy-(values.get(i-1)/opPrice-1)*100*dy,ox+dx*i,oy-(values.get(i)/opPrice-1)*100*dy);//|
            g2d.draw(iline);

        }
    }


    public void addValue(double value) {
//        Line2D iline = new Line2D.Double(ox+dx*(i-1),oy+(values.get(i-1)/opPrice-1)*100*dy,ox+dx*i,oy+(values.get(i-1)/opPrice-1)*100*dy);//|
//        g2d.draw(iline);

        values.add(value);
        repaint();
    }
public void clear(){
        values=new ArrayList<>();
    }


    public static void createGuiAndShow() {
        JFrame frame = new JFrame("ConcreteObserverT");

        frame.getContentPane().add(new DataReceiver());

        // Set frame's close operation and location in the screen.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
