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
                fullNameTextField.apply {
                    this.error = "field empty"
                    this.isErrorEnabled = this.editText!!.text.toString().isNullOrEmpty()
                }
                phoneTextField.apply {
                    this.error = "field empty"
                    this.isErrorEnabled = this.editText!!.text.toString().isNullOrEmpty()
                }
                if (gender.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, "select gender", Toast.LENGTH_SHORT).show()
                }
                emailTextField.apply {
                    this.error = "field empty"
                    this.isErrorEnabled = this.editText!!.text.toString().isNullOrEmpty()
                }
                addressTextField.apply {
                    this.error = "field empty"
                    this.isErrorEnabled = this.editText!!.text.toString().isNullOrEmpty()
                }
                if (
                    !fullNameTextField.isErrorEnabled &&
                    !phoneTextField.isErrorEnabled &&
                    !emailTextField.isErrorEnabled &&
                    !addressTextField.isErrorEnabled &&
                    !gender.isNullOrBlank()
                ) {
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
                } else {
                    Toast.makeText(this@MainActivity, "check errors", Toast.LENGTH_SHORT).show()
                }
            }
            nextPage.setOnClickListener {
                startActivity(Intent(this@MainActivity, DisplayActivity::class.java))
            }
        }
    }
}