package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CalculatorModelTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    DisplayInterface display = context.mock(DisplayInterface.class);

    CalculatorModel model = new CalculatorModel(display);

    @Test
    public void emptyStackCreatedOnConstruction() {
        assertTrue(model.stackIsEmpty());
    }

    @Test
    public void tryingToCallOperandOnStackWithLessThanTwoElementsDoesNothing() {
        context.checking(new Expectations() {{
            ignoring(display).updateInputField();
            ignoring(display).updateAnswerField();
        }});
        model.addToStack(3);
        model.calculate("+");
        assertThat(model.topOfStack(), is(3));
    }

    @Test
    public void callingAdditionOperandReplacesTopTwoElementsInStackWithTheirSum() {
        context.checking(new Expectations() {{
            ignoring(display).updateInputField();
            ignoring(display).updateAnswerField();
        }});
        model.addToStack(3);
        model.addToStack(4);
        model.addToStack(5);
        model.calculate("+");
        assertThat(model.topOfStack(), is(9));
        assertThat(model.sizeOfStack(), is(2));
    }

    @Test
    public void callingSubtractionOperandReplacesTopTwoElementsInStackWithTheirDifference() {
        context.checking(new Expectations() {{
            ignoring(display).updateInputField();
            ignoring(display).updateAnswerField();
        }});
        model.addToStack(3);
        model.addToStack(4);
        model.addToStack(5);
        model.calculate("-");
        assertThat(model.topOfStack(), is(-1));
        assertThat(model.sizeOfStack(), is(2));
    }

    @Test
    public void numbersAndOperandsKeptTrackOfInUserInputString() {
        context.checking(new Expectations() {{
            ignoring(display).updateInputField();
            ignoring(display).updateAnswerField();
        }});
        model.addToStack(3);
        model.addToStack(4);
        model.addToStack(5);
        model.calculate("-");

        assertThat(model.getUserInput(), is("3 4 5 - "));
    }

    @Test
    public void clearMethodEmptiesTheStackAndEmptiesTheUserInputString() {
        context.checking(new Expectations() {{
            ignoring(display).updateInputField();
            ignoring(display).updateAnswerField();
        }});
        model.addToStack(3);
        model.addToStack(4);
        model.clearStack();

        assertThat(model.getUserInput(), is(""));
        assertTrue(model.stackIsEmpty());
    }
}
