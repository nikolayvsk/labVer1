import org.example.Main;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private Main wordCounter;


    @BeforeAll
    public static void setUpAll() {
        System.out.println("Running all tests...");
    }

    @BeforeEach
    public void setUp() {
        wordCounter = new Main();
    }

    @Test
    public void testCounterWords() {
        String input = "tiger! tiger! burning bright";
        Map<String, Integer> expected = Map.of("tiger", 2, "burning", 1, "bright", 1);
        Map<String, Integer> actual = wordCounter.countWords(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testCounterWithNumberPunctuationWhiteSpaces() {
        String input = "what!!!!!...             \n\n\n    the hammer?             ((00))0what the chain?";
        Map<String, Integer> expected = Map.of("what", 2, "the", 2, "hammer", 1, "chain", 1);
        Map<String, Integer> actual = wordCounter.countWords(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testCounter() {
        Main wordCount = new Main();
        String input = "hello hello hello hellohello helo helo";
        assertTrue(wordCount.countWords(input).get("hello") == 3);
    }

    @Test
    public void testEmptyString() {
        String input = "";
        Map<String, Integer> actualCount = wordCounter.countWords(input);
        assertTrue(actualCount.isEmpty());
    }

    @Test
    public void testNull() {
        String input = null;
        assertThrows(IllegalArgumentException.class, () -> wordCounter.countWords(input));
    }
    @Test
    public void testCounterWordsWithHamcrest() {
        String input = "sky; dog, cat, rock::cat,+ cat, sky ==+\n";
        Map<String, Integer> expected = Map.of("sky", 2, "rock", 1, "cat", 3, "dog", 1);
        Map<String, Integer> actual = Main.countWords(input);
        assertThat(actual, CoreMatchers.is(expected));
    }

    @Test
    public void testCounterWithNumberPunctuationWhiteSpacesWithHamcrest() {
        String input = "There,       .are 12 eggs!!!!2231 in a chest. hello hello hello";
        Map<String, Integer> expected = Map.of("There", 1, "are", 1, "eggs", 1, "in", 1, "a", 1, "chest", 1, "hello", 3);
        Map<String, Integer> actual = Main.countWords(input);
        assertThat(actual, CoreMatchers.is(expected));
        assertThat(wordCounter.countWords(input).get("hello"), CoreMatchers.is(CoreMatchers.equalTo(3)));
    }

    @ParameterizedTest
    @CsvSource({"There are five games here, 5", "hello hello hello hellohello helo helo, 3"})
    public void testParamCounterWords(String input, int expectedCount) {
        Map<String, Integer> actualCount = wordCounter.countWords(input);
        assertEquals(expectedCount, actualCount.size());
    }
}
