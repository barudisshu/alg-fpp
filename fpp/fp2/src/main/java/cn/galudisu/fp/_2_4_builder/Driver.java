package cn.galudisu.fp._2_4_builder;

public class Driver {

    public static void main(String[] args) {
        // Note the method chaining
        UsedCar car = new UsedCarBuilder()
                .make("Maruti")
                .model("Alto")
                .kmDriven(10000)
                .yearOfManufacturing(2006)
                .hasGps(false)
                .hasAc(false)
                .hasAbs(false)
                .hasAirBags(false)
                .build();
        System.out.println(car);
    }

    // Java 允许使用构造器来实现构造模式，即 (arg1, arg2,...,argN) 重构
}