public interface Subject {
    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void notifyObservers();
    public void giveNewData(Object o);
}
