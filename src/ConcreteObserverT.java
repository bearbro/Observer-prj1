import java.util.ArrayList;

public class ConcreteObserverT implements Observer {
    Subject subject;
    double[] prices;
    int nub;
    DataReceiver dataReceiver;

    @Override
    public void update(Object newdata) {
        MyData o = (MyData) newdata;
        int x = o.getTime();
        //time 010800
//        System.out.println(nub);
        if(nub==60*4){ //新一天
            prices = new double[60 * 4];
            nub=0;
            dataReceiver.clear();
        }

        int y = x % 100 + (x % 10000 / 100 - 8) * 60 ;
        if (prices[y] == 0) nub++;
        prices[y] = o.getPrice();
        dataReceiver.addValue(prices[y]);
    }

    public DataReceiver getDataReceiver() {
        return dataReceiver;
    }


    public ConcreteObserverT(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
        prices = new double[60 * 4];
        nub = 0;
        dataReceiver = new DataReceiver();
    }

    public ArrayList<Double> getPrices() {
        ArrayList<Double> priceList = new ArrayList<Double>();
        for (int i = 0; i < nub; i++) {
            priceList.add(prices[i]);
        }
        return priceList;
    }

}

