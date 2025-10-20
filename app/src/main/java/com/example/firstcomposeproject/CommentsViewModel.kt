package com.example.firstcomposeproject

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.PostComment
import com.example.firstcomposeproject.ui.theme.CommentsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CommentsViewModel(
    feedPost: FeedPost
) : ViewModel() {
    private val _screenState = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: StateFlow<CommentsScreenState> = _screenState

    init{
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(feedPost = feedPost, comments = comments)
    }
}