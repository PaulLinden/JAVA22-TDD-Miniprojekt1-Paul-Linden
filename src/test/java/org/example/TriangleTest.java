package org.example;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    Triangle triangle;

    @Test
    public void testSCALENEStringValue(){
        Triangle.TYPE scalene = Triangle.TYPE.SCALENE;
        assertEquals("SCALENE",scalene.toString());
    }

    @Test
    public void testEQUILATERALStringValue(){
        Triangle.TYPE equilateral = Triangle.TYPE.EQUILATERAL;
        assertEquals("EQUILATERAL",equilateral.toString());
    }

    @Test
    public void testISOSCELESStringValue(){
        Triangle.TYPE isosceles = Triangle.TYPE.ISOSCELES;
        assertEquals("ISOSCELES",isosceles.toString());
    }

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

    @Test
    public void testOutputScalene() {

        triangle = new Triangle(3,4,5);
        String expectedOutput = "3, 4, 5, This is a Scalene triangle";
        String output = triangle.toString();

        assertEquals(expectedOutput,output);
    }

    @Test
    public void testOutputIsosceles() {
        triangle = new Triangle(3,3,5);
        String expectedOutput = "3, 3, 5, This is a Isosceles triangle";
        String output = triangle.toString();

        assertEquals(expectedOutput,output);
    }

    @Test
    public void testOutputEquilateral() {
        triangle = new Triangle(3,3,3);
        String expectedOutput = "3, 3, 3, This is a Equilateral triangle";
        String output = triangle.toString();

        assertEquals(expectedOutput,output);
    }

    //Util
    private void simulateUserInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}