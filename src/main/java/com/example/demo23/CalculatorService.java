package com.example.demo23;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double calculate(double a, double b, String operation) {
        switch (operation) {
            case "Сложение":
                return a + b;
            case "Вычитание":
                return a - b;
            case "Умножение":
                return a * b;
            case "Деление":
                return a / b;

            default:
                throw new IllegalArgumentException("Неизвестное действие " + operation);
        }
    }
}