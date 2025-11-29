// ui/profile/FotoAdapter.kt
package com.example.petagram.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petagram.data.Foto
import com.example.petagram.databinding.ItemFotoBinding

class FotoAdapter(private val items: List<Foto>) : RecyclerView.Adapter<FotoAdapter.VH>() {
    class VH(val binding: ItemFotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemFotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.ivFoto.setImageResource(item.imagenRes)
        holder.binding.tvRaits.text = "${item.raits} 🦴"
    }

    override fun getItemCount() = items.size
}
