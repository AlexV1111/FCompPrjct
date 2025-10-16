package com.example.firstcomposeproject.domain

data class FeedPost(
    val id: Int,
    val communityName: String,
    val publicationTime: String,
    val avatarResId: Int,
    val contentText: String,
    val contentImageResId: Int,
    val statistics: List<StatisticItem>,
)
