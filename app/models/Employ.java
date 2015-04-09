package models;

import play.data.validation.Constraints;
import java.util.ArrayList;
import java.util.List;
 

public class Employ
{
	// Data sample
	private static List<Employ> employs;
	static {
		employs = new ArrayList<Employ>();
//		employs.add(new Employ("E1", "Ta", "01/29/02", 1, "09299192029",
//				"tand@gmail.com", "workplace1", true, "D1", "A1", "C1"));
//		employs.add(new Employ("E2", "Ta", "01/29/02", 1, "09299192029",
//				"tand@gmail.com", "workplace1", true, "D1", "A1", "C1"));
//		employs.add(new Employ("E3", "Ta", "01/29/02", 1, "09299192029",
//				"tand@gmail.com", "workplace1", true, "D1", "A1", "C1"));
//		employs.add(new Employ("E4", "Ta", "01/29/02", 1, "09299192029",
//				"tand@gmail.com", "workplace1", true, "D1", "A1", "C1"));
//		employs.add(new Employ("E5", "Ta", "01/29/02", 1, "09299192029",
//				"tand@gmail.com", "workplace1", true, "D1", "A1", "C1"));
	}

	@Constraints.Required
	public String id;
	@Constraints.Required
	public String name;
	@Constraints.Required
	public String birthday;
	public int gender;
	@Constraints.Required
	public String phone_number;
	@Constraints.Required
	public String degree; // học vị: Cử nhân/thạc sĩ/ tiến sĩ
	@Constraints.Required
	public String workplace;
	@Constraints.Required
	public boolean admin;
	@Constraints.Required
	public Dept dept = new Dept();
	@Constraints.Required
	public Academic academic = new Academic();
	@Constraints.Required
	public Contract contract = new Contract();

	/**
	 * 
	 */
	public Employ() {
		super();
	}


	/**
	 * @param id
	 * @param name
	 * @param birthday
	 * @param gender
	 * @param phone_number
	 * @param degree
	 * @param workplace
	 * @param admin
	 * @param dept
	 * @param academic
	 * @param contract
	 */
	public Employ(String id, String name, String birthday, int gender,
			String phone_number, String degree, String workplace, boolean admin,
			Dept dept, Academic academic, Contract contract) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday
		this.gender = gender
		this.phone_number = phone_number;
		this.degree = degree;
		this.workplace = workplace;
		this.admin = admin;
		this.dept = dept;
		this.academic = academic;
		this.contract = contract;
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
	 * @return the degree
	 */
	public String getdegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setdegree(String degree) {
		this.degree = degree;
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

	 * @return the dept
	 */
	public Dept getDept() {
		return dept;
	}


	/**
	 * @param dept the dept to set
	 */
	public void setDept(Dept dept) {
		this.dept = dept;
	}


	/**
	 * @return the academic
	 */
	public Academic getAcademic() {
		return academic;
	}


	/**
	 * @param academic the academic to set
	 */
	public void setAcademic(Academic academic) {
		this.academic = academic;
	}


	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
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