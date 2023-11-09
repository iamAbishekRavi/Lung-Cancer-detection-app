package com.example.lungcancerdetection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lungcancerdetection.databinding.ActivityMainBinding
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var counter = 5
    private var userName = ""
    private var userPassword = ""
    private var isValid = false

    /* Class to hold credentials */
    private inner class Credentials {
        var name = "Admin"
        var password = "123456"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /* Describe the logic when the login button is clicked */
        binding.loginButton.setOnClickListener {
            /* Obtain user inputs */
            userName = binding.usernameEditText.text.toString()
            userPassword = binding.passwordEditText.text.toString()

            /* Check if the user inputs are empty */
            if (userName.isEmpty() || userPassword.isEmpty()) {
                /* Display a message toast to the user to enter the details */
                Toast.makeText(this@MainActivity2, "Please enter name and password!", Toast.LENGTH_LONG).show()
            } else {
                /* Validate the user inputs */
                isValid = validate(userName, userPassword)

                /* If not valid */
                if (!isValid) {
                    /* Decrement the counter */
                    counter--

                    /* Show the attempts remaining */
                    binding.tvAttempts.text = "Attempts Remaining: $counter"

                    /* Disable the login button if there are no attempts left */
                    if (counter == 0) {
                        binding.loginButton.isEnabled = false
                        Toast.makeText(this@MainActivity2, "You have used all your attempts, try again later!", Toast.LENGTH_LONG).show()
                    }
                    /* Display an error message */
                    else {
                        Toast.makeText(this@MainActivity2, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show()
                    }
                }
                /* If valid */
                else {
                    /* Allow the user into your app by going into the next activity */
                    startActivity(Intent(this, MainActivity3::class.java))
                }
            }
        }
    }

    /* Validate the credentials */
    private fun validate(userName: String, userPassword: String): Boolean {
        /* Get the object of Credentials class */
        val credentials = Credentials()

        /* Check the credentials */
        return userName == credentials.name && userPassword == credentials.password
    }
}





