package com.example.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.data.UserData
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var gender: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyBinding()
    }

    private fun applyBinding() {
        binding.apply {
            genderChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                gender = if (checkedIds.isEmpty()) {
                    null
                } else {
                    if (checkedIds.contains(R.id.male_radio)) "male"
                    else if (checkedIds.contains(R.id.female_radio)) "female"
                    else "others"
                }
            }
            register.setOnClickListener {
                if (gender.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, "Select gender", Toast.LENGTH_SHORT).show()
                } else {
                    UserData(
                        address = addressTextField.editText!!.text.toString(),
                        department_id = 2,
                        email = emailTextField.editText!!.text.toString(),
                        gender = gender!!,
                        name =fullNameTextField.editText!!.text.toString(),
                        phone =phoneTextField.editText!!.text.toString(),
                    ).let {
                        // TODO: send
                    }
                }
            }
            nextPage.setOnClickListener {
                // TODO: go to next page
            }
        }
    }
}