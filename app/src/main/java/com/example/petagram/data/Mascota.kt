package com.example.petagram.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Contextual
import kotlinx.datetime.Instant

@Serializable
data class Mascota(
    val id: String,

    @SerialName("user_id")
    val userId: String?,

    val nombre: String,

    val rating: Int,

    @SerialName("imagen_url")
    val imagenUrl: String?,

    @SerialName("created_at")
    @Contextual
    val createdAt: Instant
)
