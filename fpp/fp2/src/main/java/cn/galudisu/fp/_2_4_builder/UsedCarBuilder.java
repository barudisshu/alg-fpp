package cn.galudisu.fp._2_4_builder;

/**
 * 构建者模式，设置一系列方法，用于构建对象
 *
 * @author galudisu
 */
public class UsedCarBuilder {

    private final UsedCar car;

    public UsedCarBuilder() {
        this.car = new UsedCar();
    }

    public UsedCarBuilder hasAirBags(final boolean b) {
        car.setHasAirBags(b);
        return this;
    }

    public UsedCarBuilder hasAbs(final boolean b) {
        car.setHasAbs(b);
        return this;
    }

    public UsedCarBuilder hasAc(final boolean b) {
        car.setHasAc(b);
        return this;
    }

    public UsedCarBuilder hasGps(final boolean b) {
        car.setHasGps(b);
        return this;
    }

    public UsedCarBuilder yearOfManufacturing(final int year) {
        car.setYearOfManufacturing(year);
        return this;
    }

    public UsedCarBuilder kmDriven(final int km) {
        car.setKmDriven(km);
        return this;
    }

    public UsedCarBuilder model(final String itsModel) {
        car.setModel(itsModel);
        return this;
    }

    public UsedCarBuilder make(final String itsMake) {
        car.setMake(itsMake);
        return this;
    }

    public UsedCar build() {
        // set sensible defaults for optional attributes - gps, ac, airbags, abs
        // check make and model are consistent
        // check year of manufacturing is sensible
        // check kmDriven is not negative
        return car;
    }
}
