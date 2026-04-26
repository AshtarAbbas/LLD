/*
 * Abstract Factory Design Pattern
 *
 * Purpose:
 * - To provide an interface for creating families of related or dependent objects
 *   without specifying their concrete classes.
 *
 * - Ensures that products created together are compatible.
 *
 * Real-World Analogy:
 * - Think of a Furniture Showroom.
 *
 *   You choose a style: "Modern" or "Victorian"
 *
 *   ModernFactory → ModernChair + ModernTable
 *   VictorianFactory → VictorianChair + VictorianTable
 *
 *   You never mix styles (e.g., ModernChair with VictorianTable)
 *
 * Key Components:
 * 1. Abstract Products (Chair, Table) – interfaces for product types
 * 2. Concrete Products – specific implementations (ModernChair, VictorianChair)
 * 3. Abstract Factory – declares methods to create each product
 * 4. Concrete Factories – implement creation for a specific family
 * 5. Client – uses factory interface, not concrete classes
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Chooses a factory (ModernFurnitureFactory)
 *         ├── createChair()
 *         ├── createTable()
 *    └── Gets a family of related objects
 *    └── Uses them without knowing concrete classes
 *
 * When to Use:
 * - When system needs multiple families of related objects
 * - When you want to enforce consistency between products
 * - When you want to avoid mixing incompatible objects
 *
 * Pros:
 * - Ensures compatibility between related objects
 * - Follows Open/Closed Principle
 * - Decouples client from concrete classes
 *
 * Cons:
 * - Complex structure (many classes/interfaces)
 * - Adding new product types is difficult (e.g., add Sofa everywhere)
 */


public class AbstractFactoryPattern {

    // Client
    public static void main(String[] args) {

        // Choose factory (family)
        FurnitureFactory factory = new ModernFurnitureFactory();

        Chair chair = factory.createChair();
        Table table = factory.createTable();

        chair.sitOn();
        table.use();

        // Switch family
        FurnitureFactory factory2 = new VictorianFurnitureFactory();

        Chair chair2 = factory2.createChair();
        Table table2 = factory2.createTable();

        chair2.sitOn();
        table2.use();
    }

    /*
     * 1. Abstract Products
     */
    interface Chair {
        void sitOn();
    }

    interface Table {
        void use();
    }

    /*
     * 2. Concrete Products (Modern Family)
     */
    static class ModernChair implements Chair {
        @Override
        public void sitOn() {
            System.out.println("Sitting on Modern Chair");
        }
    }

    static class ModernTable implements Table {
        @Override
        public void use() {
            System.out.println("Using Modern Table");
        }
    }

    /*
     * 2. Concrete Products (Victorian Family)
     */
    static class VictorianChair implements Chair {
        @Override
        public void sitOn() {
            System.out.println("Sitting on Victorian Chair");
        }
    }

    static class VictorianTable implements Table {
        @Override
        public void use() {
            System.out.println("Using Victorian Table");
        }
    }

    /*
     * 3. Abstract Factory
     */
    interface FurnitureFactory {
        Chair createChair();
        Table createTable();
    }

    /*
     * 4. Concrete Factories (Families)
     */
    static class ModernFurnitureFactory implements FurnitureFactory {

        @Override
        public Chair createChair() {
            return new ModernChair();
        }

        @Override
        public Table createTable() {
            return new ModernTable();
        }
    }

    static class VictorianFurnitureFactory implements FurnitureFactory {

        @Override
        public Chair createChair() {
            return new VictorianChair();
        }

        @Override
        public Table createTable() {
            return new VictorianTable();
        }
    }
}