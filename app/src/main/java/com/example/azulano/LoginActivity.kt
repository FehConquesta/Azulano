package com.example.azulano

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azulano.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.txtTelaCadastro.setOnClickListener{
            navegarTelaCadastro()

        }

        binding.txtEsqueciSenha.setOnClickListener{
            navegarTelaSenha()
        }

        binding.btEntrar.setOnClickListener{
            val email = binding.Email.text.toString()
            val password = binding.Senha.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                login(it)
                entrar(email, password)

            }else{
                Toast.makeText(this@LoginActivity, "Preencha os campos",Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun entrar(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d(TAG,"SignInUserWithEmailAndPassword:Success")
                val user = auth.currentUser

            }else  {
                Log.w(TAG,"SignInUserWithEmailAndPassword:Failure")
                Toast.makeText(baseContext,"E-mail ou Senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object{
        private var TAG ="EmailAndPassword"
    }
    private fun navegarTelaCadastro(){
        val intent = Intent(this,CadActivity::class.java)
        startActivity(intent)
    }
    private fun navegarTelaPrincipal(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun navegarTelaSenha(){
        val intent = Intent(this,ForgotActivity::class.java)
        startActivity(intent)
    }
    private fun login(view: View){

        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE

        binding.btEntrar.isEnabled = false
        binding.btEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTelaPrincipal() },3000)

    }
}
