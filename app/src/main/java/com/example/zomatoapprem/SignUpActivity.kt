package com.example.zomatoapprem

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zomatoapprem.databinding.ActivityMainBinding
import com.example.zomatoapprem.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Signupscreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnsignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val uniqueid = binding.etUniqueId.text.toString()
            val password = binding.etPassword.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this , "name is required" , Toast.LENGTH_SHORT).show()
            }else if(email.isEmpty()){
                Toast.makeText(this,"email is required",Toast.LENGTH_SHORT).show()
            }else if(password.isEmpty()){
                Toast.makeText(this , "password is required" , Toast.LENGTH_SHORT).show()
            }else {
                database = FirebaseDatabase.getInstance().getReference("Users")
                val user = User(name, email, uniqueid, password)
                database.child(uniqueid).setValue(user)
                val intent = Intent(this, menuActivity::class.java)
                startActivity(intent)
                Toast.makeText(this , "User Registered" , Toast.LENGTH_SHORT)
            }

        }
    }
}