package org.hannes.util;

/**
 * FIXME: cheap hack
 * 
 * @author Hannes
 *
 */
public class Country {

	/**
	 * 
	 */
	private String ID;
	
	/**
	 * 
	 */
	private String CC1;
	
	/**
	 * 
	 */
	private String CC2;
	
	/**
	 * 
	 */
	private String CC3;
	
	/**
	 * 
	 */
	private String CC4;
	
	/**
	 * 
	 */
	private String IPR;

	public Country(String id, String cc1, String cc2, String cc3, String cc4, String ipr) {
		this.ID = id;
		this.CC1 = cc1;
		this.CC2 = cc2;
		this.CC3 = cc3;
		this.CC4 = cc4;
		this.IPR = ipr;
	}

	/**
	 * Generates a default country
	 */
	public Country() {
		this.IPR = "1201";
		this.ID = "223";
		this.CC1 = "0";
		this.CC2 = "0";
		this.CC3 = "0";
		this.CC4 = "2147483648";
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCC1() {
		return CC1;
	}

	public void setCC1(String cC1) {
		CC1 = cC1;
	}

	public String getCC2() {
		return CC2;
	}

	public void setCC2(String cC2) {
		CC2 = cC2;
	}

	public String getCC3() {
		return CC3;
	}

	public void setCC3(String cC3) {
		CC3 = cC3;
	}

	public String getCC4() {
		return CC4;
	}

	public void setCC4(String cC4) {
		CC4 = cC4;
	}

	public String getIPR() {
		return IPR;
	}

	public void setIPR(String iPR) {
		IPR = iPR;
	}

}