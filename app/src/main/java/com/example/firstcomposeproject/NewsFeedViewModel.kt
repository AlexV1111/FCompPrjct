package com.example.firstcomposeproject

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticItem
import com.example.firstcomposeproject.domain.StatisticType
import com.example.firstcomposeproject.ui.theme.NewsFeedScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class NewsFeedViewModel : ViewModel() {

    private val initialPostsList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(
                    id = it,
                    communityName = "Title $it",
                    publicationTime = "14:00",
                    avatarResId = R.drawable.post_comunity_thumbnail,
                    contentText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo",
                    contentImageResId = R.drawable.post_content_image,
                    statistics = listOf(
                        StatisticItem(StatisticType.VIEWS, 966),
                        StatisticItem(StatisticType.SHARES, 7),
                        StatisticItem(StatisticType.COMMENTS, 8),
                        StatisticItem(StatisticType.LIKES, 27)
                    ),
                )
            )
        }
    }

    private val initialState = NewsFeedScreenState.Posts(initialPostsList)
    private val _screenState = MutableStateFlow<NewsFeedScreenState>(initialState)
    val screenState: StateFlow<NewsFeedScreenState> = _screenState

    fun updateCount(
        item: StatisticItem,
        feedPost: FeedPost,
    ) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldListPost = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newPost = feedPost.copy(statistics = newStatistics)

        val newPosts = oldListPost.apply {
            replaceAll {
                if (it.id == newPost.id) {
                    newPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun deletePost(post: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

        val oldListPost = currentState.posts.toMutableList()
        val postForDelete = oldListPost.find { it.id == post.id }
        oldListPost.remove(postForDelete)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldListPost)
    }
}