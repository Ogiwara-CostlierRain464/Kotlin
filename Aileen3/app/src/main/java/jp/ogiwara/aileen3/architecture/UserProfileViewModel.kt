package jp.ogiwara.aileen3.architecture

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class UserProfileViewModel: ViewModel{

    var user: LiveData<User>? = null
    lateinit var userRepo: UserRepository

    @Inject // UserRepository parameter is provided by Dagger 2
    constructor(userRepo: UserRepository): super(){
        this.userRepo = userRepo
    }


    fun init(userId: String){
        if(this.user != null){
            return
        }
        user = userRepo.getUser(userId)
    }
}