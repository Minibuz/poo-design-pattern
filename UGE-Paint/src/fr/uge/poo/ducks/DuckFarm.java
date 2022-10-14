package fr.uge.poo.ducks;

public class DuckFarm {

    public static void main(String[] args) {
        Duck regularDuck = new RegularDuck();
        Duck rubberDuck = new RubberDuck();

        System.out.println(regularDuck.quack());
        System.out.println(rubberDuck.quack());
    }
}
