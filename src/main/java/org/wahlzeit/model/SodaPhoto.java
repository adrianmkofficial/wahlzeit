
package org.wahlzeit.model;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;


@Subclass
public class CigarettePhoto extends Photo {
	
	private String brand;
	private String manufacturer;
	
	/**
	 * @MethodType constructor
	 */
	public CigarettePhoto() {
		super();
	}
	
	/**
	 * @MethodType constructor
	 */
	public CigarettePhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * @MethodType get
	 */
	public String getBrand() {
		return this.brand;
	}
	/**
	 * @MethodType get
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	
	/**
	 * @MethodType set
	 */
	public void setBrand(String brand) {
		if(brand != null) {
			this.brand = brand;
		}
	}
	/**
	 * @MethodType set
	 */
	public void setManufacturer(String manufacturer) {
		if(manufacturer != null) {
			this.manufacturer = manufacturer;
		}
	}
	

}
