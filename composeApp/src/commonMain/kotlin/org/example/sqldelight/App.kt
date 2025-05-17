package org.example.sqldelight

import SqliteActions
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        SqliteDemoApp()
    }
}

@Composable
fun SqliteDemoApp() {
    // State to hold the list of posts
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Function to refresh posts from the database
    fun refreshPosts() {
        coroutineScope.launch { // Perform database operations off the main thread
            posts = SqliteActions.readAllPosts()
        }
    }

    // Initial load of posts
    LaunchedEffect(Unit) { // LaunchedEffect runs the block when the Composable enters the Composition
        refreshPosts()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val samplePosts = listOf(
                        Post(1, 1, "Sample Post 1", "This is the body of sample post 1."),
                        Post(1, 2, "Sample Post 2", "This is the body of sample post 2.")
                    )
                    SqliteActions.insertAllPosts(samplePosts)
                    refreshPosts() // Refresh the list after inserting
                }
            }) {
                Text("Add Sample Posts")
            }

            Button(onClick = {
                coroutineScope.launch {
                    SqliteActions.removeAllPosts()
                    refreshPosts() // Refresh the list after removing
                }
            }) {
                Text("Remove All Posts")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (posts.isEmpty()) {
            Text("No posts yet. Add some!")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(posts) { post ->
                    PostItem(post)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text = "Title: ${post.title}", style = MaterialTheme.typography.titleMedium)
        Text(text = "User ID: ${post.userId}", style = MaterialTheme.typography.bodySmall)
        Text(text = "ID: ${post.id}", style = MaterialTheme.typography.bodySmall)
        Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
    }
}