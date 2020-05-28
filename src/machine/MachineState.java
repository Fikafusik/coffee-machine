package machine;

public enum MachineState {
    CHOOSE_ACTION("Write action (buy, fill, take, remaining, exit):") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            switch (command) {
                case "buy":
                    coffeeMachine.setState(BUY);
                    break;
                case "fill":
                    coffeeMachine.setState(FILL_WATER);
                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    coffeeMachine.setState(CHOOSE_ACTION);
                    break;
                case "remaining":
                    coffeeMachine.printRemaining();
                    coffeeMachine.setState(CHOOSE_ACTION);
                    break;
                case "exit":
                    coffeeMachine.stopWorking();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
        }
    },
    BUY("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            switch (command) {
                case "1":
                    coffeeMachine.makeCoffee(CoffeeRecipe.ESPRESSO);
                    break;
                case "2":
                    coffeeMachine.makeCoffee(CoffeeRecipe.LATTE);
                    break;
                case "3":
                    coffeeMachine.makeCoffee(CoffeeRecipe.CAPPUCCINO);
                    break;
                case "back":
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
            coffeeMachine.setState(CHOOSE_ACTION);
        }
    },
    FILL_WATER("Write how many ml of water do you want to add:") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            int water = 0;
            try {
                water = Integer.parseInt(command);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            coffeeMachine.fillWater(water);
            coffeeMachine.setState(FILL_MILK);
        }
    },
    FILL_MILK("Write how many ml of milk do you want to add:") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            int milk = 0;
            try {
                milk = Integer.parseInt(command);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            coffeeMachine.fillMilk(milk);
            coffeeMachine.setState(FILL_COFFEE_BEANS);
        }
    },
    FILL_COFFEE_BEANS("Write how many grams of coffee beans do you want to add:") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            int coffee = 0;
            try {
                coffee = Integer.parseInt(command);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            coffeeMachine.fillCoffee(coffee);
            coffeeMachine.setState(FILL_DISPOSABLE_CUPS);
        }
    },
    FILL_DISPOSABLE_CUPS("Write how many disposable cups of coffee do you want to add:") {
        @Override
        public void processCommand(CoffeeMachine coffeeMachine, String command) {
            int cups = 0;
            try {
                cups = Integer.parseInt(command);
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
            coffeeMachine.fillCups(cups);
            coffeeMachine.setState(CHOOSE_ACTION);
        }
    };

    private final String message;

    MachineState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public abstract void processCommand(CoffeeMachine coffeeMachine, String command);
}
