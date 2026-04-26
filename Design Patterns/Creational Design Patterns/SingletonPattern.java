/*
 * Singleton Design Pattern
 *
 * Purpose:
 * - To ensure that a class has only ONE instance throughout the application.
 * - Provides a global access point to that instance.
 *
 * Real-World Analogy:
 * - Think of a Government Office.
 *   There is only one official office (instance), and everyone must go through it.
 *   You cannot create multiple government offices for the same purpose.
 *
 * Key Components:
 * 1. Singleton Class – contains a static instance of itself
 * 2. Private Constructor – prevents direct instantiation
 * 3. Static Method (getInstance) – provides global access to the instance
 *
 * Workflow Summary:
 *
 * Client (main)
 *    └── Calls Singleton.getInstance()
 *          ├── If instance is NULL → create new instance
 *          └── Else → return existing instance
 *    └── Always returns SAME object
 *
 * When to Use:
 * - When exactly one instance is needed (e.g., DB connection, Logger, Config)
 * - When you need controlled global access
 *
 * Pros:
 * - Controlled instance creation
 * - Saves memory (single object reused)
 * - Thread-safe versions possible
 *
 * Cons:
 * - Can introduce global state (harder to test)
 * - Needs care in multithreaded environments
 */


public class SingletonPattern {

    // Client
    public static void main(String[] args) {

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        // Verifying both references point to same object
        System.out.println(obj1 == obj2); // true
    }

    /*
     * Singleton Class
     */
    static class Singleton {

        // Step 1: Create static instance variable
        private static Singleton instance;

        // Step 2: Private constructor to restrict object creation
        private Singleton() {
            System.out.println("Singleton instance created");
        }

        /*
         * Step 3: Public static method to provide access
         * Lazy Initialization (instance created only when needed)
         */
        public static Singleton getInstance() {

            if (instance == null) {
                synchronized (Singleton.class){
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}