package hello.typeconverter.formatter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();
    @Test
    void parse() throws ParseException {
        Number result = formatter.parse("10,000", Locale.KOREA);
        assertThat(result).isEqualTo(10000L); //long 타입 주의!
    }

    @Test
    void print() {
        String result = formatter.print(10000, Locale.KOREA);
        assertThat(result).isEqualTo("10,000");
    }
}