// ui/contact/ContactActivity.kt
package com.example.petagram.ui.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petagram.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            // Aquí solo capturamos datos. El envío real por correo
            // requiere configurar un servicio seguro (SMTP con credenciales)
            // o usar un Intent de correo:
            // val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:tu@correo.com"))
            // intent.putExtra(Intent.EXTRA_SUBJECT, "Comentario de contacto")
            // intent.putExtra(Intent.EXTRA_TEXT, "${binding.etNombre.text}\n${binding.etMensaje.text}")
            // startActivity(intent)
        }
    }
}
