package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Ejercicio3Activity : AppCompatActivity() {

    private lateinit var btnPut: Button
    private lateinit var btnPatch: Button
    private lateinit var tvResultadoPutPatch: TextView
    private lateinit var colaPeticiones: RequestQueue

    private val urlApiRecurso = "https://jsonplaceholder.typicode.com/posts/1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        btnPut = findViewById(R.id.btnPut)
        btnPatch = findViewById(R.id.btnPatch)
        tvResultadoPutPatch = findViewById(R.id.tvResultadoPutPatch)

        colaPeticiones = Volley.newRequestQueue(this)

        btnPut.setOnClickListener {
            ejecutarPut()
        }

        btnPatch.setOnClickListener {
            ejecutarPatch()
        }
    }

    /**
     * PUT reemplaza todo el objeto por completo
     */
    private fun ejecutarPut() {
        tvResultadoPutPatch.text = "Enviando PUT (reemplazo completo)..."

        val datosPut = JSONObject()
        datosPut.put("title", "Título Nuevo Reemplazado (PUT)")
        datosPut.put("body", "Cuerpo Nuevo Reemplazado (PUT)")
        datosPut.put("userId", 1)

        val peticion = JsonObjectRequest(
            Request.Method.PUT,
            urlApiRecurso,
            datosPut,
            { respuesta ->
                tvResultadoPutPatch.text = "✅ Respuesta PUT (Objeto reemplazado):\n\n$respuesta"
            },
            { error ->
                tvResultadoPutPatch.text = "❌ Error en PUT: ${error.message}"
            }
        )

        colaPeticiones.add(peticion)
    }

    /**
     * PATCH modifica únicamente los campos especificados (parcial)
     */
    private fun ejecutarPatch() {
        tvResultadoPutPatch.text = "Enviando PATCH (modificación parcial)..."

        val datosPatch = JSONObject()
        datosPatch.put("title", "Título Modificado Únicamente (PATCH)")

        val peticion = JsonObjectRequest(
            Request.Method.PATCH,
            urlApiRecurso,
            datosPatch,
            { respuesta ->
                tvResultadoPutPatch.text = "✅ Respuesta PATCH (Solo título modificado):\n\n$respuesta"
            },
            { error ->
                tvResultadoPutPatch.text = "❌ Error en PATCH: ${error.message}"
            }
        )

        colaPeticiones.add(peticion)
    }
}
