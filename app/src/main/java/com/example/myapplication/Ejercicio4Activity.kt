package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Ejercicio4Activity : AppCompatActivity() {

    private lateinit var btnEliminar: Button
    private lateinit var tvResultadoDelete: TextView
    private lateinit var colaPeticiones: RequestQueue

    private val urlApiEliminar = "https://jsonplaceholder.typicode.com/posts/1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        btnEliminar = findViewById(R.id.btnEliminar)
        tvResultadoDelete = findViewById(R.id.tvResultadoDelete)

        colaPeticiones = Volley.newRequestQueue(this)

        btnEliminar.setOnClickListener {
            eliminarRecurso()
        }
    }

    private fun eliminarRecurso() {
        tvResultadoDelete.text = "Eliminando recurso en el servidor..."

        val peticion = StringRequest(
            Request.Method.DELETE,
            urlApiEliminar,
            { respuesta ->
                tvResultadoDelete.text = "✅ Recurso eliminado con éxito.\n" +
                        "Respuesta del servidor: $respuesta\n" +
                        "Estado: 200 OK"
            },
            { error ->
                tvResultadoDelete.text = "❌ Error al eliminar: ${error.message}"
            }
        )

        colaPeticiones.add(peticion)
    }
}
