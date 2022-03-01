package ic.doc;

import java.util.Stack;

public class CalculatorModel {
    DisplayInterface calculatorDisplay;
    private String userInput = "";
    private final Stack<Integer> integerStack = new Stack<>();

    public CalculatorModel(DisplayInterface display) {
        calculatorDisplay = display;
    }

    public void addToStack(int number) {
        integerStack.add(number);
        userInput = userInput + number + " ";
        calculatorDisplay.updateInputField();
    }

    public void calculate(String operand) {
        if (hasMoreThanOneInput()) {
            int a = integerStack.pop();
            int b = integerStack.pop();

            if (operand.equals("+")) {
                integerStack.push(a + b);
            }
            if (operand.equals("-")) {
                integerStack.push(b - a);
            }
            if (operand.equals("*")) {
                integerStack.push(a * b);
            }
            if (operand.equals("/")) {
                integerStack.push(b / a);
            }
            userInput = userInput + operand + " ";
            calculatorDisplay.updateInputField();
            calculatorDisplay.updateAnswerField();
        }
    }

    private boolean hasMoreThanOneInput() {
        return integerStack.size() >= 2;
    }

    public int topOfStack() {
        return integerStack.peek();
    }

    public void clearStack() {
        if (!integerStack.isEmpty()) {
            integerStack.clear();
            userInput = "";
            calculatorDisplay.updateInputField();
        }
    }

    public boolean stackIsEmpty() {
        return integerStack.empty();
    }

    public int sizeOfStack() {
        return integerStack.size();
    }

    public String getUserInput() {
        return userInput;
    }
}
