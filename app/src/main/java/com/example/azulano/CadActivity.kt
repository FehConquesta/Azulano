package com.example.azulano

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azulano.databinding.ActivityCadBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

       binding.btCadastrar.setOnClickListener{
           val email = binding.Email.text.toString()
           val password = binding.Senha.text.toString()
           val confpassword = binding.ConfSenha.text.toString()

           if(email.isNotEmpty() && password.isNotEmpty() && confpassword.isNotEmpty()) {
               if (password == confpassword) {
                   cadastrar(email, password)
               } else {
                   Toast.makeText(this@CadActivity, "Senhas não são iguais", Toast.LENGTH_SHORT)
                       .show()
               }
           }else{
                   Toast.makeText(this@CadActivity, "Preencha os campos",Toast.LENGTH_SHORT).show()
               }
           }


       }

    private fun cadastrar(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d(TAG,"CreateUserWithEmailAndPassword:Success")
                Toast.makeText(baseContext,"Cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                navegarTelaLogin()
            }else  {
                Log.w(TAG,"CreateInUserWithEmailAndPassword:Failure")
                Toast.makeText(baseContext,"E-mail ou Senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object{
        private var TAG ="EmailAndPassword"
    }

    private fun navegarTelaLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}