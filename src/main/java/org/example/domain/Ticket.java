package org.example.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {

   private int id;
   private ArrayList<ProductforSale> productforSales;

   private double totalPrice;

   public Ticket(int id, double totalPrice){
      this.id = id;
      this.totalPrice = totalPrice;
   }
   public Ticket(){
      this.id = id;
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

   @Override
   public String toString() {
      return "Ticket{" +
              "id=" + id +
              ", productforSales=" + productforSales +
              ", totalPrice=" + totalPrice +
              '}';
   }
}


