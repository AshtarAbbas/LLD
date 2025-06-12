/*
 * Builder Design Pattern
 *
 * Purpose:
 * - To construct a complex object step by step, separating construction logic from representation.
 * - Useful when the construction process must allow different representations of the object being built.
 *
 * Real-World Analogy:
 * - Think of building a customized pizza in a restaurant.
 *   You (Client) tell the Waiter (Director) what you want.
 *   The Waiter passes the instructions to the Chef (Builder), who adds dough, sauce, toppings, and cheese.
 *   The result is a customized Pizza (Product), built step by step.
 *
 * Key Components:
 * 1. Builder Interface (BuilderInterface) – defines methods to build parts of the product
 * 2. Concrete Builder (DesktopBuilder) – implements the steps defined in the builder interface
 * 3. Product (Computer) – the complex object being built
 * 4. Director – controls the construction sequence using the builder
 * 5. Client (main method) – initiates the build process through the director
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Creates Director
 *         └── Director uses BuilderInterface to:
 *              ├── buildCpu()
 *              ├── buildRam()
 *              ├── buildStorage()
 *              └── getDesktop()
 *    └── Returns the final Computer product
 *    └── Calls display() on the product
 *
 * When to Use:
 * - When an object needs to be created with many possible configurations
 * - When building process must be decoupled from object representation
 * - When construction involves multiple steps
 *
 * Pros:
 * - Cleaner code for object construction
 * - Separation of concerns: Director controls process, Builder handles parts
 * - Easier to create different representations using same process
 * - Promotes immutability and encapsulation
 *
 * Cons:
 * - Requires additional classes
 * - Might be overkill for simple objects
 */


public class BuilderPattern {

    // Client
    public static void main(String[] args) {
        Director director = new Director(); // Step 1: Create Director
        Computer desktop = director.builder(new DesktopBuilder()); // Step 2: Build product via builder
        desktop.display(); // Step 3: Display final product
    }

    /*
     * 1. Builder Interface
     * Declares methods for building parts of the product.
     */
    public interface BuilderInterface {
        void buildRam();
        void buildCpu();
        void buildStorage();
        Computer getDesktop();
    }

    /*
     * 2. Product Class
     * The complex object that is to be built.
     */
    public class Computer {
        String ram;
        String cpu;
        String storage;

        // Setters for parts
        public void setRam(String ram) {
            this.ram = ram;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public void setStorage(String storage) {
            this.storage = storage;
        }

        // Display assembled configuration
        public void display() {
            System.out.println("CPU: " + this.cpu);
            System.out.println("RAM: " + this.ram);
            System.out.println("Storage: " + this.storage);
        }
    }

    /*
     * 3. Concrete Builder
     * Implements the building steps and assembles the product.
     */
    public class DesktopBuilder implements BuilderInterface {

        private Computer computer;

        public DesktopBuilder() {
            this.computer = new Computer(); // Initialize product
        }

        @Override
        public void buildRam() {
            this.computer.setRam("32GB");
        }

        @Override
        public void buildCpu() {
            this.computer.setCpu("Core i5");
        }

        @Override
        public void buildStorage() {
            this.computer.setStorage("2TB");
        }

        @Override
        public Computer getDesktop() {
            return this.computer; // Return the fully built product
        }
    }

    /*
     * 4. Director
     * Controls the order of building steps.
     */
    public class Director {
        public Computer builder(BuilderInterface builderInterface) {
            builderInterface.buildCpu();
            builderInterface.buildRam();
            builderInterface.buildStorage();
            return builderInterface.getDesktop();
        }
    }
}
