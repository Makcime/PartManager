/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

/**
 *
 * @author max
 */
public class part {
	private Integer id, inStock;
	private String name, supplier, category, value, sup_ref,
	    new_sup_ref;
	private Double unit_price;
    /**
     * Creates a new instance of part
     */
	public part() {
	} public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSup_ref() {
		return sup_ref;
	}

	public void setSup_ref(String sup_ref) {
		this.sup_ref = sup_ref;
	}

	public String getNew_sup_ref() {
		return new_sup_ref;
	}

	public void setNew_sup_ref(String new_sup_ref) {
		this.new_sup_ref = new_sup_ref;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public String toString() {
		String str;
		str = id.toString() + " - " + inStock.toString() + " - " +
		    name + " - " + supplier + " - " + category + " - " +
		    value + " - " + sup_ref + " - " +
		    unit_price.toString();
		return str;
	}


}
