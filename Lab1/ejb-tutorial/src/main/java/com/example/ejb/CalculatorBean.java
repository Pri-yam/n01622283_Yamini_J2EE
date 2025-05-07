package com.example.ejb;

import jakarta.ejb.Stateless;

@Stateless
public class CalculatorBean {
    public int add(int a, int b) {
        return a + b;
    }

    public int multiply (int a, int b) {
        return a * b; }

    public int divide (int a, int b) {
        return a / b; }

    public int subtract (int a, int b) {
        return a - b; }
}
