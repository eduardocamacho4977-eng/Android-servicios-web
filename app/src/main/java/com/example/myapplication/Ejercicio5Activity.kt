package com.example.myapplication

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var btnCargarImagen: Button
    private lateinit var ivPersonaje: ImageView
    private lateinit var tvNombreImagen: TextView
    private lateinit var tvEspecieImagen: TextView
    private lateinit var colaPeticiones: RequestQueue

    private val urlRickAndMorty = "https://rickandmortyapi.com/api/character"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        btnCargarImagen = findViewById(R.id.btnCargarImagen)
        ivPersonaje = findViewById(R.id.ivPersonaje)
        tvNombreImagen = findViewById(R.id.tvNombreImagen)
        tvEspecieImagen = findViewById(R.id.tvEspecieImagen)

        colaPeticiones = Volley.newRequestQueue(this)

        btnCargarImagen.setOnClickListener {
            obtenerPersonajeEImagen()
        }
    }

    private fun obtenerPersonajeEImagen() {
        tvNombreImagen.text = "Cargando datos..."
        tvEspecieImagen.text = ""

        // 1. Petición GET para obtener datos del personaje
        val peticionJson = JsonObjectRequest(
            Request.Method.GET,
            urlRickAndMorty,
            null,
            { respuesta ->
                try {
                    val resultados = respuesta.getJSONArray("results")
                    if (resultados.length() > 0) {
                        val primerPersonaje = resultados.getJSONObject(0)
                        val nombre = primerPersonaje.getString("name")
                        val especie = primerPersonaje.getString("species")
                        val urlImagen = primerPersonaje.getString("image")

                        tvNombreImagen.text = nombre
                        tvEspecieImagen.text = "Especie: $especie"

                        // 2. Descargar la imagen del personaje usando ImageRequest de Volley
                        cargarImagenConVolley(urlImagen)
                    }
                } catch (e: Exception) {
                    tvNombreImagen.text = "Error JSON: ${e.message}"
                }
            },
            { error ->
                tvNombreImagen.text = "Error API: ${error.message}"
            }
        )

        colaPeticiones.add(peticionJson)
    }

    private fun cargarImagenConVolley(urlImagen: String) {
        val peticionImagen = ImageRequest(
            urlImagen,
            { bitmap: Bitmap ->
                // Éxito: Colocar la imagen descargada en el ImageView
                ivPersonaje.setImageBitmap(bitmap)
            },
            0, 0, // Ancho y alto máximo (0 = mantiene proporción original)
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            { error ->
                tvEspecieImagen.text = "Error al descargar imagen: ${error.message}"
            }
        )

        colaPeticiones.add(peticionImagen)
    }
}
