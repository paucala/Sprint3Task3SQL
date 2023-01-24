package org.example.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {

   private int sellId;
   private ArrayList<ProductforSale> productforSales;

   public Ticket(int idVenta){
      this.sellId = idVenta;
      this.productforSales = new ArrayList<>();
   }

   public int getSellId() {
      return sellId;
   }

   public ArrayList<ProductforSale> getProductforSales() {
      return productforSales;
   }
}
