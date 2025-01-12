import org.example.exceptions.NotPositiveNumberException;
import org.example.exceptions.ToLessThanFromException;
import org.example.utils.RegexUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegexUtilsTest {
    @Test
    void emailRegexTest() {
        String expected = "^[0-9a-zA-Z]{1,15}[-,.]{0,1}[0-9a-zA-Z]{1,15}[@]{1}[a-z]{1,8}[.]{0,1}[a-z]{2,4}$";
        String actual = RegexUtils.emailRegex();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addCountWithTwoNumberTest() {
        String expected1 = "{12,15}";
        String expected2 = "{1,6}";
        String expected3 = "{1,15}";
        String expected4 = "{2,4}";

        String actual1 = RegexUtils.addCount(12, 15);
        String actual2 = RegexUtils.addCount(1, 6);
        String actual3 = RegexUtils.addCount(1, 15);
        String actual4 = RegexUtils.addCount(2, 4);

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
        Assertions.assertEquals(expected3, actual3);
        Assertions.assertEquals(expected4, actual4);
    }

    @Test
    void addCountWithTwoNumberExceptionsTest() {
        ToLessThanFromException exception = Assertions.assertThrows(ToLessThanFromException.class, () -> {
            RegexUtils.addCount(10, 4);
        });

        Assertions.assertEquals(ToLessThanFromException.class, exception.getClass());
        Assertions.assertEquals("Значение \"до\" меньше чем значение \"от\"", exception.getMessage());

        NotPositiveNumberException exception2 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.addCount(-1, 6);
        });

        NotPositiveNumberException exception3 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.addCount(3, -15);
        });

        Assertions.assertEquals(NotPositiveNumberException.class, exception2.getClass());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception2.getMessage());

        Assertions.assertEquals(NotPositiveNumberException.class, exception3.getClass());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception3.getMessage());
    }

    @Test
    void addCountWithOneNumberTest() {
        String expected1 = "{4}";
        String expected2 = "{3}";
        String expected3 = "{12}";
        String expected4 = "{6}";

        String actual1 = RegexUtils.addCount(4);
        String actual2 = RegexUtils.addCount(3);
        String actual3 = RegexUtils.addCount(12);
        String actual4 = RegexUtils.addCount(6);

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
        Assertions.assertEquals(expected3, actual3);
        Assertions.assertEquals(expected4, actual4);
    }

    @Test
    void addCountWithOneNumberWithExceptionsTest() {
        NotPositiveNumberException exception1 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.addCount(-12);
        });

        NotPositiveNumberException exception2 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.addCount(-9);
        });

        Assertions.assertEquals(NotPositiveNumberException.class, exception1.getClass());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception1.getMessage());

        Assertions.assertEquals(NotPositiveNumberException.class, exception2.getClass());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception2.getMessage());
    }

    @Test
    void matchingEmailTest() {
        boolean actual1 = RegexUtils.matcher(RegexUtils.emailRegex(), "assdadssadsd@bk.rs").find();
        boolean actual2 = RegexUtils.matcher(RegexUtils.emailRegex(), "qwerty@yandex.com").find();
        boolean actual3 = RegexUtils.matcher(RegexUtils.emailRegex(), "qwerty@gmail.com").find();

        boolean actual4 = RegexUtils.matcher(RegexUtils.emailRegex(), "afasfssa").find();
        boolean actual5 = RegexUtils.matcher(RegexUtils.emailRegex(), "12345678").find();
        boolean actual6 = RegexUtils.matcher(RegexUtils.emailRegex(), "wrong_email").find();

        Assertions.assertTrue(actual1);
        Assertions.assertTrue(actual2);
        Assertions.assertTrue(actual3);

        Assertions.assertFalse(actual4);
        Assertions.assertFalse(actual5);
        Assertions.assertFalse(actual6);
    }

    @Test
    void charsTest() {
        String expected1 = "[23]";
        String expected2 = "[A-Z]";
        String expected3 = "[dfhufujafnu]";

        String actual1 = RegexUtils.chars("23");
        String actual2 = RegexUtils.chars("A-Z");
        String actual3 = RegexUtils.chars("dfhufujafnu");

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void charsListTwoNumbersTest() {
        String expected1 = "[A-Z]{3,17}";
        String expected2 = "[qwerty]{1,5}";

        String actual1 = RegexUtils.charsList(3, 17, "A-Z");
        String actual2 = RegexUtils.charsList(1, 5, "qwerty");

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void charsListTwoNumbersExceptionTest() {
        NotPositiveNumberException exception1 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.charsList(-1, 5, "");
        });

        NotPositiveNumberException exception2 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.charsList(1, -5, "");
        });

        ToLessThanFromException exception3 = Assertions.assertThrows(ToLessThanFromException.class, () -> {
            RegexUtils.charsList(12, 8, "");
        });

        ToLessThanFromException exception4 = Assertions.assertThrows(ToLessThanFromException.class, () -> {
            RegexUtils.charsList(24, 8, "");
        });

        Assertions.assertEquals(NotPositiveNumberException.class, exception1.getClass());
        Assertions.assertEquals(NotPositiveNumberException.class, exception2.getClass());
        Assertions.assertEquals(ToLessThanFromException.class, exception3.getClass());
        Assertions.assertEquals(ToLessThanFromException.class, exception4.getClass());

        Assertions.assertEquals("Значение не является позитивным целым числом", exception1.getMessage());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception2.getMessage());
        Assertions.assertEquals("Значение \"до\" меньше чем значение \"от\"", exception3.getMessage());
        Assertions.assertEquals("Значение \"до\" меньше чем значение \"от\"", exception4.getMessage());
    }

    @Test
    void charsListOneNumberTest() {
        String expected1 = "[A-Z]{2}";
        String expected2 = "[qwerty]{56}";

        String actual1 = RegexUtils.charsList(2, "A-Z");
        String actual2 = RegexUtils.charsList(56, "qwerty");

        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void charsListOneNumberExceptionTest() {
        NotPositiveNumberException exception1 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.charsList(-5, "");
        });

        NotPositiveNumberException exception2 = Assertions.assertThrows(NotPositiveNumberException.class, () -> {
            RegexUtils.charsList(-25, "qwerty");
        });

        Assertions.assertEquals(NotPositiveNumberException.class, exception1.getClass());
        Assertions.assertEquals(NotPositiveNumberException.class, exception2.getClass());

        Assertions.assertEquals("Значение не является позитивным целым числом", exception1.getMessage());
        Assertions.assertEquals("Значение не является позитивным целым числом", exception2.getMessage());
    }
}
