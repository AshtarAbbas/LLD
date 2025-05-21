import java.util.List;
import java.util.NoSuchElementException;

public class IteratorPatternDemo {

    // Generic Iterator interface
    interface MyIterator<T> {
        boolean hasNext();
        T next();
    }

    // Generic Aggregate interface
    interface Aggregate<T> {
        MyIterator<T> createIterator();
    }

    // Employee class
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

    // Concrete Iterator for Employee
    static class EmployeeIterator implements MyIterator<Employee> {
        private int current = 0;
        private List<Employee> employees;

        public EmployeeIterator(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public boolean hasNext() {
            return current < employees.size();
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator has no more elements.");
            }
            return employees.get(current++);
        }
    }

    // Concrete Aggregate (Company)
    static class Company implements Aggregate<Employee> {
        private List<Employee> employees;

        public Company(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public MyIterator<Employee> createIterator() {
            return new EmployeeIterator(employees);
        }
    }

    // Main method
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Ashtar", 15, 2000000.00),
            new Employee("Abbas", 51, 300000.00),
            new Employee("Naqvi", 150, 4000000.00),
            new Employee("Syed", 115, 1000000.00)
        );

        Aggregate<Employee> company = new Company(employees);
        MyIterator<Employee> iterator = company.createIterator();

        while (iterator.hasNext()) {
            Employee e = iterator.next();
            System.out.println(e.getName() + "\t" + e.getAge() + "\t" + e.getSalary());
        }
    }
}
