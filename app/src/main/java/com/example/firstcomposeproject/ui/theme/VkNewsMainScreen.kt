package com.example.firstcomposeproject.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firstcomposeproject.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                val selectedItemPosition = remember { mutableStateOf(0) }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        ShowPostList(viewModel, paddingValues)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowPostList(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val listFeedPost = viewModel.listFeedPost.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(
            bottom = 80.dp
        )
    ) {
        items(
            items = listFeedPost.value,
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
                    onCommentClickListener = { statisticItem ->
                        viewModel.updateCount(statisticItem, feedPost)
                    }
                )
            }
        }
    }
}

