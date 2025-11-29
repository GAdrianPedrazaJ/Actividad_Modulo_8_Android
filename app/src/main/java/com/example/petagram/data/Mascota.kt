package com.example.petagram.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Contextual
import java.time.Instant

@Serializable
data class Mascota(
    val id: String, // UUID generado por Supabase

    @SerialName("user_id")
    val userId: String?, // ID del usuario (si usas Auth)

    val nombre: String,

    val rating: Int, // número de huesos

    @SerialName("imagen_url")
    val imagenUrl: String?, // URL de imagen (opcional)

    @SerialName("created_at")
    @Contextual
    val createdAt: Instant // fecha de calificación
)
