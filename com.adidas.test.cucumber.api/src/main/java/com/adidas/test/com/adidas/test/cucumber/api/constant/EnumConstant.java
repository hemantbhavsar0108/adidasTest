package com.adidas.test.com.adidas.test.cucumber.api.constant;

public class EnumConstant {
	
	public enum PetOption {
	      AVAILABLE("available"),
	      PENDING("pending"),
	      SOLD("sold");
	      
	     private String name;

	     PetOption(String name) {
	          this.name = name;
	      }

	      public String toString() {
	          return name;
	      }
	  }

}
