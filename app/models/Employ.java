package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
 
@Entity
public class Employ extends Model
{
    // Data sample
    private static List<Employ> employs;
    static {
        employs = new ArrayList<Employ>();
        employs.add(new Employ(1L,"Ta","Ha Noi","tandcntt@gmail.com",new Date(),
            "01666606058","Nam","xxx","xxx",true,"xxx","xxx", "xxx"));
        employs.add(new Employ(2L,"Ta","Ha Noi","tandcntt2@gmail.com",new Date(),
            "01666606058","Nam","xxx","xxx",true,"xxx","xxx", "xxx"));
    }

    @Id
    @Constraints.Min(1)
    public Long id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String address;
    @Constraints.Required
    public String email;
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date birthday = new Date();
    @Constraints.Required
    public String phone_number;
    @Constraints.Required
    public String gender;
    @Constraints.Required
    public String degree;
    @Constraints.Required
    public String workplace;
    @Constraints.Required
    public boolean admin;
    @Constraints.Required
    public String dept;
    @Constraints.Required
    public String academic;
    @Constraints.Required
    public String contract;

    public static Finder<Long,Employ> find = new Finder<Long,Employ>(
        Long.class, Employ.class
    );

    /**
     * 
     */
    public Employ() {
        super();
    }


    /**
     * @param id
     * @param name
     * @param address
     * @param email
     * @param birthday
     * @param phone_number
     * @param gender
     * @param degree
     * @param workplace
     * @param admin
     * @param dept
     * @param academic
     * @param contract
     */
    public Employ(Long id, String name, String address, String email,Date birthday, String phone_number, String gender,
        String degree, String workplace, boolean admin, String dept, String academic, String contract) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.gender = gender;
        this.degree = degree;
        this.workplace = workplace;
        this.dept = dept;
        this.academic = academic;
        this.contract = contract;   
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return this.name;
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
        return this.phone_number;
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
        return this.address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the degree
     */
    public String getdegree() {
        return this.degree;
    }

    /**
     * @param degree
     */
    public void setdegree(String degree) {
        this.degree = degree;
    }


    /**
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }


    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


     /**
     * @return the dept
     */
    public String getDept() {
        return this.dept;
    }

     /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

     /**
     * @return the academic
     */
    public String getAcademic() {
        return this.academic;
    }

     /**
     * @param academic the academic to set
     */
    public void setAcademic(String academic) {
        this.academic = academic;
    }

     /**
     * @return the contract
     */
    public String getContract() {
        return this.contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(String contract) {
        this.contract = contract;
    }
    
    // Begin function for model
    
    /**
     * Return all of employs
     * @return employs
     */
    public static List<Employ> findAll() {
        return find.all();
    }
    
    /**
     * Return Employ by ID
     * @param id
     * @return
     */
    public static Employ findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }
}