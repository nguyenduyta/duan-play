package models;

import play.data.validation.Constraints;

public class Instead {

	public String id;
	@Constraints.Required
	public String name;
	public Employ employ;
	/**
	 * 
	 */
	public Instead() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param employ
	 */
	public Instead(String id, String name, Employ employ) {
		super();
		this.id = id;
		this.name = name;
		this.employ = employ;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}