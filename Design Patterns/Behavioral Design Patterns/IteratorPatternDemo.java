// IteratorPatternDemo.java

/*
 * Iterator Design Pattern
 *
 * Purpose:
 * - To provide a way to access the elements of a collection sequentially
 *   without exposing its internal structure.
 *
 * Key Components:
 * 1. Iterator Interface (MyIterator<T>) – defines the traversal operations (hasNext, next)
 * 2. Concrete Iterator (EmployeeIterator) – implements traversal logic for a collection
 * 3. Aggregate Interface (Aggregate<T>) – defines a method to return an iterator
 * 4. Concrete Aggregate (Company) – implements the Aggregate interface and holds a collection
 * 5. Client (main method) – uses the iterator to access collection elements
 *
 * When to Use:
 * - You want to traverse a complex collection without exposing its internals
 * - You want to use different traversal mechanisms (forward, reverse, filtered)
 *
 * Pros:
 * - Clean separation between data structure and iteration logic
 * - Supports multiple simultaneous iterations
 * - Promotes reuse of traversal logic
 *
 * Cons:
 * - Adds extra classes/interfaces
 * - Simple collections may not need the abstraction overhead
 */

import java.util.List;
import java.util.NoSuchElementException;

public class IteratorPatternDemo {

    // 1. Iterator interface
    interface MyIterator<T> {
        boolean hasNext(); // Returns true if more elements exist
        T next();          // Returns the next element in the collection
    }

    // 2. Aggregate interface (a collection that provides an iterator)
    interface Aggregate<T> {
        MyIterator<T> createIterator(); // Returns a custom iterator for the collection
    }

    // 3. Data class representing an Employee
    static class Employee {
        private String name;
        private int age;
        private double salary;

        public Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }
    }

    // 4. Concrete iterator for the Employee collection
    static class EmployeeIterator implements MyIterator<Employee> {
        private int current = 0;              // Pointer to current index
        private List<Employee> employees;     // Collection of employees

        public EmployeeIterator(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public boolean hasNext() {
            return current < employees.size(); // Returns true if more elements
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in iterator.");
            }
            return employees.get(current++); // Return current and move to next
        }
    }

    // 5. Concrete aggregate representing a company
    static class Company implements Aggregate<Employee> {
        private List<Employee> employees;

        public Company(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public MyIterator<Employee> createIterator() {
            return new EmployeeIterator(employees); // Return iterator for this company
        }
    }

    // 6. Client using the iterator
    public static void main(String[] args) {
        // Creating a list of employees
        List<Employee> employees = List.of(
            new Employee("Ashtar", 15, 2000000.00),
            new Employee("Abbas", 51, 300000.00),
            new Employee("Naqvi", 150, 4000000.00),
            new Employee("Syed", 115, 1000000.00)
        );

        // Creating the Company aggregate
        Aggregate<Employee> company = new Company(employees);

        // Creating the iterator
        MyIterator<Employee> iterator = company.createIterator();

        // Traversing the employee list using the iterator
        while (iterator.hasNext()) {
            Employee e = iterator.next();
            System.out.println(e.getName() + "\t" + e.getAge() + "\t" + e.getSalary());
        }
    }
}

