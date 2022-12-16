package utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

public class TestUtils {
	private static final Logger logger = Logger.getLogger(TestUtils.class.getName());

	public String getUniqueMail(String value) {

		logger.info("Starting of getUniqueMail method");
		logger.info("Ending of getUniqueMail method");

		Random rand = new Random();
		// Generate random integers in range 0 to 99
		int rand_int1 = rand.nextInt(100);
		return value + rand_int1 + "@gmail.com";
	}

	public String getUniqueNumber(String value) {

		logger.info("Starting of getUniqueNumber method");
		logger.info("Ending of getUniqueNumber method");

		Random rand = new Random();
		// Generate random integers in range 0 to 9
		int rand_int1 = rand.nextInt(10);

		return value + rand_int1;
	}

	public String getUniqueCouponCode(String value) {
		logger.info("Starting of getUniqueCouponCode method");
		logger.info("Ending of getUniqueCouponCode method");

		Random rand = new Random();
		// Generate random integers in range 0 to 99
		int rand_int1 = rand.nextInt(100);
		return value + rand_int1;
	}
	public static String getRandomString(final int length) {
		final StringBuilder buffer = new StringBuilder();
		final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final int charactersLength = chars.length();

		for (int i = 0; i < length; i++) {
			final double index = Math.random() * charactersLength;
			buffer.append(chars.charAt((int) index));
		}
		return buffer.toString();
	}

	public String randomNumber(int digits) {
		logger.info("Starting of randomNumber method");
		logger.info("Ending of randomNumber method");

		return String.valueOf(RandomStringUtils.randomNumeric(digits));
	}
}
