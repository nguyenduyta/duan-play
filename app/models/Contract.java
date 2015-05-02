package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Contract extends Model
{
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String description;

    public static Finder<Long,Contract> find = new 
        Finder<Long,Contract>(Long.class, Contract.class);

    public static List<Contract> findAll() {
        return find.all();
    }
    
    public static Contract findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static HashMap<String, String> selectCollection()  {
        HashMap<String, String> output = new HashMap<String, String>();
        for(Contract l : Contract.find.all())  {
            output.put(l.id.toString(), l.name);
        }
        return output;
    }
}