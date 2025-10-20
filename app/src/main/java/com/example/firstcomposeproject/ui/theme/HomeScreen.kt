package com.example.firstcomposeproject.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstcomposeproject.NewsFeedViewModel
import com.example.firstcomposeproject.domain.FeedPost

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit,
) {
    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    val currentState = screenState.value
    when (currentState) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                posts = currentState.posts,
                onCommentClickListener = onCommentClickListener
            )
        }

        NewsFeedScreenState.Initial -> {

        }
    }
}

@Composable
private fun FeedPosts(
    viewModel: NewsFeedViewModel,
    posts: List<FeedPost>,
    onCommentClickListener: (FeedPost) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = 80.dp
        )
    ) {
        items(
            items = posts,
            key = { it.id }
        )
        { feedPost ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { it ->
                    val isDismissed = (it == SwipeToDismissBoxValue.EndToStart)
                    if (isDismissed) {
                        viewModel.deletePost(feedPost)
                    }
                    return@rememberSwipeToDismissBoxState isDismissed
                },
                positionalThreshold = { it * 0.5f }
            )

            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true,
                backgroundContent = {}
            ) {
                PostCard(
                    modifier = Modifier.padding(8.dp),
                    feedPost = feedPost,
                    onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(statisticItem, feedPost)
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(statisticItem, feedPost)
                    },
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(statisticItem, feedPost)
                    },
                    onCommentClickListener = {
                        onCommentClickListener(feedPost)
                    }
                )
            }
        }
    }
}