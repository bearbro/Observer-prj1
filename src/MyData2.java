public class MyData2 {
    Double maxPrice;
    Double minPrice;
    Double opPrice;
    Double clPrice;
    Integer time;

    public MyData2() {
    }

    public MyData2(Double maxPrice, Double minPrice, Double opPrice, Double clPrice, Integer time) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.opPrice = opPrice;
        this.clPrice = clPrice;
        this.time = time;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getOpPrice() {
        return opPrice;
    }

    public void setOpPrice(Double opPrice) {
        this.opPrice = opPrice;
    }

    public Double getClPrice() {
        return clPrice;
    }

    public void setClPrice(Double clPrice) {
        this.clPrice = clPrice;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
