package fr.uge.poo.ducks;

import java.util.ServiceLoader;

public class DuckFarmBetter {

    public static void main(String[] args) {

        ServiceLoader<DuckFactory> serviceLoaderDuckFactory = ServiceLoader.load(fr.uge.poo.ducks.DuckFactory.class);
        for(DuckFactory duckFactory : serviceLoaderDuckFactory) {
            var duckFifi = duckFactory.withName("Fifi");
            System.out.println(duckFifi.quack());
            var duckRiri = duckFactory.withName("Riri");
            System.out.println(duckRiri.quack());
            var duckLoulou = duckFactory.withName("Loulou");
            System.out.println(duckLoulou.quack());
        }
    }
}
