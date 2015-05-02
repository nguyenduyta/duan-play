package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Academic extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String description;

    public static Finder<Long,Academic> find = new 
        Finder<Long,Academic>(Long.class, Academic.class);

    public static List<Academic> findAll() {
        return find.all();
    }
    
    public static Academic findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();
        for(Academic l : Academic.find.all())  {
            output.put(l.id.toString(), l.name);
        }
        return output;
    }
}