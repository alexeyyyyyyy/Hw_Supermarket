package supermarket.main;

import supermarket.dao.SuperMarketImpl;
import supermarket.model.Product;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        SuperMarketImpl supermarket = new SuperMarketImpl();

        
        supermarket.addProduct(new Product(1234567890123L, "Chocolate Bar", "Sweets", "SweetNature", 1.99, LocalDate.of(2025, 10, 15)));
        supermarket.addProduct(new Product(1234567890124L, "Milk 1L", "Dairy", "DairyFarm", 1.15, LocalDate.of(2023, 10, 01))); 
        supermarket.addProduct(new Product(1234567890125L, "Sour Cream Chips", "Snacks", "Crunchy", 2.49, LocalDate.of(2025, 6, 30)));
        supermarket.addProduct(new Product(1234567890127L, "Milk 1L", "Dairy", "DairyFarm", 1.15, LocalDate.of(2021, 10, 01)));
        supermarket.addProduct(new Product(1234567890126L, "Milk 1L", "Dairy", "DairyFarm", 1.15, LocalDate.of(2022, 10, 01)));
        
        System.out.println("All products:");
        for (int i = 0; i < supermarket.skuQuantity(); i++) {
            Product product = supermarket.findByBarCode(1234567890123L + i); 
            if (product != null) {
                System.out.println(product);
            }
        }

        
        System.out.println("\nExpired products:");
        Product[] expiredProducts = supermarket.findProductsWithExpiredDate();
        for (Product product : expiredProducts) {
            System.out.println(product);
        }
    }
}
