package org.example;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    Triangle triangle;

    @Test
    public void testInvalidInputWithChar() {
        String[] input = {"a", "4", "5"};
        triangle = new Triangle(input);
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testInvalidInputWithTooManyNumbers() {
        String[] input = {"3", "4", "5", "6"};
        triangle = new Triangle(input);
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testInvalidInputWithZeroSide() {
        String[] input = {"0", "2", "4"};
        triangle = new Triangle(input);
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testInvalidInputWithSideEqualsTwoSideSum() {
        String[] input = {"1", "1", "2"};
        triangle = new Triangle(input);
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testScaleneTriangle() {
        triangle = new Triangle();
        triangle.setCurrent_type(3, 4, 5);
        assertEquals(Triangle.TYPE.SCALENE, triangle.getCurrent_type());
    }

    @Test
    public void testIsoscelesTriangle() {
        triangle = new Triangle();
        triangle.setCurrent_type(3, 3, 5);
        assertEquals(Triangle.TYPE.ISOSCELES, triangle.getCurrent_type());
    }

    @Test
    public void testEquilateralTriangle() {
        triangle = new Triangle();
        triangle.setCurrent_type(3, 3, 3);
        assertEquals(Triangle.TYPE.EQUILATERAL, triangle.getCurrent_type());
    }

    @Test
    public void setCurrentTypeWithInvalidValues() {
        triangle = new Triangle();
        triangle.setCurrent_type(0, 0, 0);
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void getCurrentTypeWithoutSetting() {
        triangle = new Triangle();
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testConstructorWithValidValues() {
        triangle = new Triangle(3, 4, 5);
        assertEquals(Triangle.TYPE.SCALENE, triangle.getCurrent_type());
    }

    @Test
    public void testUserInputWithValidValues() {
        triangle = new Triangle();
        String input = "3,4,5";
        simulateUserInput(input);
        triangle.getUserInput();
        assertEquals(Triangle.TYPE.SCALENE, triangle.getCurrent_type());
    }

    @Test
    public void testUserInputWithInvalidValues() {
        triangle = new Triangle();
        String input = "3,3,3,3";
        simulateUserInput(input);
        triangle.getUserInput();
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testUserInputWithInvalidCharacters() {
        triangle = new Triangle();
        String input = "a,a,a";
        simulateUserInput(input);
        triangle.getUserInput();
        assertNull(triangle.getCurrent_type());
    }

    @Test
    public void testUserInputWithNonNumberException() {
        triangle = new Triangle();
        String input = "a,a,a";
        simulateUserInput(input);
        assertThrows(NumberFormatException.class, triangle::getUserInput);
    }

    @Test
    @DisplayName("ConstructorNonNumberException")
    public void testConstructorWithNonNumberException() {
        String[] input = new String[]{"a", "a", "a"};
        assertThrows(NumberFormatException.class, () -> new Triangle(input));
    }

    //Util
    private void simulateUserInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}