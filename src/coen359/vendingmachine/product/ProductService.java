package coen359.vendingmachine.product;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import coen359.vendingmachine.product.nutritionalinfo.NutritionalInfo;

public class ProductService {
	private EntityManager manager;
	ProductFactory productFactory;

	public ProductService(EntityManager manager) {
		this.manager = manager;
	}

	// method to create a new record
	public Product createProduct(String id, String name, double price, Date expiryDate, Integer quantity, String type, String imageLink, Integer calorie, Integer fat, Integer sugar) {
		//Product product = new Product();
		
		productFactory= new ProductFactory();
		Product product = productFactory.createProductType(type);
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setExpiryDate(expiryDate);
		product.setQuantity(quantity);
		product.setType(type);
		product.setImageLink(imageLink);		
		
		NutritionalInfo nutritionalInfo = new NutritionalInfo();
		nutritionalInfo.setCalorie(calorie);
		nutritionalInfo.setFat(fat);
		nutritionalInfo.setSugar(sugar);
		product.addNutritionalInfo(nutritionalInfo);
		
		manager.persist(product);
		return product;
	}

	// method to read a record
	public Product readProduct(String id) {
		Product product = manager.find(Product.class, id);
		return product;   	 
	}
        
       

	// method to read all records
	public List<Product> readAll() {
		TypedQuery<Product> query = manager.createQuery("Select p FROM Product p", Product.class);
	
		List<Product> result =  query.getResultList();
		
		for (Object temp : result) {
			System.out.println(temp);
		}

		return result;   	 
	}

	// method to update a record
	public Product updateProduct(String id, String name, double price, Date expiryDate, Integer quantity, String type, String imageLink, Integer calorie, Integer fats, Integer sugar) {
		
		Product product = manager.find(Product.class, id);	
		NutritionalInfo nutritionalInfo = manager.find(NutritionalInfo.class, id);
		
		if (product != null) {
			product.setId(id);
			product.setName(name);
			product.setPrice(price);
			product.setExpiryDate(expiryDate);
			product.setQuantity(quantity);
			product.setType(type);
			product.setImageLink(imageLink);	
			
			if (nutritionalInfo != null){
				
				//NutritionalInfo nutritionalInfo = new NutritionalInfo();
				nutritionalInfo.setCalorie(calorie);
				nutritionalInfo.setFat(fats);
				nutritionalInfo.setSugar(sugar);
				product.addNutritionalInfo(nutritionalInfo); //doubt here. add or update? 
			}
			
		}
		return product;	
}

	// method to delete a record
	public void deleteProduct(String id) {
		Product product = manager.find(Product.class, id);
		NutritionalInfo nutritionalInfo = manager.find(NutritionalInfo.class, id);
		
		if (product != null) {
			manager.remove(product);
			
			if(nutritionalInfo != null){
				manager.remove(nutritionalInfo);
			}
		}
	}
}

