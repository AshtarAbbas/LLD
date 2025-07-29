/*
 * Adapter Design Pattern
 *
 * Purpose:
 * - To allow incompatible interfaces to work together.
 * - It acts as a bridge between two incompatible interfaces.
 *
 * Real-World Analogy:
 * - Think of a **power adapter** that lets a 2-pin plug fit into a 3-pin socket.
 *   The device (Client) expects one kind of interface, but the plug provides another.
 *   The adapter converts the interface of the plug into something compatible with the socket.
 *
 * Problem it Solves:
 * - Sometimes you want to use an existing class, but its interface doesn't match the one you need.
 * - Adapter lets you **wrap an existing class** with a new interface.
 *
 * Key Components:
 * 1. Target Interface – defines the domain-specific interface used by the client
 * 2. Adaptee – existing class that needs adapting
 * 3. Adapter – wraps the adaptee and makes it compatible with the target interface
 * 4. Client – uses only the Target interface
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Uses TargetInterface
 *         └── Calls `request()`
 *              └── Adapter converts this call to Adaptee's `specificRequest()`
 *                   └── Returns the result to the client
 *
 * When to Use:
 * - You want to use a class that does what you need but has the wrong interface
 * - You don’t want to modify existing code (open/closed principle)
 *
 * Pros:
 * - Increases reusability of existing code
 * - Promotes code decoupling and flexibility
 * - Can work with legacy or third-party code
 *
 * Cons:
 * - May introduce extra complexity
 * - Too many adapters can clutter your codebase
 */


public class AdapterPattern {

    // Client
    public static void main(String[] args) {
        TargetInterface target = new Adapter(new Adaptee()); // Step 1: Wrap adaptee in adapter
        target.request(); // Step 2: Client calls target interface method
    }

    /*
     * 1. Target Interface
     * Defines the interface expected by the client.
     */
    public interface TargetInterface {
        void request(); // Generic method client expects
    }

    /*
     * 2. Adaptee
     * An existing class with a different (incompatible) interface.
     */
    public class Adaptee {
        public void specificRequest() {
            System.out.println("Called Adaptee's specificRequest()");
        }
    }

    /*
     * 3. Adapter
     * Bridges the gap between Adaptee and TargetInterface.
     */
    public class Adapter implements TargetInterface {

        private Adaptee adaptee;

        public Adapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            System.out.println("Adapter converts request() to specificRequest()");
            adaptee.specificRequest(); // Delegates to adaptee's method
        }
    }
}
