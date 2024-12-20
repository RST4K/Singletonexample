package com.example.singletonexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Класс Singleton для хранения данных
object DataManager {
    private var counter: Int = 0

    fun incrementCounter() {
        counter++
    }

    fun getCounter(): Int {
        return counter
    }
}

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Найдем текстовое поле для отображения счётчика
        val textView: TextView = findViewById(R.id.textView)

        // Найдем кнопку для перехода на другой экран
        val button: Button = findViewById(R.id.button)

        // Увеличиваем счётчик с помощью Singleton
        DataManager.incrementCounter()

        // Отображаем текущее значение счётчика
        textView.text = "Счётчик: ${DataManager.getCounter()}"

        // Устанавливаем обработчик клика для кнопки
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("counter_value", DataManager.getCounter())
            startActivity(intent)
        }
    }
}

class SecondActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Получаем переданное значение счётчика
        val counterValue = intent.getIntExtra("counter_value", 0)

        // Найдем текстовое поле для отображения значения
        val textView: TextView = findViewById(R.id.textView)

        // Отображаем полученное значение
        textView.text = "Полученное значение: $counterValue"
    }
}