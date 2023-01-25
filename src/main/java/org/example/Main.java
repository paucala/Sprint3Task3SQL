package org.example;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.domain.*;
import org.example.repository.Repository;
import org.example.service.Serv;
import org.example.service.Service;

public class Main {

	private static Serv service;
	private static Scanner inputKey;	
	private static String shopName;
	private static List<Product> products = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		wellcome();
    }
	
	public static void wellcome () {
		
		service = new Service();
		shopName = "Floreria";   //service.init();  // sacar el harcodeo cuando haga la integracion
		if(shopName.isEmpty()) {
			shopName = captureString("Insert Flower Shop name");			
		}	
		
		System.out.println("Bienvenido al sistema de gestion de Floristeria" + shopName + "\n");
		mainMenu();
	}
	
	public static void mainMenu() {
	
		System.out.println("\n" + "** Main menu **" + "\n");
		System.out.println("1. Product");
		System.out.println("2. Invoice");
		System.out.println("3. Exit");
		
		int choice = captureNumber("Select task: " + "\n");

		if(choice > 3) {
			System.out.println("Option not supported");
			mainMenu();
		} else if(choice == 1) {
			productMenu();
		} else if (choice == 2) {
			// TODO
		} else if (choice == 3) {
			// TODO
		}
		
		/*
		 * Stock menues
		 */

	}	
	public static void productMenu() {
		System.out.println("\n" + "** Product menu **" + "\n");
		System.out.println("1. Create product");
		System.out.println("2. List products");
		System.out.println("3. Valuate stock");
		System.out.println("4. Back");
		
		int choice = captureNumber("Select task: " + "\n");
		
		switch (choice){
			
			case 1 : productCreate();
					 break;
			case 2 : productList();
					 break;
			case 3 : productSum();
					 break;
			case 4 : wellcome();
					 break;
		}	
	}
	
	private static void productSum() {
		
		double totalStock = service.sumStock();
		System.out.println("\n" + "** Stock ammount menu **" + "\n");
		System.out.println("The stock ammount is: " + totalStock + "\n");
		mainMenu();
	}

	private static void productList() {

		products = service.getAllProducts();
		products.forEach(System.out::println);
		System.out.println("\n");
		mainMenu();
	}

	private static void productCreate() {
		
		System.out.println("\n" + "** Stock creation menu **" + "\n");
		System.out.println("1. Decoration");
		System.out.println("2. Flower");
		System.out.println("3. Tree");
		System.out.println("4. Back");
		
		int choice = captureNumber("Select task: ");
		
		switch (choice){
			
			case 1 : decorationCreate();
					 break;
			case 2 : flowerCreate();
					 break;
			case 3 : treeCreate();
					 break;
			case 4 : productMenu();
					 break;
		}
	}

	private static void decorationCreate() {
		
		System.out.println("Create decoration product "+ "\n");
		String name =  captureString("Name: ");
//		if(!service.productExist(name){
			String material = captureString("Material: ");
			int price = (int) captureDouble("price: ");  // cuando ajustemos el tipo de variable de producto volver a poner double
			int quantity = captureNumber("quantity: ");
			
			// Cambiar price a double en product
			Product decoration = new Decoration(name, price, quantity, material);
			service.createProduct(decoration);			
			System.out.println("The product has been created" + "\n" );
//		}else {
			System.out.println("The product already exists" + "\n" );
		}
		
		wellcome();
//	}

	private static void flowerCreate() {
		
		System.out.println("Create product flower"+ "\n");
		String name =  captureString("Name: ");
		String color = captureString("Color: ");
		double price = captureDouble("price: ");
		int quantity = captureNumber("quantity: ");
		
		// Cambiar price a double en product
		//Product decoration = new Flower (name, price, quantity, color);
		//service.createProduct(decoration);
		
	}
	
	private static void treeCreate() {
		
		System.out.println("Create product tree "+ "\n");
		String name =  captureString("name: ");
		double high = captureDouble("heigth: ");
		double price = captureDouble("price: ");
		int quantity = captureNumber("quantity: ");
		
		// Cambiar price a double en product
		// Product tree = new Tree(name, price, quantity, high);
		// service.createProduct(tree);
	}

	/*
	 * Invoice menues
	 */

	public static void invoiceMenu() {
		
		System.out.println("\n" + "** Invoice menu **" + "\n");
		System.out.println("1. Create invoice");
		System.out.println("2. Total ammount of sales");
		System.out.println("3. Back");
		
		int choice = captureNumber("Select task: " + "\n");
		
		switch (choice){
			
			case 1 : invoiceCreate();
					 break;
			case 2 : invoiceSum();
					 break;
			case 3 : wellcome();
					 break;
		}	
	}
	
	private static void invoiceCreate() {
		// TODO Auto-generated method stub
		
	}
	
	private static void invoiceSum() {
		// TODO Auto-generated method stub
		
	}

	

	// Metodos auxiliares para el ingreso de datos numericos y alfanumericos por keyboard
	
	public static  String captureString(String mensaje) {
		inputKey = new Scanner(System.in);
		System.out.println(mensaje);
		return inputKey.nextLine();
	}

	public static int captureNumber(String mensaje) {
		inputKey = new Scanner(System.in);
		Integer datoNumerico;

		do {
			System.out.println(mensaje);
			try {
				datoNumerico = Integer.parseInt(inputKey.nextLine());
			} catch (Exception e) {
				System.out.println("El valor ingresado no es un numero. Intente nuevamente ");
				datoNumerico = -1;
			}

		} while (datoNumerico == -1);
		return datoNumerico;
	}

	public static double captureDouble(String mensaje) {
	Double datoDouble;

		do {
			System.out.println(mensaje);
			try {
				datoDouble = Double.parseDouble(inputKey.nextLine());
			} catch (Exception e) {
				System.out.println("El valor ingresado no es un numero. Intente nuevamente ");
				datoDouble = -1.0;
			}

		} while (datoDouble == -1.0);
		return datoDouble;
	}
}