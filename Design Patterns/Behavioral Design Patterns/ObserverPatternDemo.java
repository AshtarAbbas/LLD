// ObserverPatternDemo.java

/*
 * Observer Design Pattern
 *
 * Purpose:
 * - Defines a one-to-many dependency between objects so that when one object (subject)
 *   changes state, all its dependents (observers) are notified and updated automatically.
 *
 * Key Components:
 * 1. Subject (Observable): Maintains a list of observers and notifies them of changes
 * 2. Observer: Defines an updating interface for objects that should be notified
 * 3. ConcreteSubject: Implements state tracking and notification
 * 4. ConcreteObserver: Implements update behavior
 *
 * When to Use:
 * - When changes to one object require changing others automatically
 * - When an object should notify unknown or dynamically changing dependents
 *
 * Pros:
 * - Promotes loose coupling between subject and observers
 * - Supports dynamic relationships
 * - Easy to add new observers without changing subject code
 *
 * Cons:
 * - Can lead to memory leaks if observers are not unsubscribed
 * - Notification order is not guaranteed
 */

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternDemo {

    // Client: Demonstrates registering observers and notifying them on state change
    public static void main(String[] args) {
        // Create a concrete subject
        NewsAgency agency = new NewsAgency();

        // Create and attach observers
        Observer subscriber1 = new NewsChannel("Channel A");
        Observer subscriber2 = new NewsChannel("Channel B");
        Observer subscriber3 = new NewsChannel("Channel C");

        agency.registerObserver(subscriber1);
        agency.registerObserver(subscriber2);
        agency.registerObserver(subscriber3);

        // Trigger a change (breaking news)
        agency.setNews("New Policy Announced");

        // Unsubscribe one observer
        agency.removeObserver(subscriber2);

        // Trigger another update
        agency.setNews("Sports: Team X wins the match");
    }

    // 1. Observer Interface: defines update method
    interface Observer {
        void update(String news);  // Called when subject changes
    }

    // 2. Subject Interface: defines attach, detach, and notify
    interface Subject {
        void registerObserver(Observer o);    // Add an observer
        void removeObserver(Observer o);      // Remove an observer
        void notifyObservers();               // Notify all observers
    }

    // 3. Concrete Subject: holds state and notifies observers on change
    static class NewsAgency implements Subject {
        private List<Observer> observers = new ArrayList<>();  // List of subscribers
        private String latestNews;  // The state being observed

        // Called to update the internal state
        public void setNews(String news) {
            this.latestNews = news;
            notifyObservers();  // Automatically notify all observers
        }

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(latestNews);  // Send the updated news to each observer
            }
        }
    }

    // 4. Concrete Observer: reacts to changes from subject
    static class NewsChannel implements Observer {
        private String name;

        public NewsChannel(String name) {
            this.name = name;
        }

        @Override
        public void update(String news) {
            System.out.println(name + " received update: " + news);  // Display received news
        }
    }
}
