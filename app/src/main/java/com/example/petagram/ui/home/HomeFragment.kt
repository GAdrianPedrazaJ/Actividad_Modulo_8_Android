package com.example.petagram.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petagram.R
import com.example.petagram.data.MascotaRepository
import com.example.petagram.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MascotaAdapter
    private val repository = MascotaRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MascotaAdapter(emptyList())
        binding.rvMascotas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMascotas.adapter = adapter

        binding.tvMensaje.visibility = View.GONE
        binding.rvMascotas.visibility = View.GONE

        lifecycleScope.launch {
            Log.d("HomeFragment", "Llamando a obtenerUltimasMascotas...")
            try {
                val mascotas = repository.obtenerUltimasMascotas(userId = null)
                Log.d("HomeFragment", "Mascotas recibidas: ${mascotas.size}")
                mascotas.forEach {
                    Log.d("HomeFragment", "Nombre: ${it.nombre}, Rating: ${it.rating}")
                }

                if (mascotas.isNotEmpty()) {
                    adapter.updateData(mascotas)
                    binding.rvMascotas.visibility = View.VISIBLE
                    binding.tvMensaje.visibility = View.GONE
                } else {
                    binding.tvMensaje.text = getString(R.string.mensaje_sin_mascotas)
                    binding.tvMensaje.visibility = View.VISIBLE
                    binding.rvMascotas.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("HomeFragment", "Error al cargar mascotas: ${e.message}")
                binding.tvMensaje.text = getString(R.string.mensaje_error_carga)
                binding.tvMensaje.visibility = View.VISIBLE
                binding.rvMascotas.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
