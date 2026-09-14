package com.cognizant.tradeorderservice.exception;

public class DuplicateResourceException extends RuntimeException{
	public DuplicateResourceException(String message) {
		super(message);
	}
}
