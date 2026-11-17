package com.example.myapplication

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

class DetalleActivity : AppCompatActivity() {

    private lateinit var ivDetalleFoto: ImageView
    private lateinit var tvDetalleNombre: TextView
    private lateinit var tvDetalleEspecie: TextView
    private lateinit var colaPeticiones: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        ivDetalleFoto = findViewById(R.id.ivDetalleFoto)
        tvDetalleNombre = findViewById(R.id.tvDetalleNombre)
        tvDetalleEspecie = findViewById(R.id.tvDetalleEspecie)

        colaPeticiones = Volley.newRequestQueue(this)

        // Recibir datos enviados por Intent desde Ejercicio6Activity
        val nombre = intent.getStringExtra("nombre") ?: "Sin Nombre"
        val especie = intent.getStringExtra("especie") ?: "Desconocido"
        val urlImagen = intent.getStringExtra("urlImagen") ?: ""

        tvDetalleNombre.text = nombre
        tvDetalleEspecie.text = "Especie: $especie"

        if (urlImagen.isNotEmpty()) {
            val peticionImagen = ImageRequest(
                urlImagen,
                { bitmap: Bitmap ->
                    ivDetalleFoto.setImageBitmap(bitmap)
                },
                0, 0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.ARGB_8888,
                {
                    // Error al descargar imagen
                }
            )
            colaPeticiones.add(peticionImagen)
        }
    }
}
