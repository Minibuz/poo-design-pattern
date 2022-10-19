package fr.uge.poo.ducks;

import java.util.ServiceLoader;

public class DuckFarm {

    public static void main(String[] args) {
        ServiceLoader<Duck> serviceLoaderDuck = ServiceLoader.load(fr.uge.poo.ducks.Duck.class);
        for(Duck duck : serviceLoaderDuck) {
            System.out.println(duck.quack());
        }
    }
}
