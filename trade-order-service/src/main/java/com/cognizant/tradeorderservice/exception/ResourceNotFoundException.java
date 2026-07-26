package com.cognizant.tradeorderservice.exception;

public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
	   super(message);
   }
}
