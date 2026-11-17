package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class Ejercicio1Activity : AppCompatActivity() {

    private lateinit var btnObtenerUsuarios: Button
    private lateinit var tvResultado: TextView
    private lateinit var colaPeticiones: RequestQueue
    private val urlApi = "https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        btnObtenerUsuarios = findViewById(R.id.btnObtenerUsuarios)
        tvResultado = findViewById(R.id.tvResultado)
        colaPeticiones = Volley.newRequestQueue(this)

        btnObtenerUsuarios.setOnClickListener {
            obtenerUsuarios()
        }
    }

    private fun obtenerUsuarios() {
        tvResultado.text = "Cargando usuarios..."

        val peticion = StringRequest(
            Request.Method.GET,
            urlApi,
            { respuesta ->
                parsearYMostrarUsuarios(respuesta)
            },
            { error ->
                tvResultado.text = "Error GET: ${error.message}"
            }
        )

        colaPeticiones.add(peticion)
    }

    private fun parsearYMostrarUsuarios(jsonRespuesta: String) {
        try {
            val jsonArray = JSONArray(jsonRespuesta)
            val constructorTexto = StringBuilder()
            constructorTexto.append("--- NOMBRES DE USUARIOS ---\n\n")

            for (i in 0 until jsonArray.length()) {
                val usuarioObj = jsonArray.getJSONObject(i)
                val nombre = usuarioObj.getString("name")
                val email = usuarioObj.getString("email")

                constructorTexto.append("${i + 1}. $nombre ($email)\n")
            }

            tvResultado.text = constructorTexto.toString()
        } catch (e: Exception) {
            tvResultado.text = "Error parseando JSON: ${e.message}"
        }
    }
}
