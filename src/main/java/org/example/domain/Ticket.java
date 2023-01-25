package org.example.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {

   private int id;
   private ArrayList<ProductforSale> productforSales;

   private double totalPrice;

   public Ticket(int idVenta){
      this.id = idVenta;
      this.productforSales = new ArrayList<>();
      this.totalPrice = totalPrice;
   }

   public int getId() {
      return id;
   }

   public ArrayList<ProductforSale> getProductforSales() {
      return productforSales;
   }

   public double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(double totalPrice) {
      this.totalPrice = totalPrice;
   }
}


