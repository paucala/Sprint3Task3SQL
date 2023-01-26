package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.example.domain.Decoration;
import org.example.domain.Flower;
import org.example.domain.Product;
import org.example.domain.ProductforSale;
import org.example.domain.Tree;
import org.example.service.Serv;
import org.example.service.Service;

public class Main {

	// region variables
	private static Serv service;
	private static Scanner inputKey;
	private static String shopName;
	private static List<Product> products = new ArrayList<>();
	// end region variables
	
	public static void main(String[] args) {

		wellcome();
	}

	public static void wellcome() {

		service = new Service();
		shopName = service.init();
		if (shopName.isEmpty()) {
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

		if (choice > 3) {
			System.out.println("Option not supported");
			mainMenu();
		} else if (choice == 1) {
			productMenu();
		} else if (choice == 2) {
			invoiceMenu();
		} else if (choice == 3) {
			System.exit(0);
		}

		/*
		 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Stock menues  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		 */

	}

	public static void productMenu() {
		System.out.println("\n" + "** Product menu **" + "\n");
		System.out.println("1. Create product");
		System.out.println("2. List products");
		System.out.println("3. Valuate stock");
		System.out.println("4. Back");

		int choice = captureNumber("Select task: " + "\n");

		switch (choice) {

		case 1:
			productCreate();
			break;
		case 2:
			productList();
			break;
		case 3:
			productSum();
			break;
		case 4:
			wellcome();
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

		System.out.println("\n" + "** Stock list **" + "\n");
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

		switch (choice) {

		case 1:
			decorationCreate();
			break;
		case 2:
			flowerCreate();
			break;
		case 3:
			treeCreate();
			break;
		case 4:
			productMenu();
			break;
		}
	}

	private static void decorationCreate() {

		System.out.println("Create decoration product " + "\n");
		String name = captureString("Name: ");
		String material = captureString("Material: ");
		double price = captureDouble("price: ");
		int quantity = captureNumber("quantity: ");
		Product decoration = new Decoration(name, price, quantity, material);
		boolean productExist = service.createProduct(decoration);

		if (!productExist) {
			System.out.println("The product has been created" + "\n");
		} else if (productExist) {
			System.out.println("The product already exists" + "\n");
		} else {
			System.out.println("Hubo un problema al crear el producto. Por favor verifique el stock.");
		}
		
		mainMenu();
	}

	private static void flowerCreate() {

		System.out.println("Create product flower" + "\n");
		String name = captureString("Name: ");
		String color = captureString("Color: ");
		double price = captureDouble("price: ");
		int quantity = captureNumber("quantity: ");

		Product flower = new Flower(name, price, quantity, color);
		boolean productExist = service.createProduct(flower); 

		if (!productExist) {

			System.out.println("The product has been created" + "\n");
		} else if (productExist) {
			System.out.println("The product already exists" + "\n");
		} else {
			System.out.println("Hubo un problema al crear el producto. Por favor verifique el stock.");
		}
		mainMenu();

	}

	private static void treeCreate() {

		System.out.println("Create product tree " + "\n");
		String name = captureString("name: ");
		double high = captureDouble("heigth: ");
		double price = captureDouble("price: ");
		int quantity = captureNumber("quantity: ");

		Product tree = new Tree(name, price, quantity, high);
		boolean productExist = service.createProduct(tree); 

		if (!productExist) {

			System.out.println("The product has been created" + "\n");
		} else if (productExist) {
			System.out.println("The product already exists" + "\n");
		} else {
			System.out.println("Hubo un problema al crear el producto. Por favor verifique el stock.");
		}
		mainMenu();

	}

	/*
	 * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Invoice menues  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	 */

	public static void invoiceMenu() {

		System.out.println("\n" + "** Invoice menu **" + "\n");
		System.out.println("1. Create invoice");
		System.out.println("2. Total ammount of sales");
		System.out.println("3. Back");

		int choice = captureNumber("Select task: " + "\n");

		switch (choice) {

		case 1:
			invoiceCreate();
			break;
		case 2:
			invoiceSum();
			break;
		case 3:
			wellcome();
			break;
		}
	}

	private static void invoiceCreate() {
		Integer selection = 0;
		int order = 0;
		double totalAmmount = 0;
		products = service.getAllProducts(); //
		Map<Integer, Product> productsToShow = new HashMap<>();
		List<ProductforSale> ticketDetail = new ArrayList<>();
		System.out.println("\n" + "** Create ticket **" + "\n");
		System.out.println("Products to include in ticket: " + "\n");

		for (Product product : products) {
			order++;
			productsToShow.put(order, product);
			System.out.println("id: " + order + " name: " + product.getName() + " price: " + product.getPrice());
		}

		do {
			selection = captureNumber("Select product from list or 0 to finish: " + "\n");
			if (selection <= products.size() && selection > 0) {
				int productQuantity = captureNumber("Quantity: " + "\n");
				Product productSelected = productsToShow.get(selection);
				ProductforSale productForSale = new ProductforSale(productSelected, productQuantity);
				ticketDetail.add(productForSale);
				totalAmmount = totalAmmount + productSelected.getPrice() * productQuantity;
			} else {
				System.out.println("Product not included in the list, try again");
			}
		} while (selection != 0);

		if (!ticketDetail.isEmpty()) {

			System.out.println("\n" + "** Ticket detail **" + "\n");

			for (ProductforSale productforSale : ticketDetail) {
				System.out.println(productforSale.getProduct() + " " + productforSale.getQuantity());
			}
			System.out.println("\n" + "Total ammount: " + totalAmmount + "\n");

			service.createTicket(ProductforSale); // TODO verificar el parámetro que le paso a servicio
			
			mainMenu();
		}

	}

	private static void invoiceSum() {

		System.out.println("\n" + "** Total sales ammount:  **" + "\n");
		System.out.println(service.sumSell());
		System.out.println("\n");
		mainMenu();

	}

	// Metodos auxiliares para el ingreso de datos numericos y alfanumericos por
	// keyboard

	public static String captureString(String mensaje) {
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