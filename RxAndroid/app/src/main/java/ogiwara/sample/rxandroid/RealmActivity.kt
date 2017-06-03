package ogiwara.sample.rxandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import ogiwara.sample.rxandroid.model.User

class RealmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        val realm = Realm.getDefaultInstance()
        val id = 1
        realm.executeTransaction {
            val obj: User = it.createObject(User::class.java,id)
            obj.name = "ogiwara"
        }
    }
}
