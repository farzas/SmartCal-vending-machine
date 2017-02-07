package coen359.vendingmachine.product;

public class ProductFactory {
	
	public Product createProductType (String type) {
		
		if(type.equals("Sandwich"))
			return new Sandwich();
		
		else if(type.equals("Beverage"))
			return new Beverage();
		
		else if(type.equals("Candy"))
			return new Candy();
		
		else
			return new Product();
	}
}








