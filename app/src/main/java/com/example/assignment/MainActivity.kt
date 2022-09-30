package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.api.service
import com.example.assignment.data.UserData
import com.example.assignment.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
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
                fullNameTextField.isErrorEnabled = false
                phoneTextField.isErrorEnabled = false
                emailTextField.isErrorEnabled = false
                addressTextField.isErrorEnabled = false

                if (fullNameTextField.editText!!.text.toString().isEmpty()) {
                    fullNameTextField.error = "field empty"
                    fullNameTextField.isErrorEnabled = true
                } else if (phoneTextField.editText!!.text.toString().isEmpty()) {
                    phoneTextField.error = "field empty"
                    phoneTextField.isErrorEnabled = true
                } else if (gender.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, "Select gender", Toast.LENGTH_SHORT).show()
                } else if (emailTextField.editText!!.text.toString().isEmpty()) {
                    emailTextField.error = "field empty"
                    emailTextField.isErrorEnabled = true
                } else if (addressTextField.editText!!.text.toString().isEmpty()) {
                    addressTextField.error = "field empty"
                    addressTextField.isErrorEnabled = true
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val call = service.register(
                            UserData(
                                address = addressTextField.editText!!.text.toString(),
                                department_id = 2,
                                email = emailTextField.editText!!.text.toString(),
                                gender = gender!!,
                                name = fullNameTextField.editText!!.text.toString(),
                                phone = phoneTextField.editText!!.text.toString(),
                            )
                        )
                        val response = call.execute()
                        Log.d(TAG, "response.raw().message: ${response.body()?.name}")
                        withContext(Dispatchers.Main) {
                            // TODO: show message after registration
                            if (response.isSuccessful) {
                                Log.d(TAG, "call successful")
                                Toast.makeText(
                                    this@MainActivity,
                                    "call successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Log.d(TAG, "call unsuccessful")
                                Toast.makeText(
                                    this@MainActivity,
                                    "call unsuccessful",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
            nextPage.setOnClickListener {
                startActivity(Intent(this@MainActivity, DisplayActivity::class.java))
            }
        }
    }
}