package com.ecommerce.bcruz.dto;

import java.util.List;

public class CheckoutRequest
{
	private Long userId;
	private List<CartItem> itemProducts;
	
	private String email;
    private String firstName;
    private String lastName;

    private String addressLine1;
    private String addressLine2;
    
	private String city;
    private String provinceState;
    private String postalCode;
    private String country;
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	public List<CartItem> getItemProducts() { return itemProducts; }
	public void setItemProducts(List<CartItem> itemProducts) { this.itemProducts = itemProducts; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getAddressLine1() { return addressLine1; }
	public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
	public String getAddressLine2() { return addressLine2; }
	public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getProvinceState() { return provinceState; }
	public void setProvinceState(String provinceState) { this.provinceState = provinceState; }
	public String getPostalCode() { return postalCode; }
	public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }
}
