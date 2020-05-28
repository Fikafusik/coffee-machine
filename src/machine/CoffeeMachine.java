package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private boolean isWorking;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private MachineState state;

    CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.isWorking = true;
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        setState(MachineState.CHOOSE_ACTION);
    }

    public void setState(MachineState state) {
        System.out.println();
        System.out.println(state.getMessage());
        this.state = state;
    }

    public void fillWater(int water) {
        this.water += water;
    }

    public void fillMilk(int milk) {
        this.milk += milk;
    }

    public void fillCoffee(int coffee) {
        this.coffeeBeans += coffee;
    }

    public void fillCups(int cups) {
        this.cups += cups;
    }

    public void takeMoney() {
        printMoney();
        this.money = 0;
    }

    private void printMoney() {
        System.out.println("I gave you $" + money);
    }

    public void printRemaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }

    private void processCommand(String command) {
        state.processCommand( this, command);
    }

    public void makeCoffee(CoffeeRecipe coffeeRecipe) {
        if (water < coffeeRecipe.getWater()) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (milk < coffeeRecipe.getMilk()) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (coffeeBeans < coffeeRecipe.getCoffeeBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }

        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");

        water -= coffeeRecipe.getWater();
        milk -= coffeeRecipe.getMilk();
        coffeeBeans -= coffeeRecipe.getCoffeeBeans();
        money += coffeeRecipe.getCost();

        --cups;
    }

    public void stopWorking() {
        isWorking = false;
    }

    public boolean working() {
        return isWorking;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);

        Scanner scanner = new Scanner(System.in);
        while (coffeeMachine.isWorking) {
            coffeeMachine.processCommand(scanner.next());
        }
    }
}
