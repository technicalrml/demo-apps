package com.sourcey.tenant.core.helper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.sourcey.materiallogindemo.auth.LoginActivity


class SessionManager(context: Context) {
    private val PREF_NAME = "merchant app"
    private val _context = context
    private val IS_LOGIN = "IsLoggedIn"
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME,0)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun setIsLogin(){
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()
    }
    fun getIsLogin(): Boolean{
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

    fun setId(id: String){
        editor.putString("id", id)
        editor.commit()
    }

    fun getId(): String{
        return sharedPreferences.getString("id","").toString()
    }

    fun setAccount(account: String){
        editor.putString("account", account)
        editor.commit()
    }

    fun getAccount(): String{
        return sharedPreferences.getString("account", "").toString()
    }

    fun setInit(init: String){
        editor.putString("init", init)
        editor.commit()
    }

    fun getInit(): String{
        return sharedPreferences.getString("init", "").toString()
    }

    fun setFirstName(firstName: String){
        editor.putString("firstName", firstName)
    }

    fun getFirstName(): String{
        return sharedPreferences.getString("firstName", "").toString()
    }

    fun setLastName(lastName: String){
        editor.putString("lastname", lastName)
    }

    fun getLastName(): String{
        return sharedPreferences.getString("lastname","").toString()
    }

    fun setUsername(username: String){
        editor.putString("username", username)
    }

    fun getUsername(): String{
        return sharedPreferences.getString("username","").toString()
    }

    fun setEmail(email: String){
        editor.putString("email", email)
    }

    fun getEmail(): String{
        return sharedPreferences.getString("email","").toString()
    }

    fun setUid(uid: String){
        editor.putString("uid", uid)
    }

    fun getUid(): String{
        return sharedPreferences.getString("uid", "").toString()
    }



    fun logoutUser(){
        editor.clear()
        editor.commit()

        val i = Intent(_context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        _context.startActivity(i)


    }


}