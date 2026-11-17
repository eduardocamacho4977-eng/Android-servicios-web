package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Ejercicio6Activity : AppCompatActivity() {

    private lateinit var rvPersonajes: RecyclerView
    private lateinit var colaPeticiones: RequestQueue
    private val listaPersonajes = mutableListOf<Personaje>()

    private val urlRickAndMorty = "https://rickandmortyapi.com/api/character"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        rvPersonajes = findViewById(R.id.rvPersonajes)
        rvPersonajes.layoutManager = LinearLayoutManager(this)

        colaPeticiones = Volley.newRequestQueue(this)

        cargarPersonajes()
    }

    private fun cargarPersonajes() {
        val peticion = JsonObjectRequest(
            Request.Method.GET,
            urlRickAndMorty,
            null,
            { respuesta ->
                try {
                    val resultados = respuesta.getJSONArray("results")
                    for (i in 0 until resultados.length()) {
                        val obj = resultados.getJSONObject(i)
                        val personaje = Personaje(
                            id = obj.getInt("id"),
                            nombre = obj.getString("name"),
                            especie = obj.getString("species"),
                            urlImagen = obj.getString("image")
                        )
                        listaPersonajes.add(personaje)
                    }

                    // Asignar Adapter con evento de clic que abre DetalleActivity con Intent
                    rvPersonajes.adapter = PersonajeAdapter(listaPersonajes, colaPeticiones) { personajeSeleccionado ->
                        val intent = Intent(this, DetalleActivity::class.java).apply {
                            putExtra("nombre", personajeSeleccionado.nombre)
                            putExtra("especie", personajeSeleccionado.especie)
                            putExtra("urlImagen", personajeSeleccionado.urlImagen)
                        }
                        startActivity(intent)
                    }

                } catch (e: Exception) {
                    Toast.makeText(this, "Error JSON: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error API: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        colaPeticiones.add(peticion)
    }
}
