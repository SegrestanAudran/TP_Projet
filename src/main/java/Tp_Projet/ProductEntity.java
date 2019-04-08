/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp_Projet;

/**
 *
 * @author Yasmina
 */
public class ProductEntity {
    private int product_id;
	private String name;

	public ProductEntity(int product_id, String name) {
		this.product_id = product_id;
		this.name = name;
	}
        
        /**
	 * Get the value of customerId
	 *
	 * @return the value of customerId
	 */
	public int getProduct_id() {
		return product_id;
	}
        /**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}
}
