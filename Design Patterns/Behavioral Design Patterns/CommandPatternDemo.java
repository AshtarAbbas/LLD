public class CommandPatternDemo {

    public static void main(String[] args) {
        RideInvoker invoker = new RideInvoker();
        RiderReceiver receiver = new RiderReceiver();

        AbstractCommand createRide = new CreateRide(receiver);
        AbstractCommand cancelRide = new CancelRide(receiver);

        invoker.processCommand(createRide);
        invoker.processCommand(cancelRide);
    }

    // Command Interface
    interface AbstractCommand {
        void execute();
    }

    // Concrete Command - CreateRide
    static class CreateRide implements AbstractCommand {
        private final RiderReceiver receiver;

        public CreateRide(RiderReceiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.createRide();
        }
    }

    // Concrete Command - CancelRide
    static class CancelRide implements AbstractCommand {
        private final RiderReceiver receiver;

        public CancelRide(RiderReceiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.cancelRide();
        }
    }

    // Receiver
    static class RiderReceiver {
        public void createRide() {
            System.out.println("Ride created");
        }

        public void cancelRide() {
            System.out.println("Ride cancelled");
        }
    }

    // Invoker
    static class RideInvoker {
        public void processCommand(AbstractCommand command) {
            command.execute();
        }
    }
}
