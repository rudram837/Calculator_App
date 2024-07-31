package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var currentInput = ""
    private var operator = ""
    private var operand1 = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        setButtonListeners()
    }

    private fun setButtonListeners() {
        val buttonIDs = intArrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEqual, R.id.buttonClear
        )
        val listener = View.OnClickListener { v ->
            val button = v as Button
            val buttonText = button.text.toString()
            onButtonClick(buttonText)
        }
        for (id in buttonIDs) {
            findViewById<View>(id).setOnClickListener(listener)
        }
    }

    private fun onButtonClick(buttonText: String) {
        when (buttonText) {
            "C" -> {
                currentInput = ""
                operand1 = 0.0
                operator = ""
                editText!!.setText("")
            }

            "+", "-", "*", "/" -> {
                operator = buttonText
                operand1 = currentInput.toDouble()
                currentInput = ""
            }

            "=" -> {
                val operand2 = currentInput.toDouble()
                var result = 0.0
                when (operator) {
                    "+" -> result = operand1 + operand2
                    "-" -> result = operand1 - operand2
                    "*" -> result = operand1 * operand2
                    "/" -> result = if (operand2 != 0.0) {
                        operand1 / operand2
                    } else {
                        editText!!.setText("Error")
                        return
                    }
                }
                editText!!.setText(result.toString())
                currentInput = result.toString()
                operator = ""
            }

            else -> {
                currentInput += buttonText
                editText!!.setText(currentInput)
            }
        }
    }
}