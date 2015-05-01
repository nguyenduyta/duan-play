package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Degree extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String description;

    public static Finder<Long,Degree> find = new 
        Finder<Long,Degree>(Long.class, Degree.class);

    public static List<Degree> findAll() {
        return find.all();
    }
    
    public static Degree findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();
        for(Degree l : Degree.find.all())  {
            output.put(l.id.toString(), l.name);
        }
        return output;
    }
}