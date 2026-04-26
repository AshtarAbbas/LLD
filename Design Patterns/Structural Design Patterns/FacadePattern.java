/*
 * Facade Design Pattern
 *
 * Purpose:
 * - To provide a simplified, unified interface to a complex subsystem.
 * - Hides the complexity of multiple classes behind a single interface.
 *
 * Real-World Analogy:
 * - Think of an Online Food Delivery App.
 *
 *   You (Client) just place an order.
 *   The app (Facade) internally:
 *      ├── Finds restaurant
 *      ├── Places order
 *      ├── Processes payment
 *      ├── Assigns delivery partner
 *
 *   You don’t deal with each system individually.
 *
 * Key Components:
 * 1. Subsystem Classes – complex internal components (Payment, Inventory, Delivery)
 * 2. Facade – provides simplified interface
 * 3. Client – interacts only with facade
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Calls Facade.placeOrder()
 *         ├── Inventory.check()
 *         ├── Payment.process()
 *         ├── Delivery.assign()
 *    └── Gets final result without dealing with complexity
 *
 * When to Use:
 * - When you want to simplify complex systems
 * - When there are many interdependent classes
 * - When you want a clean API for clients
 *
 * Pros:
 * - Reduces complexity for client
 * - Decouples client from subsystem
 * - Improves readability and usability
 *
 * Cons:
 * - Facade can become a "god class" if overloaded
 * - May hide useful low-level functionality
 */


public class FacadePattern {

    // Client
    public static void main(String[] args) {

        OrderFacade facade = new OrderFacade();
        facade.placeOrder();
    }

    /*
     * 1. Subsystem Classes
     */
    static class InventoryService {
        public void checkStock() {
            System.out.println("Checking inventory...");
        }
    }

    static class PaymentService {
        public void processPayment() {
            System.out.println("Processing payment...");
        }
    }

    static class DeliveryService {
        public void assignDelivery() {
            System.out.println("Assigning delivery partner...");
        }
    }

    /*
     * 2. Facade
     */
    static class OrderFacade {

        private InventoryService inventory = new InventoryService();
        private PaymentService payment = new PaymentService();
        private DeliveryService delivery = new DeliveryService();

        public void placeOrder() {

            System.out.println("Order placed via Facade");

            inventory.checkStock();
            payment.processPayment();
            delivery.assignDelivery();

            System.out.println("Order completed");
        }
    }
}