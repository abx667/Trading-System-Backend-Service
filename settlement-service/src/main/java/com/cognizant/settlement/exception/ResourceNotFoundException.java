package com.cognizant.settlement.exception;

public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
	   super(message);
   }
}
