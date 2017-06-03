package ogiwara.sample.rxandroid.model


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class User : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    @Required
    var name: String? = null
}

//You can't use this!
open class User2(@PrimaryKey var id: Int = 0,@Required var name: String? = null): RealmObject()

