package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar navegación con Intents explícitos a cada actividad de la guía SENA

        findViewById<Button>(R.id.btnEjercicio1).setOnClickListener {
            startActivity(Intent(this, Ejercicio1Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio2).setOnClickListener {
            startActivity(Intent(this, Ejercicio2Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio3).setOnClickListener {
            startActivity(Intent(this, Ejercicio3Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio4).setOnClickListener {
            startActivity(Intent(this, Ejercicio4Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio5).setOnClickListener {
            startActivity(Intent(this, Ejercicio5Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio6).setOnClickListener {
            startActivity(Intent(this, Ejercicio6Activity::class.java))
        }

        findViewById<Button>(R.id.btnEjercicio7).setOnClickListener {
            startActivity(Intent(this, Ejercicio7Activity::class.java))
        }
    }
}
