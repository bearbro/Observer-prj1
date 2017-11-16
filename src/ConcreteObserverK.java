import java.util.ArrayList;

public class ConcreteObserverK implements Observer {
    Subject subject;
    DataReceiverK dataReceiverk;
    ArrayList<Double> maxPrice;
    ArrayList<Double> minPrice;
    ArrayList<Double> opPrice;
    ArrayList<Double> clPrice;
    ArrayList<Integer> time;

    @Override
    public void update(Object newdata) {
        MyData o = (MyData) newdata;
        int x = o.getTime();
        //time 010800
        int y = x % 100 + (x % 10000 / 100 - 8) * 60;
        int ntime = x / 10000;
        double price = o.getPrice();
        int it = time.indexOf(ntime);
        if (it == -1) {
            time.add(ntime);
            maxPrice.add(price);
            minPrice.add(price);
            opPrice.add(price);


        } else {
            if (minPrice.get(it) > price) {
                minPrice.set(it, price);
            }
            if (maxPrice.get(it) < price) {
                maxPrice.set(it, price);
            }
        }
//        System.out.println(x);
        if (x % 10000 == 1159) {
            clPrice.add(price);
            int i = maxPrice.size() - 1;
            dataReceiverk.addValue(new MyData2(maxPrice.get(i),
                    minPrice.get(i), opPrice.get(i), clPrice.get(i), time.get(i)));
        }
    }

    public ConcreteObserverK(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
        maxPrice = new ArrayList<Double>();
        minPrice = new ArrayList<Double>();
        opPrice = new ArrayList<Double>();
        clPrice = new ArrayList<Double>();
        time = new ArrayList<>();
        dataReceiverk = new DataReceiverK();
    }

    public DataReceiverK getDataReceiver() {
        return dataReceiverk;
    }

    public ArrayList<MyData2> getPrices() {
        ArrayList<MyData2> prices = new ArrayList<MyData2>();
        for (int i = 0; i < time.size(); i++) {
            prices.add(new MyData2(maxPrice.get(i),
                    minPrice.get(i), opPrice.get(i), clPrice.get(i), time.get(i)));
        }
        return prices;
    }
}

