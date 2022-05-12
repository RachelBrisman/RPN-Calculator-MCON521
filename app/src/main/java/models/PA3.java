package models;

import java.util.Scanner;
import java.util.Stack;

public class PA3 {
    public static String evaluate(String expression) {
        Stack<Double> stackNum = new Stack<>();
        String[] strExp = expression.split("\\s+");

        for (int i = 0; i < strExp.length; i++) {

            String value = strExp[i];
            if (!pushIfNumeric(value, stackNum)) {

                if (isOperation(value)) {
                    if (stackNum.size() < 2) {
                        return "Invalid input";
                    } else {
                        Double value1 = stackNum.pop();
                        Double value2 = stackNum.pop();
                        stackNum.push(operate(value1, value2, value.charAt(0)));
                        }

                    } else{
                        return "Invalid input";
                    }
                }

        }
        return String.format("%.2f", stackNum.pop());
    }

    public static boolean pushIfNumeric(String s, Stack<Double> stackNum) {
        try {
            Double dblNum = Double.parseDouble(s);
            stackNum.push(dblNum);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperation(String s) {
        if (s.equals("x") || s.equals("/") || s.equals("+") || s.equals("-")) {
            return true;
        } else {
            return false;
        }
    }

    public static Double operate(Double val1, Double val2, char operation) {
        Double retVal = null;
        switch (operation) {
            case '+':
                retVal = val2 + val1;
                break;
            case '-':
                retVal = val2 - val1;
                break;
            case '/':
                retVal = val2 / val1;
                break;
            case 'x':
                retVal = val2 * val1;
                break;
        }
        return retVal;
    }
}