package io.swagger.db;



public class SingletonObject {

	   //create an object of SingleObject
	   private static SingletonObject instance = new SingletonObject();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private SingletonObject(){}

	   //Get the only object available
	   public static SingletonObject getInstance(){
	      return instance;
	   }

	   public void showMessage(){
	      System.out.println("Hello World!");
	   }
	}
