package com.example.demo23;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CalculatorController {
    private final CalculatorService calculatorService;
    private final CalculationRepository calculationRepository;

    public CalculatorController(CalculatorService calculatorService, CalculationRepository calculationRepository) {
        this.calculatorService = calculatorService;
        this.calculationRepository = calculationRepository;
    }

    @GetMapping("/")
    public String showCalculator(Model model) {
        List<Calculation> calculations = calculationRepository.findAll();
        model.addAttribute("calculations", calculations);
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double operand1, @RequestParam double operand2, @RequestParam String operation, Model model) {
        // Выполняем вычисление с использованием сервиса
        double result = calculatorService.calculate(operand1, operand2, operation);

        // Создаем новый объект Calculation для сохранения в базу данных
        Calculation calculation = new Calculation();
        calculation.setOperand1(operand1);
        calculation.setOperand2(operand2);
        calculation.setOperation(operation);
        calculation.setResult(result);

        // Сохраняем результат в базе данных
        calculationRepository.save(calculation);

        return "redirect:/";
    }

    @PostMapping("/clear")
    public String clearResults() {
        // Очистка результатов
        calculationRepository.deleteAll();
        return "redirect:/";
    }
}
