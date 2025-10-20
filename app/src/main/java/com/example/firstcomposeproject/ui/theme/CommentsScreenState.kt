package com.example.firstcomposeproject.ui.theme

import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.PostComment

sealed class CommentsScreenState {
    object Initial : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ) :
        CommentsScreenState()
}