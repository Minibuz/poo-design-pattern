package fr.uge.poo.ducks;

public class DuckFarmBetter {

    public static void main(String[] args) {

        var regularDuckFifi = new RegularDuckFactory().withName("Fifi");
        System.out.println(regularDuckFifi.quack());
        var regularDuckRiri = new RegularDuckFactory().withName("Riri");
        System.out.println(regularDuckRiri.quack());
        var regularDuckLoulou = new RegularDuckFactory().withName("Loulou");
        System.out.println(regularDuckLoulou.quack());

        var rubberDuckFifi = new RubberDuckFactory().withName("Fifi");
        System.out.println(rubberDuckFifi.quack());
        var rubberDuckRiri = new RubberDuckFactory().withName("Riri");
        System.out.println(rubberDuckRiri.quack());
        var rubberDuckLoulou = new RubberDuckFactory().withName("Loulou");
        System.out.println(rubberDuckLoulou.quack());

        var surpriseDuckFifi = new DiscoDuckFactory().withName("Fifi");
        System.out.println(surpriseDuckFifi.quack());
        var surpriseDuckRiri = new DiscoDuckFactory().withName("Riri");
        System.out.println(surpriseDuckRiri.quack());
        var surpriseDuckLoulou = new DiscoDuckFactory().withName("Loulou");
        System.out.println(surpriseDuckLoulou.quack());
    }
}
