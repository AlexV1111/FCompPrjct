package com.example.firstcomposeproject

import androidx.lifecycle.ViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.domain.StatisticItem
import com.example.firstcomposeproject.domain.StatisticType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val initialList = mutableListOf<FeedPost>().apply {
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

    private val _listFeedPost = MutableStateFlow(initialList)
    val listFeedPost: StateFlow<List<FeedPost>> = _listFeedPost

    fun updateCount(
        item: StatisticItem,
        feedPost: FeedPost,
    ) {
        val oldListPost = _listFeedPost.value.toMutableList()
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

        _listFeedPost.value = oldListPost.apply {
            replaceAll {
                if (it.id == newPost.id) {
                    newPost
                } else {
                    it
                }
            }
        }
    }

    fun deletePost(post: FeedPost) {
        val oldListPost = _listFeedPost.value.toMutableList()
        val postForDelete = oldListPost.find{ it.id == post.id}
        oldListPost.remove(postForDelete)
        _listFeedPost.value = oldListPost
    }
}