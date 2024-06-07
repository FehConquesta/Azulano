package com.example.azulano

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.azulano.databinding.ActivityCadBinding
import com.example.azulano.databinding.ActivityForgotBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btReset.setOnClickListener{
            val email = binding.Email.text.toString()
            if(email.isNotEmpty()){
                Firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Email sent.")
                            Toast.makeText(this@ForgotActivity, "E-mail enviado", Toast.LENGTH_SHORT).show()
                            navegarTelaLogin()

                        }else{
                            Log.w(TAG,"Failed")
                        }
                    }
            }
        }

        binding.btVoltar.setOnClickListener {
            navegarTelaLogin()
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
