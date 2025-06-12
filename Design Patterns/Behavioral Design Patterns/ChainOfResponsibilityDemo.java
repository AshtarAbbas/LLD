/**
 * Chain of Responsibility Design Pattern Example
 * ----------------------------------------------
 * Intent:
 * - To avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.
 * - The request is passed along a chain of handlers until one of them handles it.
 *
 * Real-World Analogy:
 * - Think of a customer support system:
 *     Level 1 Support → Level 2 → Level 3
 *     A simple query is resolved by Level 1. If it’s complex, it’s forwarded to Level 2, and so on.
 *
 * Workflow Summary:
 *
 * ChainOfResponsibilityDemo (main)
 *   ├── Creates handlers:
 *   │     ├── LowLevelHandler (handles level 1)
 *   │     ├── MidLevelHandler (handles level 2)
 *   │     └── HighLevelHandler (handles level 3)
 *   ├── Chain setup: low → mid → high
 *   ├── Requests sent to first handler (`low`)
 *   │     ├── If it can handle → processes request
 *   │     └── Else → forwards to next in chain
 *   └── If no handler handles the request → "not handled" message printed
 *
 * When to Use:
 * - When multiple objects can handle a request, and the handler isn’t known in advance.
 * - When you want to decouple sender and receiver.
 * - When handlers should be dynamically chained at runtime.
 *
 * When NOT to Use:
 * - When all requests must be handled.
 * - When the order of handlers is hardcoded and inflexible.
 * - When performance is critical (due to long chains).
 *
 * Pros:
 * - Reduces coupling between sender and receiver.
 * - Adds flexibility in assigning responsibilities.
 * - Supports runtime changes to the chain.
 *
 * Cons:
 * - Can be hard to debug due to dynamic nature.
 * - May lead to requests not being handled.
 * - Long chains may decrease performance.
 */

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        // Creating handlers
        Handler low = new LowLevelHandler();
        Handler mid = new MidLevelHandler();
        Handler high = new HighLevelHandler();

        // Setting up the chain: low -> mid -> high
        low.setNext(mid);
        mid.setNext(high);

        // Client sends various requests
        System.out.println("Sending request of level 1:");
        low.handleRequest(1);

        System.out.println("\nSending request of level 2:");
        low.handleRequest(2);

        System.out.println("\nSending request of level 3:");
        low.handleRequest(3);

        System.out.println("\nSending request of level 4 (no handler):");
        low.handleRequest(4);
    }

    /**
     * Handler Interface (Abstract Handler)
     * - Declares method to set the next handler and to handle the request
     */
    interface Handler {
        void setNext(Handler next);
        void handleRequest(int level);
    }

    /**
     * Concrete Handler for level 1
     */
    static class LowLevelHandler implements Handler {
        private Handler next;

        @Override
        public void setNext(Handler next) {
            this.next = next;
        }

        @Override
        public void handleRequest(int level) {
            if (level == 1) {
                System.out.println("LowLevelHandler handled request of level 1.");
            } else if (next != null) {
                next.handleRequest(level);
            } else {
                System.out.println("Request of level " + level + " was not handled.");
            }
        }
    }

    /**
     * Concrete Handler for level 2
     */
    static class MidLevelHandler implements Handler {
        private Handler next;

        @Override
        public void setNext(Handler next) {
            this.next = next;
        }

        @Override
        public void handleRequest(int level) {
            if (level == 2) {
                System.out.println("MidLevelHandler handled request of level 2.");
            } else if (next != null) {
                next.handleRequest(level);
            } else {
                System.out.println("Request of level " + level + " was not handled.");
            }
        }
    }

    /**
     * Concrete Handler for level 3
     */
    static class HighLevelHandler implements Handler {
        private Handler next;

        @Override
        public void setNext(Handler next) {
            this.next = next;
        }

        @Override
        public void handleRequest(int level) {
            if (level == 3) {
                System.out.println("HighLevelHandler handled request of level 3.");
            } else if (next != null) {
                next.handleRequest(level);
            } else {
                System.out.println("Request of level " + level + " was not handled.");
            }
        }
    }
}
