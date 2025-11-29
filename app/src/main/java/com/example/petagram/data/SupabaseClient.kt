package com.example.petagram.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object SupabaseClient {

    private const val SUPABASE_URL = "https://vklmdoyikbczsrjbwibq.supabase.co"
    private const val SUPABASE_API_KEY = "sb_publishable_7xyUG-ClhLubRpgqzokeSw_doNXw-0N"

    val json = Json { ignoreUnknownKeys = true }
    val client = OkHttpClient()

    fun buildRequest(
        endpoint: String,
        method: String = "GET",
        body: String? = null,
        prefer: String? = null
    ): Request {
        val builder = Request.Builder()
            .url("$SUPABASE_URL/rest/v1/$endpoint")
            .addHeader("apikey", SUPABASE_API_KEY)
            .addHeader("Accept", "application/json")

        if (body != null) {
            builder.addHeader("Content-Type", "application/json")
            if (prefer != null) builder.addHeader("Prefer", prefer)
            builder.method(method, body.toRequestBody("application/json".toMediaType()))
        } else {
            builder.method(method, null)
        }

        return builder.build()
    }
}