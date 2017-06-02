package ogiwara.sample.rxandroid.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject{
    @PrimaryKey
    public int id;
    @Required
    public String name;
}
