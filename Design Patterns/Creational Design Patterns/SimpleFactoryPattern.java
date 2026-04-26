/*
 * Simple Factory Design Pattern
 *
 * Purpose:
 * - To create objects without exposing the instantiation logic to the client.
 * - The client asks the factory for an object instead of using "new".
 *
 * Real-World Analogy:
 * - Think of a Vehicle Showroom.
 *   You (Client) ask for a car type (e.g., "SUV", "SEDAN").
 *   The Showroom (Factory) decides which exact model to give.
 *   You don’t construct the car yourself — the showroom does it for you.
 *
 * Key Components:
 * 1. Product Interface (Vehicle) – defines common behavior
 * 2. Concrete Products (Car, Bike) – actual implementations
 * 3. Factory Class (VehicleFactory) – contains logic to create objects
 * 4. Client (main method) – requests objects from factory
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Calls VehicleFactory.getVehicle("CAR")
 *          ├── Factory checks type
 *          ├── Creates corresponding object (Car/Bike)
 *          └── Returns Vehicle
 *    └── Client uses the object without knowing creation details
 *
 * When to Use:
 * - When object creation logic is complex
 * - When you want to centralize object creation
 * - When client should not know exact class being instantiated
 *
 * Pros:
 * - Encapsulates object creation logic
 * - Reduces coupling between client and concrete classes
 * - Easier to manage and modify object creation
 *
 * Cons:
 * - Violates Open/Closed Principle (adding new types requires modifying factory)
 * - Factory can become large if many types are added
 */


public class SimpleFactoryPattern {

    // Client
    public static void main(String[] args) {

        Vehicle vehicle1 = VehicleFactory.getVehicle("CAR");
        vehicle1.drive();

        Vehicle vehicle2 = VehicleFactory.getVehicle("BIKE");
        vehicle2.drive();
    }

    /*
     * 1. Product Interface
     */
    interface Vehicle {
        void drive();
    }

    /*
     * 2. Concrete Products
     */
    static class Car implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Driving a Car 🚗");
        }
    }

    static class Bike implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Riding a Bike 🏍️");
        }
    }

    /*
     * 3. Factory Class
     */
    static class VehicleFactory {

        public static Vehicle getVehicle(String type) {

            if (type == null) {
                return null;
            }

            if (type.equalsIgnoreCase("CAR")) {
                return new Car();
            } else if (type.equalsIgnoreCase("BIKE")) {
                return new Bike();
            }

            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}