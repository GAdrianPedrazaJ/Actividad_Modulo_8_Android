package com.example.petagram.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petagram.R
import com.example.petagram.data.Mascota
import com.example.petagram.databinding.ItemMascotaBinding

class MascotaAdapter(private var items: List<Mascota>) :
    RecyclerView.Adapter<MascotaAdapter.VH>() {

    class VH(val binding: ItemMascotaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMascotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

        // Nombre y rating
        holder.binding.tvNombre.text = item.nombre
        holder.binding.tvRait.text = context.getString(R.string.raits_mascota_placeholder, item.rating)

        // Imagen desde Supabase o respaldo local
        if (!item.imagenUrl.isNullOrBlank()) {
            Glide.with(context)
                .load(item.imagenUrl)
                .placeholder(R.drawable.ic_pet_sample)
                .error(R.drawable.ic_pet_sample)
                .into(holder.binding.ivMascota)
        } else {
            holder.binding.ivMascota.setImageResource(R.drawable.ic_pet_sample)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<Mascota>) {
        items = newItems
        notifyDataSetChanged()
    }
}
