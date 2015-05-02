package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
 
@Entity
public class Employ extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String address;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    public Date birthday = new Date();

    @Constraints.Required
    public String phone_number;

    @Constraints.Required
    public boolean gender;

    @Constraints.Required
    public String workplace;

    @Constraints.Required
    @ManyToOne
    public Degree degree;

    @Constraints.Required
    @ManyToOne
    public Dept dept;

    @Constraints.Required
    @ManyToOne
    public Academic academic;

    @Constraints.Required
    @ManyToOne
    public Contract contract;

    public static Finder<Long,Employ> find = new 
        Finder<Long,Employ>(Long.class, Employ.class);

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        Employ employ = Employ.findByEmail(email);
        if (employ != null && id == null) {
            errors.add(new ValidationError("email", "This e-mail is already registered."));
        }

        if (!validatePhoneNumber(phone_number)) {
            errors.add(new ValidationError("phone_number", "This phone number is syntax wrong."));
        }
        return errors.isEmpty() ? null : errors;
    }

    private static boolean validatePhoneNumber(String phoneNo) {
        if (phoneNo.matches("\\d{10,15}")) return true;
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        else if(phoneNo.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) return true;
        else return false;
    }
    
    public static List<Employ> findAll() {
        return find.all();
    }
    
    public static Employ findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static Employ findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
}