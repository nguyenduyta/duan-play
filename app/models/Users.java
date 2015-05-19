package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Users extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    @Constraints.MaxLength(50)
    public String name;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    @Constraints.MinLength(6)
    public String password;

    public boolean admin = false;

    public static Finder<Long,Users> find = new 
        Finder<Long,Users>(Long.class, Users.class);

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        Users user = Users.findByEmail(email);
        if (user != null && id == null) {
            errors.add(new ValidationError("email", "This e-mail is already registered."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public static List<Users> findAll() {
        return find.all();
    }
    
    public static Users findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static Users findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static boolean authenticate(String email, String password) {
        Users user = Users.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.password)) {
          return true;
        } else {
          return false;
        }
    }
}