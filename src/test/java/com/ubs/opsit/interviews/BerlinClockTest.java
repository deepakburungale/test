package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;


public class BerlinClockTest {

	private TimeConverter berlinClock = new TimeConverterImpl();

	/**
	 * Test Berlin clock when time is midnight : 00:00:00
	 */
	@Test
	public void testBerlinClockMidnight() {
		String iputTime = "00:00:00";
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Y\n");
		expectedOutput.append("OOOO\n");
		expectedOutput.append("OOOO\n");
		expectedOutput.append("OOOOOOOOOOO\n");
		expectedOutput.append("OOOO");
		String convertedTime = berlinClock.convertTime(iputTime);
		Assert.assertEquals(expectedOutput.toString(), convertedTime);

	}

	/**
	 * Test Berlin clock when time is middle of afternoon : 13:17:01
	 */
	@Test
	public void testBerlinClockMidAfternoon() {
		String iputTime = "13:17:01";
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("O\n");
		expectedOutput.append("RROO\n");
		expectedOutput.append("RRRO\n");
		expectedOutput.append("YYROOOOOOOO\n");
		expectedOutput.append("YYOO");
		String convertedTime = berlinClock.convertTime(iputTime);
		Assert.assertEquals(expectedOutput.toString(), convertedTime);

	}

	/**
	 * Test Berlin clock when time is just before midnight : 23:59:59
	 */
	@Test
	public void testBerlinClockBeforeMidNight() {
		String iputTime = "23:59:59";
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("O\n");
		expectedOutput.append("RRRR\n");
		expectedOutput.append("RRRO\n");
		expectedOutput.append("YYRYYRYYRYY\n");
		expectedOutput.append("YYYY");
		String convertedTime = berlinClock.convertTime(iputTime);
		Assert.assertEquals(expectedOutput.toString(), convertedTime);

	}

	/**
	 * Test Berlin clock when time is midnight : 24:00:00
	 */
	@Test
	public void testBerlinClockMidNight() {
		String iputTime = "24:00:00";
		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("Y\n");
		expectedOutput.append("RRRR\n");
		expectedOutput.append("RRRR\n");
		expectedOutput.append("OOOOOOOOOOO\n");
		expectedOutput.append("OOOO");
		String convertedTime = berlinClock.convertTime(iputTime);
		Assert.assertEquals(expectedOutput.toString(), convertedTime);

	}

}
