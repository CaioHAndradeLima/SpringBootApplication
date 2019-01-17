package com.mechanical.endpoint

import com.mechanical.model.UserModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("user")
class UserEndpoint {

    @RequestMapping(method = [RequestMethod.GET])
    fun get() : MutableList<UserModel> {

        val list = ArrayList<UserModel>()
        list.add(  UserModel("user1","email","password")  )
        list.add( UserModel("user2","email","password") )

        return list
    }

}