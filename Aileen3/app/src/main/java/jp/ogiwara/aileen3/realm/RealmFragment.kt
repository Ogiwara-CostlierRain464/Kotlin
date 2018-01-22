package jp.ogiwara.aileen3.realm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import jp.ogiwara.aileen3.R
import org.jetbrains.anko.find

class RealmFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_realm,container,false)

        val result = root!!.find<TextView>(R.id.realm_result)
        root.find<Button>(R.id.realm_button).setOnClickListener {
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction {
                    val obj: Cat = it.createObject(Cat::class.java,1)
                    obj.name = "ogiwara"
                }

                realm.where(Cat::class.java).findAll().forEach {
                    Log.d("RealmWithKotlin", "${it.name} is ${it.id} years old.")
                }
            }
        }

        return root
    }

    open class Cat: RealmObject(){
        @PrimaryKey
        var id: Int = 0
        @Required
        var name: String? = null
    }
}