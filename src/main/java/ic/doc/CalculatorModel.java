package ic.doc;

import java.util.Stack;

public class CalculatorModel {
    DisplayInterface calculatorDisplay;
    private final Stack<Integer> integerStack = new Stack<>();

    public CalculatorModel(DisplayInterface display) {
        calculatorDisplay = display;
    }

    public void addToStack(int number) {
        integerStack.add(number);
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
        integerStack.clear();
    }

    public boolean stackIsEmpty() {
        return integerStack.empty();
    }

    public int sizeOfStack() {
        return integerStack.size();
    }
}
