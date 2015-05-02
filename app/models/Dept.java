package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Dept extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String description;

    public static Finder<Long,Dept> find = new 
        Finder<Long,Dept>(Long.class, Dept.class);

    public static List<Dept> findAll() {
        return find.all();
    }
    
    public static Dept findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();
        for(Dept l : Dept.find.all())  {
            output.put(l.id.toString(), l.name);
        }
        return output;
    }
}