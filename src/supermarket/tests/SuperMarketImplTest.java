package supermarket.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supermarket.dao.SuperMarket;
import supermarket.dao.SuperMarketImpl;
import supermarket.model.Product;

class SuperMarketImplTest {
	SuperMarket market;
	Product[] products = new Product[19];
	LocalDate now = LocalDate.now();
	
	
	

	@BeforeEach
	void setUp() throws Exception {
		market = new SuperMarketImpl();
		products[0] = new Product(1234567890123L, "Chocolate Bar", "Sweets", "SweetNature", 1.99, LocalDate.of(2025, 10, 15));
		products[1] = new Product(1234567890124L, "Coca-Cola 0.5L", "Beverages", "Coca-Cola", 1.29, LocalDate.of(2024, 12, 1));
		products[2] = new Product(1234567890125L, "Sour Cream Chips", "Snacks", "Crunchy", 2.49, LocalDate.of(2025, 6, 30));
		products[3] = new Product(1234567890126L, "Pasta with Tomato Sauce", "Groceries", "PastaMaster", 3.99, LocalDate.of(2026, 2, 20));
		products[4] = new Product(1234567890127L, "Orange Juice 1L", "Beverages", "FreshJuice", 1.79, LocalDate.of(2025, 5, 10));
		products[5] = new Product(1234567890128L, "Chicken Breasts", "Meat", "FarmFresh", 5.99, LocalDate.of(2024, 11, 25));
		products[6] = new Product(1234567890129L, "Red Apples", "Fruits", "SweetNature", 3.50, LocalDate.of(2025, 8, 15));
		products[7] = new Product(1234567890130L, "Salted Almonds", "Snacks", "NuttyDelight", 4.99, LocalDate.of(2026, 3, 5));
		products[8] = new Product(1234567890131L, "Black Tea 100g", "Beverages", "TeaTime", 2.99, LocalDate.of(2025, 7, 22));
		products[9] = new Product(1234567890132L, "Milk 1L", "Dairy", "DairyFarm", 1.15, LocalDate.of(2024, 11, 10));
		products[10] = new Product(1234567890133L, "Fresh Cucumbers", "Vegetables", "GreenField", 0.99, LocalDate.of(2025, 4, 1));
		products[11] = new Product(1234567890134L, "Whole Wheat Bread", "Bakery", "Baker's Best", 2.50, LocalDate.of(2024, 9, 30));
		products[12] = new Product(1234567890135L, "Greek Yogurt", "Dairy", "YogurtLand", 1.89, LocalDate.of(2025, 12, 5));
		products[13] = new Product(1234567890136L, "Olive Oil 500ml", "Cooking", "HealthyChoice", 7.49, LocalDate.of(2024, 1, 15));
		products[14] = new Product(1234567890137L, "Granola Bars", "Snacks", "NutriBars", 3.29, LocalDate.of(2025, 11, 20));
		products[15] = new Product(1234567890138L, "Frozen Peas 500g", "Frozen Foods", "GreenGrove", 2.10, LocalDate.of(2025, 10, 10));
		products[16] = new Product(1234567890139L, "Basmati Rice 1kg", "Groceries", "RiceWorld", 4.20, LocalDate.of(2022, 4, 5));
		products[17] = new Product(1234567890140L, "Cereal 500g", "Breakfast", "CrispyMornings", 3.00, LocalDate.of(2025, 8, 25));
		products[18] = new Product(1234567890141L, "Honey 250g", "Condiments", "SweetNature", 5.50, LocalDate.of(2023, 3, 1));
		for (int i =0 ; i < products.length - 1; i++) {
			market.addProduct(products[i]);
		}
	}

	@Test
	void testAddProduct() {
		assertFalse(market.addProduct(null));
		assertFalse(market.addProduct(products[0]));
		assertEquals(18,market.skuQuantity());
		assertTrue(market.addProduct(products[18]));
		assertEquals(19,market.skuQuantity());	
		assertTrue(market.addProduct(new Product(1234567890142L, "Greek Yogurt", "Dairy", "YogurtLand", 0.99, LocalDate.of(2025, 12, 5))));
		assertEquals(20,market.skuQuantity());
		assertTrue(market.addProduct(new Product(1234567890143L, "Greek Yogurt", "Dairy", "YogurtLand", 0.99, LocalDate.of(2025, 12, 5))));
		assertEquals(21,market.skuQuantity());
		
	}

	@Test
	void testRemoveProduct() {
		assertNull(market.removeProduct(1234567890128321332L));
		assertEquals(18, market.skuQuantity());
		assertNotNull(market.removeProduct(1234567890128L));
		assertEquals(17, market.skuQuantity());
	}

	@Test
	void testFindByBarCode() {
		assertNull(market.removeProduct(1234567890128321332L));
		assertNotNull(market.removeProduct(1234567890137L));
	}

	@Test
	void testFindByCategory() {
	    Product[] actual = market.findByCategory("Snacks");
	    Product[] expected = {products[2], products[7], products[14]};
	   
	    assertArrayEquals(expected, actual);
	}


	@Test
	void testFindByBrand() {
		Product[] actual = market.findByBrand("SweetNature");
		Product[] expected = {products[0],products[6]};
		assertArrayEquals(expected, actual);
		assertEquals(18, market.skuQuantity());
		market.addProduct(products[18]);
		assertEquals(19, market.skuQuantity());
		Product[] actual1 = market.findByBrand("SweetNature");
		Product[] expected1 = {products[0],products[6], products[18]};
		assertArrayEquals(expected1, actual1);
	}

	@Test
	void testFindProductsWithExpiredDate() {
		Product[] actual = market.findProductsWithExpiredDate();
		 System.out.println("Actual expired products: " + Arrays.toString(actual));
		Product[] expected = {products[11],products[13],products[16]};
		
		assertArrayEquals(expected, actual);
		assertEquals(18, market.skuQuantity());
		
		market.addProduct(products[18]);
		Product[] actual1 = market.findProductsWithExpiredDate();
		Product[]expected1 = {products[11],products[13],products[16],products[18]};
		assertArrayEquals(expected1,actual1);
		assertEquals(19, market.skuQuantity());
		
	}

	@Test
	void testSkuQuantity() {
		assertEquals(18, market.skuQuantity());
	}

}
