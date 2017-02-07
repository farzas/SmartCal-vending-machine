package coen359.vendingmachine.product.nutritionalinfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import coen359.vendingmachine.product.Product;

@Entity(name = "Nutritional_Info")
public class NutritionalInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Product product;

    @Column(name = "calorie")
    private Integer calorie;

    @Column(name = "fat")
    private Integer fat;

    @Column(name = "sugar")
    private Integer  sugar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getSugar() {
        return sugar;
    }

    public void setSugar(Integer sugar) {
        this.sugar = sugar;
    }
     
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static int getNumberOfColumns() {
        
		return 4;
    }

	// return the name of column i
    // this method has also been modified to accomodate the  jtable column names
    public static String getColumnName(int i) throws Exception {

    	String colName = null;
        
        if (i == 7) {
            colName = "Calorie";
        } else if (i == 8) {
            colName = "Fat";
        } else if (i == 9) {
            colName = "Sugar";
        } else {
            throw new Exception("Access to invalid column number in NutritionList table");
        }

        return colName;
    }

	@Override
	public String toString() {
		return "NutritionalInfo [id=" + id + ", calorie=" + calorie + ", fat=" + fat + ", sugar=" + sugar
				+ "]";
	}
}

