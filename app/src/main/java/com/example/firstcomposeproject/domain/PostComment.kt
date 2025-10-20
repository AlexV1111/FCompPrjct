package com.example.firstcomposeproject.domain

import com.example.firstcomposeproject.R


data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.rick,
    val commentText: String = "Long comment...",
    val publicationName: String = "10:00",
)
