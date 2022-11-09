package com.example.kotlin_lesson_neco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible

import com.example.kotlin_lesson_neco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
     var ror = 1
     var ror1 = 1


    /*Соаздали глабальные переменные, которые сохраняют логин, пароль,
    имя, фамилия, отчество*/
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var name2: String = "empty"
    private var name3: String = "empty"
    private var avatarImageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }
    // Принимаем и обрабатываем данные с активити SignInUpAct
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {

            val login1 = data?.getStringExtra(Constance.LOGIN)
            val password1 = data?.getStringExtra(Constance.PASSWORD)
            //Если все поля введениы вверно
            if (login == login1 && password == password1) {

                bindingClass.imAvatar.visibility = View.VISIBLE
                bindingClass.imAvatar.setImageResource(avatarImageId)
                val textInfo = "$name $name2 $name3"
                bindingClass.tvInfo.text = textInfo
                bindingClass.bHide.visibility = View.GONE
                bindingClass.bExit.text = "Exit"

            //Если какие нибудь манипуляции были не соблюдены в if
            }else{

                bindingClass.imAvatar.visibility = View.VISIBLE
                bindingClass.imAvatar.setImageResource(R.drawable.shrek_2)
                bindingClass.tvInfo.text = "Такого аккаунта не существует!"
            }
        //Услоивие для регистрации
        }else if (requestCode == Constance.REQUEST_CODE_SIGN_UP){

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name = data.getStringExtra(Constance.NAME)!!
            name2 = data.getStringExtra(Constance.NAME2)!!
            name3 = data.getStringExtra(Constance.NAME3)!!
            avatarImageId = data.getIntExtra(Constance.AVATAR_ID, 0)
            bindingClass.imAvatar.visibility = View.VISIBLE
            bindingClass.imAvatar.setImageResource(avatarImageId)
            val textInfo = "$name $name2 $name3"
            bindingClass.tvInfo.text = textInfo
            bindingClass.bHide.visibility = View.GONE
            bindingClass.bExit.text = "Exit"
        }
    }
    //Слушатель нажатий на кнопку SignIn
    fun onClickSignIn(view: View){
        if (bindingClass.imAvatar.isVisible && bindingClass.tvInfo.text.toString() != "Такого аккаунта не существует!"){

            bindingClass.imAvatar.visibility = View.INVISIBLE
            bindingClass.tvInfo.text = ""
            bindingClass.bHide.visibility = View.VISIBLE
            bindingClass.bExit.text = getString(R.string.sign_in)

        }else {

            val intent = Intent(this, SignInUpAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
        }

    }
    // Слушатель нажатий на кнопку SignUp, то-что будет отображаться на SignUpAct
    fun onClickSignUp(view: View){
        val intent = Intent(this,SignInUpAct::class.java )
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent,Constance.REQUEST_CODE_SIGN_UP)

    }

}