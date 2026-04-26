/*
 * Factory Method Design Pattern
 *
 * Purpose:
 * - To define an interface for creating an object, but let subclasses decide
 *   which class to instantiate.
 * - Delegates object creation to child classes instead of a central factory.
 *
 * Real-World Analogy:
 * - Think of a Logistics Company.
 *   The main company (Creator) defines a method to deliver goods.
 *   But the actual delivery type (Truck, Ship, Air) is decided by subclasses.
 *
 *   RoadLogistics → creates Truck
 *   SeaLogistics  → creates Ship
 *
 * Key Components:
 * 1. Product Interface (Vehicle) – defines common behavior
 * 2. Concrete Products (Car, Bike) – actual implementations
 * 3. Creator (VehicleFactory) – declares factory method
 * 4. Concrete Creators (CarFactory, BikeFactory) – override factory method
 * 5. Client (main method) – works with creator, not concrete classes
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Creates Concrete Factory (CarFactory / BikeFactory)
 *         └── Calls createVehicle()
 *              └── Factory decides which object to create
 *    └── Returns Product (Vehicle)
 *    └── Client uses it without knowing concrete class
 *
 * When to Use:
 * - When class cannot anticipate which objects it must create
 * - When you want to delegate object creation to subclasses
 * - When you want to follow Open/Closed Principle
 *
 * Pros:
 * - Eliminates large if-else or switch statements
 * - Follows Open/Closed Principle (easy to extend)
 * - Decouples client from concrete classes
 *
 * Cons:
 * - Increases number of classes
 * - Can add complexity for simple use cases
 */


public class FactoryMethodPattern {

    // Client
    public static void main(String[] args) {

        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        car.drive();

        VehicleFactory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.createVehicle();
        bike.drive();
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
     * 3. Creator (Abstract Factory Class)
     */
    static abstract class VehicleFactory {

        // Factory Method
        abstract Vehicle createVehicle();

        // Optional: common business logic
        public void deliverVehicle() {
            Vehicle vehicle = createVehicle();
            System.out.println("Delivering vehicle...");
            vehicle.drive();
        }
    }

    /*
     * 4. Concrete Creators
     */
    static class CarFactory extends VehicleFactory {

        @Override
        Vehicle createVehicle() {
            return new Car();
        }
    }

    static class BikeFactory extends VehicleFactory {

        @Override
        Vehicle createVehicle() {
            return new Bike();
        }
    }
}