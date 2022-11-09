package com.example.kotlin_lesson_neco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.kotlin_lesson_neco.databinding.ActivitySignInUpBinding

class SignInUpAct : AppCompatActivity() {

    lateinit var bindingClass: ActivitySignInUpBinding
    private var signState = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        //Если мы зашли зарегистрироваться
        signState = intent.getStringExtra(Constance.SIGN_STATE)!!

        if (signState == Constance.SIGN_IN_STATE) {
            bindingClass.edName.visibility = View.GONE
            bindingClass.edName2.visibility = View.GONE
            bindingClass.edName3.visibility = View.GONE
            bindingClass.bAvatar.visibility = View.INVISIBLE
        }

    }
    //В случае если мы зашли для регистрации
    fun onClickDane(view: View) {

        if (signState == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.edPassword.text.toString())
            intent.putExtra(Constance.NAME, bindingClass.edName.text.toString())
            intent.putExtra(Constance.NAME2, bindingClass.edName2.text.toString())
            if (bindingClass.imAvatar.isVisible)intent.putExtra(Constance.NAME3, bindingClass.edName3.text.toString())
            intent.putExtra(Constance.AVATAR_ID, R.drawable.fiona)

            setResult(RESULT_OK, intent)
            finish()
        // Если зашли не для регистрации, а для входа
        }else if(signState == Constance.SIGN_IN_STATE)

            intent.putExtra(Constance.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.edPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()


    }

    fun onClickAvatar(view: View){

        bindingClass.imAvatar.visibility = View.VISIBLE


    }
}