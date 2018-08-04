package com.daimler.epdm.business.listofvalues.impl;

import com.ubs.opsit.interviews.BusinessException;

/**
 * Provides operations to convert time into Berlin clock representation.
 *
 * @author Deepak
 */
public class TimeConverterImpl implements TimeConverter {

	private static final int CHARS_ALLOWED_IN_FIRST_ROW = 4;

	private static final int CHARS_ALLOWED_IN_SECOND_ROW = 4;

	private static final int CHARS_ALLOWED_IN_THIRD_ROW = 11;

	private static final int CHARS_ALLOWED_IN_FOURTH_ROW = 4;

	/**
	 * Converts given time into Berlin clock representation.
	 *
	 * @param aTime
	 *            Time in the format hh:mm:ss to be converted into Berlin clock
	 *            representation.
	 *
	 * @return Berlin clock representation of given time. {@inheritDoc}
	 */
	@Override
	public String convertTime(String aTime) {

		// Split given string of time into hour,minute and second.
		String[] timeArray = aTime.split(":");

		validateTime(timeArray);

		StringBuilder berlinClockBuilder = new StringBuilder();

		buildTopRowOfBerlinClock(timeArray[2], berlinClockBuilder);

		buildFirstRowOfBerlinClock(timeArray[0], berlinClockBuilder);

		buildSecondRowOfBerlinClock(timeArray[0], berlinClockBuilder);

		buildThirdRowOfBerlinClock(timeArray[1], berlinClockBuilder);

		buildFourthRowOfBerlinClock(timeArray[1], berlinClockBuilder);

		return berlinClockBuilder.toString();

	}

	private void validateTime(String[] timeArray) {

		if (timeArray.length != 3) {
			throw new BusinessException("Please enter time in format hh:mm:ss");
		}
		if (Integer.valueOf(timeArray[0]) > 24 || Integer.valueOf(timeArray[0]) < 0) {
			throw new BusinessException("Hours entered shoud be within 0 to 24");
		}
		if (Integer.valueOf(timeArray[1]) > 60 || Integer.valueOf(timeArray[1]) < 0) {
			throw new BusinessException("Minutes entered should be within 0 to 60");
		}
		if (Integer.valueOf(timeArray[2]) > 60 || Integer.valueOf(timeArray[2]) < 0) {
			throw new BusinessException("Seconds entered should be within 0 to 60");
		}
	}

	/**
	 * Builds top row of Berlin clock. "Y" represents every 2 seconds
	 *
	 * @param seconds
	 *            Seconds part of time.
	 *
	 * @param berlinClockBuilder
	 *            Berlin clock string builder.
	 */
	private void buildTopRowOfBerlinClock(String seconds, StringBuilder berlinClockBuilder) {

		Integer secondsPartOfTime = Integer.valueOf(seconds);

		int topRow = secondsPartOfTime == 0 ? 0 : secondsPartOfTime % 2;

		if (topRow == 0) {
			berlinClockBuilder.append("Y\n");
		} else {
			berlinClockBuilder.append("O\n");
		}
	}

	/**
	 * Builds first row of Berlin clock.It consists of 4 characters with
	 * combination of character "R" and "O". Each character "R" represents 5
	 * hours.
	 *
	 * @param hours
	 *            Hours part of time.
	 *
	 * @param berlinClockBuilder
	 *            Berlin clock string builder.
	 */
	private void buildFirstRowOfBerlinClock(String hours, StringBuilder berlinClockBuilder) {

		Integer hoursPartOfTime = Integer.valueOf(hours);
		int firstRow = hoursPartOfTime == 0 ? 0 : hoursPartOfTime / 5;

		for (int i = 0; i < CHARS_ALLOWED_IN_FIRST_ROW; i++) {
			if (i < firstRow) {
				berlinClockBuilder.append("R");
			} else {
				berlinClockBuilder.append("O");
			}
		}
		berlinClockBuilder.append("\n");

	}

	/**
	 * Builds second row of Berlin clock.It consists of 4 characters with
	 * combination of character "R" and "O". Each character "R" represents 1
	 * hour.
	 *
	 * @param hours
	 *            Hours part of time.
	 *
	 * @param berlinClockBuilder
	 *            Berlin clock string builder.
	 */
	private void buildSecondRowOfBerlinClock(String hours, StringBuilder berlinClockBuilder) {

		Integer hoursPartOfTime = Integer.valueOf(hours);
		int secondRow = hoursPartOfTime == 0 ? 0 : hoursPartOfTime % 5;

		for (int i = 0; i < CHARS_ALLOWED_IN_SECOND_ROW; i++) {
			if (i < secondRow) {
				berlinClockBuilder.append("R");
			} else {
				berlinClockBuilder.append("O");
			}
		}
		berlinClockBuilder.append("\n");
	}

	/**
	 * Builds third row of Berlin clock.It consists of 11 characters with
	 * combination of character "R","Y" and "O". Each character "R" or "Y"
	 * represents 5 minutes and character "R" at position 3,6 and 9 represents
	 * first quarter, half and last quarter of hour.
	 *
	 * @param minutes
	 *            Minutes part of time.
	 *
	 * @param berlinClockBuilder
	 *            Berlin clock string builder.
	 */
	private void buildThirdRowOfBerlinClock(String minutes, StringBuilder berlinClockBuilder) {
		Integer minutesPartOfTime = Integer.valueOf(minutes);
		int thirdRow = minutesPartOfTime == 0 ? 0 : minutesPartOfTime / 5;

		for (int i = 0; i < CHARS_ALLOWED_IN_THIRD_ROW; i++) {
			if (i < thirdRow) {
				if (i == 2 || i == 5 || i == 8) {
					berlinClockBuilder.append("R");
				} else {
					berlinClockBuilder.append("Y");
				}
			} else {
				berlinClockBuilder.append("O");
			}
		}
		berlinClockBuilder.append("\n");
	}

	/**
	 * Builds fourth row of Berlin clock.It consists of 4 characters with
	 * combination of character "Y" and "O". Each character "Y" represents 1
	 * minute.
	 *
	 * @param minutes
	 *            Minutes part of time.
	 *
	 * @param berlinClockBuilder
	 *            Berlin clock string builder.
	 */
	private void buildFourthRowOfBerlinClock(String minutes, StringBuilder berlinClockBuilder) {
		Integer minutesPartOfTime = Integer.valueOf(minutes);
		int fourthRow = minutesPartOfTime == 0 ? 0 : minutesPartOfTime % 5;

		for (int i = 0; i < CHARS_ALLOWED_IN_FOURTH_ROW; i++) {
			if (i < fourthRow) {
				berlinClockBuilder.append("Y");
			} else {
				berlinClockBuilder.append("O");
			}
		}
	}

}