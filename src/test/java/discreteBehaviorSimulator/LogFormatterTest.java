package discreteBehaviorSimulator;

/**
 * @author Beda Igiraneza
 *
 */

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class LogFormatterTest {

	private LogFormatter logForm = new LogFormatter();

	@Test
	public void format() {
		String dateRegex = "^[0-9]{4}(\\.[0-9]{2}){2}\\s([0-9]{2}:){2}[0-9]{2}\\.[0-9]{3}";
		Pattern dateP = Pattern.compile(dateRegex);
		Matcher matcher = null;

		// log records
		LogRecord record = new LogRecord(Level.WARNING, "Test message");
		String formatted = this.logForm.format(record);

		String dateFormatted = formatted.split(": ")[0];
		matcher = dateP.matcher(dateFormatted);

		assertTrue(dateFormatted instanceof String);
		assertTrue(matcher.matches());
	}
}