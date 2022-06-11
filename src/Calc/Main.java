package Calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             BufferedReader bufferedReader = new BufferedReader(new StringReader(scanner.nextLine()))) {

            System.out.println(calc(bufferedReader.readLine()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CalculatorException calculatorException) {
            System.out.println(calculatorException.getMessage());
        }
    }

    public static String calc(String input) throws CalculatorException {
        boolean isRoman = false;
        int firstOperand;
        int secondOperand;
        int result;

        String[] operands = input.replaceAll(" ", "").split("[+\\-*/]");
        String operator = input.replaceAll("[^+\\-*/]", "");

        if ((operands.length != 2) || (operator.length() != 1))
            throw new CalculatorException("There must be 2 operands and 1 operator");

        try {
            firstOperand = Integer.parseInt(operands[0]);
            secondOperand = Integer.parseInt(operands[1]);

        } catch (NumberFormatException e) {
            firstOperand = SwitcherArabicAndRoman.romanToArabic(operands[0]);
            secondOperand = SwitcherArabicAndRoman.romanToArabic(operands[1]);
            isRoman = true;
        }

        if ((firstOperand <= 0) || (firstOperand > 10) || (secondOperand <= 0) || (secondOperand > 10))
            throw new CalculatorException("Operand is not in range (0,10]");


        result = switch (operator) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> throw new CalculatorException("Incorrect operator");
        };

        if (isRoman) {
            return SwitcherArabicAndRoman.arabicToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }
}

