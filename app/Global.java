import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {
    
    public void onStart(Application app) {
        InitialData.insert(app);
    }
    
    static class InitialData {
        
        public static void insert(Application app) {
            if(Ebean.find(Users.class).findRowCount() == 0) {
                
                Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");

                Ebean.save(all.get("users"));
                Ebean.save(all.get("academics"));
                Ebean.save(all.get("contracts"));
                Ebean.save(all.get("degrees"));
                Ebean.save(all.get("depts"));                
            }
        }
        
    }
    
}