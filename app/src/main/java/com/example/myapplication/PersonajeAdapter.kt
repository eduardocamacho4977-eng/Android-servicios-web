package com.example.myapplication

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest

class PersonajeAdapter(
    private val listaPersonajes: List<Personaje>,
    private val colaPeticiones: RequestQueue,
    private val onItemClick: (Personaje) -> Unit
) : RecyclerView.Adapter<PersonajeAdapter.PersonajeViewHolder>() {

    class PersonajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFoto: ImageView = itemView.findViewById(R.id.itemIvFoto)
        val tvNombre: TextView = itemView.findViewById(R.id.itemTvNombre)
        val tvEspecie: TextView = itemView.findViewById(R.id.itemTvEspecie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent, false)
        return PersonajeViewHolder(vista)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = listaPersonajes[position]
        holder.tvNombre.text = personaje.nombre
        holder.tvEspecie.text = personaje.especie
        holder.ivFoto.setImageResource(android.R.color.darker_gray)

        // Descargar la imagen usando ImageRequest de Volley
        val peticionImagen = ImageRequest(
            personaje.urlImagen,
            { bitmap: Bitmap ->
                holder.ivFoto.setImageBitmap(bitmap)
            },
            0, 0,
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            {
                // Silencioso o imagen por defecto
            }
        )
        colaPeticiones.add(peticionImagen)

        // Evento clic en el ítem
        holder.itemView.setOnClickListener {
            onItemClick(personaje)
        }
    }

    override fun getItemCount(): Int = listaPersonajes.size
}
