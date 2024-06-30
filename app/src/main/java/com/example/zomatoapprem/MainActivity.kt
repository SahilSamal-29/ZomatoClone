package com.example.zomatoapprem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Bundle
import android.widget.Toast
import com.example.zomatoapprem.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this , SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogIn.setOnClickListener{
            val uniqueid = binding.etUniqueId.text.toString()
            val password = binding.etPassword.text.toString()
            if(uniqueid.isNotEmpty()){
                readData(uniqueid , password)
            }
        }

    }
    private fun readData(uniqueid:String , password:String){
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueid).get().addOnSuccessListener {
            if(it.exists()){
                val fpass = it.child("password").value
                if(password==fpass){
                    val intent = Intent(this, menuActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this , "incorect password",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this ,"failed", Toast.LENGTH_SHORT).show()
        }
    }
}