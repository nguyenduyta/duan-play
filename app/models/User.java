package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User extends Model
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

    public static Finder<Long,User> find = new 
        Finder<Long,User>(Long.class, User.class);

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        User user = User.findByEmail(email);
        if (user != null && id == null) {
            errors.add(new ValidationError("email", "This e-mail is already registered."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public static List<User> findAll() {
        return find.all();
    }
    
    public static User findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static boolean authenticate(String email, String password) {
        User user = User.findByEmail(email);
        if (user == null) {
            return false;
        } else if (!BCrypt.checkpw(password, user.password)) {
            return false;
        }
        return true;
    }
}