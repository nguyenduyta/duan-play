package models;

public class Employ
{
	protected String id;
	protected String name;
	protected int phone_number;
	protected String address;
	protected int gender;
	protected int bomon_id;
	protected int khoa_id;
	protected int hopdong_id;
	
	/**
	 * 
	 */
	public Employ() {
		super();
	}


	/**
	 * @param id
	 * @param name
	 * @param phone_number
	 * @param address
	 * @param gender
	 * @param bomon_id
	 * @param khoa_id
	 * @param hopdong_id
	 */
	public Employ(String id, String name, int phone_number, String address,
			int gender, int bomon_id, int khoa_id, int hopdong_id) {
		super();
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
		this.gender = gender;
		this.bomon_id = bomon_id;
		this.khoa_id = khoa_id;
		this.hopdong_id = hopdong_id;
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


	/**
	 * @return the phone_number
	 */
	public int getPhone_number() {
		return phone_number;
	}


	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}


	/**
	 * @return the bomon_id
	 */
	public int getBomon_id() {
		return bomon_id;
	}


	/**
	 * @param bomon_id the bomon_id to set
	 */
	public void setBomon_id(int bomon_id) {
		this.bomon_id = bomon_id;
	}


	/**
	 * @return the khoa_id
	 */
	public int getKhoa_id() {
		return khoa_id;
	}


	/**
	 * @param khoa_id the khoa_id to set
	 */
	public void setKhoa_id(int khoa_id) {
		this.khoa_id = khoa_id;
	}


	/**
	 * @return the hopdong_id
	 */
	public int getHopdong_id() {
		return hopdong_id;
	}


	/**
	 * @param hopdong_id the hopdong_id to set
	 */
	public void setHopdong_id(int hopdong_id) {
		this.hopdong_id = hopdong_id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employ [" + (name != null ? "name=" + name + ", " : "")
				+ "phone_number=" + phone_number + ", "
				+ (address != null ? "address=" + address : "") + "]";
	}
	
	
}