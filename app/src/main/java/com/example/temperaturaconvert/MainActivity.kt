package com.example.temperaturaconvert


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTemperatura: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonConverter: Button
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTemperatura = findViewById(R.id.editTextTemperatura)
        radioGroup = findViewById(R.id.radioGroup)
        buttonConverter = findViewById(R.id.buttonConverter)
        textViewResultado = findViewById(R.id.textViewResultado)

        buttonConverter.setOnClickListener { converterTemperatura() }
    }

    private fun converterTemperatura() {
        val temperaturaString = editTextTemperatura.text.toString().trim()

        if (temperaturaString.isNotEmpty()) {
            val temperatura = temperaturaString.toDoubleOrNull()

            if (temperatura != null) {
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val resultado = when (selectedRadioButtonId) {
                    R.id.radioCelsiusToFahrenheit -> {
                        (temperatura * 9 / 5) + 32
                    }
                    R.id.radioFahrenheitToCelsius -> {
                        (temperatura - 32) * 5 / 9
                    }
                    else -> {
                        textViewResultado.text = "Por favor, selecione uma conversão."
                        return
                    }
                }
                textViewResultado.text = String.format("Resultado: %.2f", resultado)
            } else {
                textViewResultado.text = "Por favor, insira um valor válido."
            }
        } else {
            textViewResultado.text = "Por favor, preencha o campo de temperatura."
        }
    }
}
