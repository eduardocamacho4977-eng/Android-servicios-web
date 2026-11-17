package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Ejercicio2Activity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnEnviarPost: Button
    private lateinit var tvResultadoPost: TextView
    private lateinit var colaPeticiones: RequestQueue

    private val urlApi = "https://jsonplaceholder.typicode.com/posts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        btnEnviarPost = findViewById(R.id.btnEnviarPost)
        tvResultadoPost = findViewById(R.id.tvResultadoPost)

        colaPeticiones = Volley.newRequestQueue(this)

        btnEnviarPost.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                crearUsuarioPost(nombre, correo)
            }
        }
    }

    private fun crearUsuarioPost(nombre: String, correo: String) {
        tvResultadoPost.text = "Enviando datos al servidor..."

        // Construir objeto JSON con los datos del formulario
        val datosJson = JSONObject()
        datosJson.put("title", nombre)
        datosJson.put("body", correo)
        datosJson.put("userId", 1)

        val peticion = JsonObjectRequest(
            Request.Method.POST,
            urlApi,
            datosJson,
            { respuesta ->
                val idCreado = respuesta.optInt("id", -1)
                tvResultadoPost.text = "✅ Usuario creado con Éxito:\n" +
                        "• ID asignado: $idCreado\n" +
                        "• Nombre (Title): ${respuesta.optString("title")}\n" +
                        "• Correo (Body): ${respuesta.optString("body")}\n\n" +
                        "Respuesta completa JSON:\n$respuesta"
            },
            { error ->
                tvResultadoPost.text = "❌ Error en POST: ${error.message}"
            }
        )

        colaPeticiones.add(peticion)
    }
}
