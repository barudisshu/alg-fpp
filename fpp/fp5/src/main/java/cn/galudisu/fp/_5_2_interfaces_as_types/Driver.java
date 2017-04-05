package cn.galudisu.fp._5_2_interfaces_as_types;

// Driver.java
public class Driver {
    public static void main(String[] args) {
        Horse horse = new Horse();
        Donkey donkey = new Donkey();
        donkey.walk();
        donkey.moveGoods();
        horse.walk();
    }
}