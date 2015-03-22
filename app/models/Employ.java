package models;

import java.util.ArrayList;
import java.util.List;
 

public class Employ
{
	// Data sample
	private static List<Employ> employs;
	  static {
		  employs = new ArrayList<Employ>();
		  employs.add(new Employ("E1","Ta","01666606058","address1",1,"B1","K2","H1"));
		  employs.add(new Employ("E2","Tuong","0987736812","address2",1,"B2","K1","H1"));
		  employs.add(new Employ("E3","Tuan","0987362772","address3",1,"B1","K1","H2"));
	  }
	  
	protected String id;
	protected String name;
	protected String phone_number;
	protected String address;
	protected int gender;
	protected String bomon_id;
	protected String khoa_id;
	protected String hopdong_id;
	
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
	public Employ(String id, String name, String phone_number, String address,
			int gender, String bomon_id, String khoa_id, String hopdong_id) {
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
	public String getPhone_number() {
		return phone_number;
	}


	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhone_number(String phone_number) {
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
	public String getBomon_id() {
		return bomon_id;
	}


	/**
	 * @param bomon_id the bomon_id to set
	 */
	public void setBomon_id(String bomon_id) {
		this.bomon_id = bomon_id;
	}


	/**
	 * @return the khoa_id
	 */
	public String getKhoa_id() {
		return khoa_id;
	}


	/**
	 * @param khoa_id the khoa_id to set
	 */
	public void setKhoa_id(String khoa_id) {
		this.khoa_id = khoa_id;
	}


	/**
	 * @return the hopdong_id
	 */
	public String getHopdong_id() {
		return hopdong_id;
	}


	/**
	 * @param hopdong_id the hopdong_id to set
	 */
	public void setHopdong_id(String hopdong_id) {
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
	
	// Begin function for model
	
	/**
	 * Return all of employs
	 * @return employs
	 */
	public static List<Employ> findAll() {
		return new ArrayList<Employ>(employs);
	}
	
	/**
	 * Return Employ by ID
	 * @param id
	 * @return
	 */
	public static Employ findById(String id) {
		for (Employ candicate : employs) {
			if (candicate.id.equals(id)) {
				return candicate;
			}
		}
		return null;
	}
	
	/**
	 * Return Employ by name
	 * @param name
	 * @return
	 */
	public static List<Employ> findByName(String name) {
	    final List<Employ> results = new ArrayList<Employ>();
	    for (Employ candidate : employs) {
	      if (candidate.name.toLowerCase().contains(name.toLowerCase())) {
	        results.add(candidate);
	      }
	    }
	    return results;
	  }
	 
	/**
	 * Remove employ
	 * @param Employ
	 * @return
	 */
	  public static boolean remove(Employ Employ) {
	    return employs.remove(Employ);
	  }
	 
	  /**
	   * save employ
	   */
	  public void save() {
	    employs.remove(findById(this.id));
	    employs.add(this);
	  }
}