// CommandPatternDemo.java

/*
 * Command Design Pattern
 *
 * Purpose:
 * - Encapsulate a request as an object, thereby allowing for parameterization of clients with queues, requests, and operations.
 * - Decouples the sender (Invoker) from the receiver of a request.
 *
 * Key Components:
 * 1. Command Interface (AbstractCommand) – declares `execute()` method
 * 2. Concrete Commands (CreateRide, CancelRide) – encapsulate actions on Receiver
 * 3. Receiver (RiderReceiver) – knows how to perform the operations
 * 4. Invoker (RideInvoker) – triggers command execution
 * 5. Client (main method) – creates commands and assigns them to the invoker
 *
 * When to Use:
 * - To parameterize objects based on actions (menu, toolbar, etc.)
 * - To implement undo/redo functionality
 * - To queue, schedule, or log requests
 * - To support transactional behavior or macro recording
 *
 * Pros:
 * - Decouples objects that invoke operations from those that perform them
 * - Easy to add new commands without changing existing code
 * - Enables undo/redo and logging features
 * - Supports composite (macro) commands
 *
 * Cons:
 * - Increases number of classes (one per command)
 * - Can become overly complex for simple use cases
 */

public class CommandPatternDemo {

    // Client
    public static void main(String[] args) {
        // Step 1: Create receiver
        RiderReceiver receiver = new RiderReceiver();

        // Step 2: Create command objects with receiver reference
        AbstractCommand createRide = new CreateRide(receiver);
        AbstractCommand cancelRide = new CancelRide(receiver);

        // Step 3: Create invoker and process commands
        RideInvoker invoker = new RideInvoker();
        invoker.processCommand(createRide);  // Output: Ride created
        invoker.processCommand(cancelRide);  // Output: Ride cancelled
    }

    // 1. Command Interface
    interface AbstractCommand {
        void execute(); // Defines action to be executed
    }

    // 2. Concrete Command - CreateRide
    static class CreateRide implements AbstractCommand {
        private final RiderReceiver receiver;

        public CreateRide(RiderReceiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.createRide(); // Delegates action to receiver
        }
    }

    // 2. Concrete Command - CancelRide
    static class CancelRide implements AbstractCommand {
        private final RiderReceiver receiver;

        public CancelRide(RiderReceiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.cancelRide(); // Delegates action to receiver
        }
    }

    // 3. Receiver
    static class RiderReceiver {
        public void createRide() {
            System.out.println("Ride created");
        }

        public void cancelRide() {
            System.out.println("Ride cancelled");
        }
    }

    // 4. Invoker
    static class RideInvoker {
        public void processCommand(AbstractCommand command) {
            command.execute(); // Executes the command
        }
    }
}

