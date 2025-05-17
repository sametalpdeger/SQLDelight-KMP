package org.example.sqldelight

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val ALL_POSTS_ENDPOINT = "https://jsonplaceholder.typicode.com/posts?_limit=10"

class PostAPI {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchAllPosts(): List<Post> {
        println("fetchAllPosts")
        return httpClient.get(
            ALL_POSTS_ENDPOINT
        ).body()
    }
}

