package com.ubs.opsit.interviews;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 6475177928724367521L;

	public BusinessException() {
		this("Programming Error");
	}

	/**
	 * Business exception for any business condition failure
	 *
	 * @param message
	 *            Message to be shown to user.
	 */
	public BusinessException(final String message) {
		super(message);
	}

}
