/**
 * 
 */
package com.cidaassdk;

/**
 * @author Anshul
 *
 */
public enum AddressType {
	HOME("HOME"), 
	OFFICE("OFFICE"), 
	SCHOOL("SCHOOL"), 
	HOSPITAL("HOSPITAL"), 
	GASSTATION("GASSTATION"), 
	SERVICESTATION("SERVICESTATION"), 
	UNKNOWN("UNKNOWN");
	
	private String addressType;
	
	private AddressType(String addressType){
		this.setAddressType(addressType);
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
