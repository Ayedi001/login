package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity  : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textview.setOnClickListener{
            val intent = Intent (this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.BtnRegister.setOnClickListener{
            val email = binding.EdtUserName.text.toString()
            val pass = binding.EdtPassword.text.toString()
            val copass = binding.EdtCoPassword.text.toString()


        if (email.isNotEmpty() && pass.isNotEmpty() && copass.isNotEmpty() )
        {
            if (pass == copass){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "pass is not match", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "emty is not allowed", Toast.LENGTH_SHORT).show()
        }


        }
    }


}