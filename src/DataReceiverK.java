import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DataReceiverK extends JPanel {
    private ArrayList<MyData2> values;        // 保存接收到的数据的容器.


    public DataReceiverK(MyData2 oprice) {
        values = new ArrayList<>();
        values.add(oprice);
    }

    public DataReceiverK() {
        values = new ArrayList<>();
    }

    public void clear() {
        values = new ArrayList<>();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        double ox = (getWidth() * 0.07);
        double oy = getHeight() - ox;


        double yMax = 200;
        double yMin = 50;
        double dx = (getWidth() - 3 * ox) / (30);//30个数据
        double dy = (oy -  ox) / (yMax - yMin); //k
        double dr = dx / 3;
//坐标轴


        Line2D linei;
        for(int i=0;i<=10;i++){
            g2d.setPaint(Color.green);
            linei = new Line2D.Double(ox,oy-i* (yMax - yMin)/10*dy,ox+30*dx,oy-i* (yMax - yMin)/10*dy);
            g2d.draw(linei);
            g2d.setPaint(Color.BLACK);
            g2d.drawString(String.valueOf((int)(i*(yMax - yMin)/10+yMin)),(float) ox-25,(float)(oy-i* (yMax - yMin)/10*dy));
        }
        g2d.setPaint(Color.green);

        for(int i=0;i<=30;i++){
            g2d.setPaint(Color.BLACK);


        }
        g2d.setPaint(Color.BLACK);

        Line2D line = new Line2D.Double(ox, oy, ox, oy - (yMax - yMin)* dy);//|
        g2d.draw(line);
        Line2D line2 = new Line2D.Double(ox, oy, ox + 30* dx, oy);//-
        g2d.draw(line2);
//框
        double maxPrice;
        double minPrice;
        double opPrice;
        double clPrice;
        for (int i = 0; i < values.size(); i++) {
            maxPrice = values.get(i).getMaxPrice();
            minPrice = values.get(i).getMinPrice();
            opPrice = values.get(i).getOpPrice();
            clPrice = values.get(i).getClPrice();
            if (i == values.size() - 1) {
//                System.out.print(maxPrice + " " + minPrice + " " + opPrice + " " + clPrice+"..");
//                System.out.println(opPrice+" ");
//                System.out.println((maxPrice-opPrice)/opPrice+" "+(minPrice-opPrice)/opPrice);
            }

            Line2D line1 = new Line2D.Double(ox + (i + 1) * dx, oy - dy * (maxPrice - yMin), ox + (i + 1) * dx, oy - dy * (minPrice - yMin));//|
            Rectangle2D rect;
            if (opPrice >= clPrice) {
                rect = new Rectangle2D.Double((int)(ox + (i + 1) * dx - dr), (int)(oy - dy * (opPrice - yMin)), (int)(2 * dr), (int)(dy * Math.abs(opPrice - clPrice)));//声明并创建矩形对象，矩形的左上角是(20，30)，宽是300，高是40
                g2d.setColor(new Color(255, 0, 0));
                g2d.setPaint(Color.RED);
                g2d.draw(line1);
                g2d.draw(rect);
                g2d.fillRect((int)(ox + (i + 1) * dx - dr), (int)(oy - dy * (opPrice - yMin)), (int)(2 * dr), (int)(dy * Math.abs(opPrice - clPrice)));
            } else {
                rect = new Rectangle2D.Double((int)(ox + (i + 1) * dx - dr), (int)(oy - dy * (clPrice - yMin)), (int)(2 * dr), (int)(dy * Math.abs(opPrice - clPrice)));//声明并创建矩形对象，矩形的左上角是(20，30)，宽是300，高是40

                g2d.setColor(new Color(0, 0, 255));
                g2d.fillRect((int)(ox + (i + 1) * dx - dr), (int)(oy - dy * (clPrice - yMin)), (int)(2 * dr), (int)(dy * Math.abs(opPrice - clPrice)));
                g2d.setPaint(Color.BLUE);
                g2d.draw(line1);
                g2d.draw(rect);
            }
            g2d.drawString(String.format("%1d", i+1).replace(" ", "0")
                    ,(float)(ox+(i+1)*dx),(float)(oy+25));
        }

    }


    public void addValue(MyData2 value) {
//        Line2D iline = new Line2D.Double(ox+dx*(i-1),oy+(values.get(i-1)/opPrice-1)*100*dy,ox+dx*i,oy+(values.get(i-1)/opPrice-1)*100*dy);//|
//        g2d.draw(iline);

        values.add(value);
        repaint();
    }


    public static void createGuiAndShow() {
        JFrame frame = new JFrame("ConcreteObserverK");

        frame.getContentPane().add(new DataReceiverK());

        // Set frame's close operation and location in the screen.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
