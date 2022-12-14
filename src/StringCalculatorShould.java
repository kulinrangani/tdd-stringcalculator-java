import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorShould {
    StringCalculator sc;

    @Before
    public void initializeResouces() {
        sc = new StringCalculator();
    }

    @Test
    public void returnZeroIfStringIsEmpty() {
        assertEquals(0, sc.add(""));
    }

    @Test
    public void handelNewLine() {
        assertEquals(6, sc.add("1\n2,3"));
    }

    @Test
    public void returnSumIfStringIsNotEmpty() {
        assertEquals(12, sc.add("5,7"));
    }

    @Test
    public void returnZeroIfStringHasOnlySpaces() {
        assertEquals(0, sc.add("   "));
    }

    @Test
    public void handelAnUnkownAmountOfNumbers() {
        assertEquals(670, sc.add("100, 30, 90, 50, 400"));
    }

    @Test
    public void avoidMoreThanThousend() {
        assertEquals(2, sc.add("2,1002"));
    }

    @Test
    public void handleAlphabet() {
        assertEquals(7, sc.add("1,2,a,c"));
    }

    @Test
    public void handleSingleNegativeNumber() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> sc.add("-1"));
        assertEquals(e.getMessage(), "Negatives not allowed -> -1 ");
    }

    @Test
    public void handleMulipleNegativeNumbers() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> sc.add("-1,2,-4"));
        assertEquals(e.getMessage(), "Negatives not allowed -> -1 -4 ");
    }

    @Test
    public void handelDelimiters() {
        assertEquals(3, sc.add("//;\n1;2"));
    }

    @Test
    public void handelCustomDelimiter() {
        assertEquals(6, sc.add("//;;;\n1;;;2;;;3"));
    }

    @Test
    public void sumOfOddIndices() {
        assertEquals(2, sc.add("0//1,2,3"));
        assertEquals(4, sc.add("0//0,1,5,3"));
    }

    @Test
    public void sumOfEvenIndices() {
        assertEquals(4, sc.add("1//1,2,3"));
        assertEquals(6, sc.add("1//5,3,1,7"));
    }

}
