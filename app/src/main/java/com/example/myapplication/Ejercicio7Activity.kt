package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Ejercicio7Activity : AppCompatActivity() {

    private lateinit var btnGetLocal: Button
    private lateinit var btnPostLocal: Button
    private lateinit var btnPutLocal: Button
    private lateinit var btnPatchLocal: Button
    private lateinit var btnDeleteLocal: Button
    private lateinit var tvSalidaLocal: TextView
    private lateinit var colaPeticiones: RequestQueue

    // 10.0.2.2 apunta al localhost de la máquina anfitriona desde el Emulador Android
    private val urlBaseLocal = "http://10.0.2.2:3000/usuarios"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio7)

        btnGetLocal = findViewById(R.id.btnGetLocal)
        btnPostLocal = findViewById(R.id.btnPostLocal)
        btnPutLocal = findViewById(R.id.btnPutLocal)
        btnPatchLocal = findViewById(R.id.btnPatchLocal)
        btnDeleteLocal = findViewById(R.id.btnDeleteLocal)
        tvSalidaLocal = findViewById(R.id.tvSalidaLocal)

        colaPeticiones = Volley.newRequestQueue(this)

        btnGetLocal.setOnClickListener { obtenerUsuariosLocal() }
        btnPostLocal.setOnClickListener { crearUsuarioLocal() }
        btnPutLocal.setOnClickListener { actualizarUsuarioPutLocal(1) }
        btnPatchLocal.setOnClickListener { actualizarEdadPatchLocal(2) }
        btnDeleteLocal.setOnClickListener { eliminarUsuarioLocal(1) }
    }

    private fun obtenerUsuariosLocal() {
        val solicitud = StringRequest(
            Request.Method.GET,
            urlBaseLocal,
            { respuesta -> tvSalidaLocal.text = "✅ GET Local:\n$respuesta" },
            { error -> tvSalidaLocal.text = "❌ Error GET Local: ${error.message}" }
        )
        colaPeticiones.add(solicitud)
    }

    private fun crearUsuarioLocal() {
        val datos = JSONObject()
        datos.put("nombre", "Nuevo Aprendiz SENA")
        datos.put("edad", (18..45).random())

        val solicitud = JsonObjectRequest(
            Request.Method.POST,
            urlBaseLocal,
            datos,
            { respuesta -> tvSalidaLocal.text = "✅ POST Local:\n$respuesta" },
            { error -> tvSalidaLocal.text = "❌ Error POST Local: ${error.message}" }
        )
        colaPeticiones.add(solicitud)
    }

    private fun actualizarUsuarioPutLocal(id: Int) {
        val datos = JSONObject()
        datos.put("nombre", "Usuario Actualizado PUT")
        datos.put("edad", 35)

        val solicitud = JsonObjectRequest(
            Request.Method.PUT,
            "$urlBaseLocal/$id",
            datos,
            { respuesta -> tvSalidaLocal.text = "✅ PUT Local:\n$respuesta" },
            { error -> tvSalidaLocal.text = "❌ Error PUT Local: ${error.message}" }
        )
        colaPeticiones.add(solicitud)
    }

    private fun actualizarEdadPatchLocal(id: Int) {
        val datos = JSONObject()
        datos.put("edad", (20..60).random())

        val solicitud = JsonObjectRequest(
            Request.Method.PATCH,
            "$urlBaseLocal/$id",
            datos,
            { respuesta -> tvSalidaLocal.text = "✅ PATCH Local:\n$respuesta" },
            { error -> tvSalidaLocal.text = "❌ Error PATCH Local: ${error.message}" }
        )
        colaPeticiones.add(solicitud)
    }

    private fun eliminarUsuarioLocal(id: Int) {
        val solicitud = StringRequest(
            Request.Method.DELETE,
            "$urlBaseLocal/$id",
            { tvSalidaLocal.text = "✅ DELETE Local: Usuario $id eliminado" },
            { error -> tvSalidaLocal.text = "❌ Error DELETE Local: ${error.message}" }
        )
        colaPeticiones.add(solicitud)
    }
}
