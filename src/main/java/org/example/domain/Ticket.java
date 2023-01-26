package org.example.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ticket {

   private int id;
   private List<ProductforSale> productforSales;

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


   public Ticket(List<ProductforSale> productforSales) {
	this.productforSales = productforSales;
}
public int getId() {
      return id;
   }

   public List<ProductforSale> getProductforSales() {
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


