package supermarket.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

import supermarket.model.Product;

public class SuperMarketImpl implements SuperMarket {
	private Product[] products; 
	private static final int INITIAL_CAPACITY = 20;
	private int skuQuantity;
	LocalDateTime date = LocalDateTime.now();
	
	public SuperMarketImpl() {
		products = new Product[INITIAL_CAPACITY];
	}
	@Override
	public boolean addProduct(Product product) {
		if (product == null || findByBarCode(product.getBarCode()) != null) {
			return false;
		}
		 if (skuQuantity == products.length) {
		        products = Arrays.copyOf(products, products.length * 2);
		    }
		 
		products[skuQuantity++] = product;
		return true;
	}

	@Override
	public Product removeProduct(long barCode) {
		for (int i = 0; i < skuQuantity; i++) {
			if(products[i].getBarCode() == barCode) {
				Product removedProduct = products[i];
				
				System.arraycopy(products, i + 1, products , i , skuQuantity - i - 1);
				
				products[--skuQuantity] = null;
				return removedProduct;
			}
		}
		return null;
	}

	@Override
	public Product findByBarCode(long barCode) {
		 for (int i = 0; i < skuQuantity; i++) {
		        if (products[i].getBarCode() == barCode) { 
		            return products[i]; 
		        }
		    }
		    return null;  
	}

	@Override
	public Product[] findByCategory(String category) {
		
		return findProductByPredicate(p -> p.getCategory() == category);
	}

	@Override
	public Product[] findByBrand(String brand) {
		
		return  findProductByPredicate(p -> p.getBrand() == brand);
	}

	@Override
	public Product[] findProductsWithExpiredDate() {
	   return findProductByPredicate(p -> p.getExpDate().isBefore(LocalDate.now()));
	 
	    
	}
	private Product[] findProductByPredicate(Predicate<Product> predicate) {
		Product[]res  = new Product[skuQuantity];
		int j = 0;
		for (int i = 0; i < skuQuantity; i++) {
			if(predicate.test(products[i])) {
				res[j++] = products[i];
			}
			
		}
		return Arrays.copyOf(res, j);
	}

	@Override
	public int skuQuantity() {
		  return skuQuantity;
				}

}
