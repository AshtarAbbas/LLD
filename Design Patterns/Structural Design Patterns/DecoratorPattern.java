/*
 * Decorator Design Pattern
 *
 * Purpose:
 * - To dynamically add new behavior to an object without modifying its class.
 * - Acts as a flexible alternative to subclassing.
 *
 * Real-World Analogy:
 * - Think of ordering a Pizza.
 *
 *   Base Pizza → Plain Pizza
 *   Add-ons → Cheese, Olives, Mushrooms
 *
 *   Each topping "wraps" the original pizza and adds cost/features.
 *
 * Key Components:
 * 1. Component Interface (Pizza) – defines common behavior
 * 2. Concrete Component (PlainPizza) – base object
 * 3. Decorator (PizzaDecorator) – wraps a component
 * 4. Concrete Decorators (Cheese, Olives) – add new behavior
 * 5. Client – composes objects dynamically
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Creates base object (PlainPizza)
 *    └── Wraps it with decorators:
 *          new CheeseDecorator(
 *              new OliveDecorator(
 *                  new PlainPizza()
 *              )
 *          )
 *    └── Each decorator adds its behavior
 *    └── Final result = combined behavior
 *
 * When to Use:
 * - When you want to add responsibilities dynamically
 * - When subclassing would lead to too many classes
 * - When behavior combinations are needed
 *
 * Pros:
 * - Open/Closed Principle (extend without modifying)
 * - Flexible runtime composition
 * - Avoids class explosion
 *
 * Cons:
 * - Many small classes
 * - Can become hard to debug if overused
 */


public class DecoratorPattern {

    // Client
    public static void main(String[] args) {

        // Base object
        Pizza pizza = new PlainPizza();

        // Add decorators dynamically
        pizza = new CheeseDecorator(pizza);
        pizza = new OliveDecorator(pizza);

        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: " + pizza.getCost());
    }

    /*
     * 1. Component Interface
     */
    interface Pizza {
        String getDescription();
        int getCost();
    }

    /*
     * 2. Concrete Component
     */
    static class PlainPizza implements Pizza {

        @Override
        public String getDescription() {
            return "Plain Pizza";
        }

        @Override
        public int getCost() {
            return 100;
        }
    }

    /*
     * 3. Base Decorator
     */
    static abstract class PizzaDecorator implements Pizza {

        protected Pizza pizza;

        public PizzaDecorator(Pizza pizza) {
            this.pizza = pizza;
        }

        @Override
        public String getDescription() {
            return pizza.getDescription();
        }

        @Override
        public int getCost() {
            return pizza.getCost();
        }
    }

    /*
     * 4. Concrete Decorators
     */
    static class CheeseDecorator extends PizzaDecorator {

        public CheeseDecorator(Pizza pizza) {
            super(pizza);
        }

        @Override
        public String getDescription() {
            return pizza.getDescription() + ", Cheese";
        }

        @Override
        public int getCost() {
            return pizza.getCost() + 30;
        }
    }

    static class OliveDecorator extends PizzaDecorator {

        public OliveDecorator(Pizza pizza) {
            super(pizza);
        }

        @Override
        public String getDescription() {
            return pizza.getDescription() + ", Olives";
        }

        @Override
        public int getCost() {
            return pizza.getCost() + 20;
        }
    }
}