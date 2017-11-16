import java.util.ArrayList;

public class ConcreteSubject implements Subject {
    boolean changed;
    MyData value;
    ArrayList<Observer> observers;

    public ConcreteSubject() {
        observers=new ArrayList<Observer>();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        if (changed) {
            for (Observer o : observers) {
                o.update(value);
            }
        }
    }
    public void giveNewData( Object data){
        value=(MyData)data;
        changed=true;
    }
}
