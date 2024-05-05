package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
     var stored_value = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View) {
        val box1: TextView = findViewById(R.id.textView)
        if (view is TextView) {
            val viewText = view.text.toString()
            box1.text = box1.text.toString() + viewText

            // Update stored values
            updateStoredValues(viewText)
        }
    }

    private fun updateStoredValues(value: String) {
        // Remove the last value
        if (stored_value.isNotEmpty() && stored_value.lastOrNull()?.matches(Regex("\\d+(\\.\\d+)?")) == true)
        {
            val lastIndex = stored_value.lastIndex
            stored_value[lastIndex] += value
        } else {
            // Add the new value
            stored_value.add(value)
        }
    }

    fun operatorAction(view: View) {
        val box1: TextView = findViewById(R.id.textView)
        val box2:TextView  = findViewById(R.id.textView2)

        if (view is TextView) {
            when (view.text) {
                "C" -> {
                    box1.text = ""
                    box2.text = ""
                    Toast.makeText(this, stored_value.toString(), Toast.LENGTH_LONG).show()
                }
                "+", "-", "*", "/","%" -> {
                    stored_value.add(view.text.toString())
                    box1.text = box1.text.toString() +  view.text.toString()
                }
            }
        }
    }

    private fun clearStoredValues() {
        stored_value.clear()
    }


    fun finalAction(view: View) {
        val box2:TextView  = findViewById(R.id.textView2)

        val number1 = stored_value[0].toDouble()
        val operator = stored_value[1]
        val number2 = stored_value[2].toDouble()

        val result = when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> number1 / number2
            "%" -> number1 % number2
            else -> null
        }

        box2.text = result.toString()

    }
}
