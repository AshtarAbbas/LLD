/*
 * Template Design Pattern
 *
 * Definition:
 * The Template Method pattern defines the skeleton of an algorithm in the base class
 * and lets subclasses override specific steps without changing the overall structure.
 *
 * Real-World Examples:
 * - Android Activity Lifecycle methods (onCreate, onStart, etc.)
 * - File parsing frameworks
 * - Order processing systems (as shown below)
 *
 * Key Components:
 * 1. AbstractTemplate – defines the algorithm skeleton via the template method `orderProcessing()`
 * 2. Concrete Subclasses – implement specific steps of the algorithm
 * 3. Client – invokes the template method without needing to understand inner steps
 *
 * Workflow Summary:
 *
 * TemplatePatternDemo (main)
 *   ├── Creates `LocalDelivery` and `InternationalDelivery` instances
 *   ├── Calls `orderProcessing()` on each
 *   │     └── Executes steps in defined sequence:
 *   │           ├── verifyOrder()     → implemented by subclass
 *   │           ├── assignDeliveryPartner() → subclass-defined
 *   │           └── trackStatus()     → subclass-defined
 *   └── Same skeleton used, but different behavior based on subclass
 *
 * When to Use:
 * - You want to avoid code duplication across classes that follow the same process.
 * - You want to allow customization of individual steps in an algorithm.
 * - You want to enforce a particular sequence of operations.
 *
 * When NOT to Use:
 * - When the algorithm steps vary widely between implementations.
 * - When future changes to the algorithm structure are expected (can make base class unstable).
 * - When overuse of inheritance is a concern.
 *
 * Advantages (Pros):
 * - Promotes code reuse.
 * - Encourages consistent algorithm structure.
 * - Makes code easier to maintain and read.
 * - Separates invariant parts of algorithm from variant parts.
 *
 * Disadvantages (Cons):
 * - Tight coupling between base and derived classes.
 * - Changes to base class may break all subclasses.
 * - Can lead to large and rigid inheritance hierarchies.
 * - Not suitable for algorithms with many variations.
 */

// Abstract class defining the template method
abstract class AbstractTemplate {
    // Template method: defines the algorithm skeleton
    public final void orderProcessing() {
        verifyOrder();            // Step 1: Verify the order
        assignDeliveryPartner(); // Step 2: Assign delivery partner
        trackStatus();           // Step 3: Track status
    }

    // Abstract steps to be implemented by subclasses
    protected abstract void assignDeliveryPartner(); // Step to assign delivery partner
    protected abstract void trackStatus();           // Step to track status
    protected abstract void verifyOrder();           // Step to verify the order
}

// Concrete subclass for local delivery orders
class LocalDelivery extends AbstractTemplate {

    @Override
    protected void assignDeliveryPartner() {
        System.out.println("Local assign delivery partner");
    }

    @Override
    protected void trackStatus() {
        System.out.println("Local track status");
    }

    @Override
    protected void verifyOrder() {
        System.out.println("Local verify order");
    }
}

// Concrete subclass for international delivery orders
class InternationalDelivery extends AbstractTemplate {

    @Override
    protected void assignDeliveryPartner() {
        System.out.println("International assign delivery partner");
    }

    @Override
    protected void trackStatus() {
        System.out.println("International track status");
    }

    @Override
    protected void verifyOrder() {
        System.out.println("International verify order");
    }
}

// Client class to test the template pattern
public class TemplatePatternDemo {
    public static void main(String[] args) {
        // Create local order
        AbstractTemplate localOrder = new LocalDelivery();

        // Create international order
        AbstractTemplate internationalOrder = new InternationalDelivery();

        // Process local order
        localOrder.orderProcessing();
        System.out.println("-----");

        // Process international order
        internationalOrder.orderProcessing();
    }
}

