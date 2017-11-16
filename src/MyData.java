public class MyData {
    int time;//010800
    double price;

    public MyData() {
    }

    public MyData(int time, double price) {
        this.time = time;
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
