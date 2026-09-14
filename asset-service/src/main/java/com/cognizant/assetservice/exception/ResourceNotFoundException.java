package com.cognizant.assetservice.exception;

public class ResourceNotFoundException extends RuntimeException{
   public ResourceNotFoundException(String message) {
	   super(message);
   }
}
