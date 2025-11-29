package com.example.petagram.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.builtins.ListSerializer
import java.io.IOException

class MascotaRepository {

    private val tableName = "mascota"

    // ✅ Insertar una mascota con rating
    suspend fun insertarMascota(mascota: Mascota): Boolean = withContext(Dispatchers.IO) {
        val jsonBody = SupabaseClient.json.encodeToString(listOf(mascota))
        val request = SupabaseClient.buildRequest(
            endpoint = tableName,
            method = "POST",
            body = jsonBody,
            prefer = "return=minimal"
        )

        try {
            val response = SupabaseClient.client.newCall(request).execute()
            Log.d("Supabase", "Código POST: ${response.code}")
            Log.d("Supabase", "Body POST: ${response.body?.string()}")
            response.isSuccessful
        } catch (e: IOException) {
            Log.e("Supabase", "Error POST: ${e.message}")
            false
        }
    }

    // ✅ Consultar las últimas 5 mascotas calificadas
    suspend fun obtenerUltimasMascotas(userId: String?): List<Mascota> = withContext(Dispatchers.IO) {
        val queryParams = if (userId != null) {
            "?user_id=eq.$userId&order=created_at.desc&limit=5"
        } else {
            "?order=created_at.desc&limit=5"
        }

        val request = SupabaseClient.buildRequest(
            endpoint = "$tableName$queryParams"
        )

        try {
            val response = SupabaseClient.client.newCall(request).execute()
            val body = response.body?.string()

            // 👇 Logs para depuración
            Log.d("Supabase", "Código GET: ${response.code}")
            Log.d("Supabase", "Body GET: $body")

            if (response.isSuccessful && body != null) {
                SupabaseClient.json.decodeFromString(ListSerializer(Mascota.serializer()), body)
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            Log.e("Supabase", "Error GET: ${e.message}")
            emptyList()
        }
    }
}
