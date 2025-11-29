package com.example.petagram.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.petagram.R
import com.example.petagram.data.Foto
import com.example.petagram.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivAvatar.setImageResource(R.drawable.ic_pet_sample)
        binding.tvNombre.setText(R.string.nombre_perfil)

        val data = listOf(
            Foto(1, R.drawable.ic_pet_sample, 5),
            Foto(2, R.drawable.ic_pet_sample, 0),
            Foto(3, R.drawable.ic_pet_sample, 5),
            Foto(4, R.drawable.ic_pet_sample, 10),
            Foto(5, R.drawable.ic_pet_sample, 2),
            Foto(6, R.drawable.ic_pet_sample, 3)
        )
        binding.rvFotos.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvFotos.adapter = FotoAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
