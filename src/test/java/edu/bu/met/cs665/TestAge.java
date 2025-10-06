package edu.bu.met.cs665;

import edu.bu.met.cs665.example1.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 5 version of the TestAge class.
 * Demonstrates simple getter and setter tests for the Person class.
 */
public class TestAge {

    @Test
    public void testGetFirstName() {
        Person student = new Person("John", "Doe");
        assertEquals("John", student.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        Person student = new Person("John", "Doe");
        student.setFirstName("Bob");
        assertEquals("Bob", student.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Person student = new Person("John", "Doe");
        assertEquals("Doe", student.getLastName());
    }

    @Test
    public void testSetLastName() {
        Person student = new Person("John", "Doe");
        student.setLastName("Henrink");
        assertEquals("Henrink", student.getLastName());
    }

    @Test
    public void testPersonConstructor() {
        Person student = new Person("John", "Doe");
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
    }
}
