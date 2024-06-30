package com.example.zomatoapprem

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatoapprem.databinding.ActivityMenuBinding
private const val TAG = "menuActivity"
class menuActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var binding: ActivityMenuBinding
    lateinit var foodArrayList: ArrayList<Food>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageArray = arrayOf(R.drawable.naan,R.drawable.naan,R.drawable.naan,R.drawable.naan,R.drawable.paneer,R.drawable.biryani,R.drawable.zomatologowhite)
        val foodNameArray = arrayOf("Shahi paneer ","Kadai paneer ","Paneer Makhanwala ","Paneer Tikka masala ","Naan ","Biryani ","")
        val prizeArrayList = arrayOf("250 Rs","220 Rs","230 Rs","299 Rs","20 Rs","300 Rs","")
        myRecyclerView = binding.recyclerView
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        foodArrayList = arrayListOf<Food>()
        for (index in imageArray.indices){
            val food = Food(
                foodPic = imageArray[index],
                itemName = foodNameArray[index],
                prize = prizeArrayList[index]
            )
            foodArrayList.add(food)
        }
        myRecyclerView.adapter = MyAdapter(foodArrayList, this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() // Close the app
    }
}
