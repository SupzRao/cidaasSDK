package com.cidaassdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * The Class UserAddress contains AddressInfo of the visited places with a tag field indicating if a address is favorite.  
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
 public class UserAddress implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7627832244101065123L;
	
	/** The label of the address. */
	private String label;
	
	/** The address info. */
	private AddressEntity addressInfo;

	/**
	 * The addressType.
	 */
	private AddressType addressType;

	/** The favorite. */
	private boolean favorite;

	/**
	 * Instantiates a new user address.
	 */
	public UserAddress() {
	}

	/**
	 * Instantiates a new user address.
	 *
	 * @param addressInfo
	 *            the address info
	 * @param favorite
	 *            the favorite
	 */
	public UserAddress(String label, AddressEntity addressInfo, boolean favorite) {
		this.label = label;
		this.addressInfo = addressInfo;
		this.favorite = favorite;
		this.addressType = AddressType.UNKNOWN;
	}

	/**
	 * Instantiates a new user address.
	 *
	 * @param addressInfo
	 *            the address info
	 * @param favorite
	 *            the favorite
	 * @param addressType
	 *            the addressType
	 */
	public UserAddress(String label, AddressEntity addressInfo, boolean favorite, AddressType addressType) {
		this.label = label;
		this.addressInfo = addressInfo;
		this.favorite = favorite;
		this.addressType = addressType;
	}

	/**
	 * Gets the address info.
	 *
	 * @return the address info
	 */
	public AddressEntity getAddressInfo() {
		return addressInfo;
	}

	/**
	 * Sets the address info.
	 *
	 * @param addressInfo
	 *            the new address info
	 */
	public void setAddressInfo(AddressEntity addressInfo) {
		this.addressInfo = addressInfo;
	}

	/**
	 * Checks if is favorite.
	 *
	 * @return true, if is favorite
	 */
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 * Sets the favorite.
	 *
	 * @param favorite
	 *            the new favorite
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the addressType
	 */
	public AddressType getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
}
