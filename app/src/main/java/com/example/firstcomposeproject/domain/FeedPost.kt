package com.example.firstcomposeproject.domain

import com.example.firstcomposeproject.R

data class FeedPost(
    val communityName: String = "Title",
    val publicationTime: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 966),
        StatisticItem(StatisticType.SHARES, 7),
        StatisticItem(StatisticType.COMMENTS, 8),
        StatisticItem(StatisticType.LIKES, 27)
    ),
)
