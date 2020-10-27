package com.imyc.SBAP.Exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and root
	 * cause.
	 *
	 * @param msg the detail message.
	 * @param t root cause
	 */
	public UserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
