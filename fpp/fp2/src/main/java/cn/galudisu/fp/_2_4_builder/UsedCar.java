package cn.galudisu.fp._2_4_builder;

/**
 * Java实现的构建模式
 *
 * @author galudisu
 */
public class UsedCar {

    private String make;
    private String model;
    private int kmDriven;
    private int yearOfManufacturing;

    private boolean hasGps;
    private boolean hasAc;
    private boolean hasAirBags;
    private boolean hasAbs;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public boolean isHasGps() {
        return hasGps;
    }

    public void setHasGps(boolean hasGps) {
        this.hasGps = hasGps;
    }

    public boolean isHasAc() {
        return hasAc;
    }

    public void setHasAc(boolean hasAc) {
        this.hasAc = hasAc;
    }

    public boolean isHasAirBags() {
        return hasAirBags;
    }

    public void setHasAirBags(boolean hasAirBags) {
        this.hasAirBags = hasAirBags;
    }

    public boolean isHasAbs() {
        return hasAbs;
    }

    public void setHasAbs(boolean hasAbs) {
        this.hasAbs = hasAbs;
    }
}
