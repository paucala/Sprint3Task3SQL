package org.example.domain;

public class Decoration extends Product{
    private String material;

    public Decoration(String name, double price, int quantity, String material) {
        super(name, price, quantity);
        this.material = material;
    }

    public Decoration(int id, String name, double price, int quantity, String material) {
        super(id, name, price, quantity);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoration{" +
                "material = " + material + '\'' +
                ", " + super.toString();
    }
}
