package org.example.sqldelight

object SqliteActions {
    fun readAllPosts(): List<Post> {
        println("info: readAllPosts")

        return sqliteQuery.readAllPosts()
            .executeAsList()
            .map {
                Post(
                    userId = it.userId.toInt(),
                    id = it.id.toInt(),
                    title = it.title,
                    body = it.body
                )
            }
    }

    fun insertAllPosts(posts: List<Post>) {
        sqliteQuery.transaction {
            posts.forEach { post ->
                sqliteQuery.insertPost(
                    userId = post.userId.toLong(),
                    id = post.id.toLong(),
                    title = post.title,
                    body = post.body
                )
            }
        }
    }

    fun removeAllPosts() {
        println("info: removeAllPosts")

        sqliteQuery.removeAllPosts()
    }
}