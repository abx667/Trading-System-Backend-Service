package com.cognizant.tradingcompany.exception;

public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
	   super(message);
   }
}
