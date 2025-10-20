package com.example.firstcomposeproject.ui.theme

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeproject.NewsFeedViewModel
import com.example.firstcomposeproject.domain.FeedPost
import com.example.firstcomposeproject.navigation.AppNavGraph
import com.example.firstcomposeproject.navigation.Screen
import com.example.firstcomposeproject.navigation.rememberNavigationState

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
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
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {feedPost ->
                        navigationState.navigateToComments(feedPost = feedPost)
                    }
                )
            },
            commentsScreenContent = {feedPost ->
                CommentsScreen(
                    onBachPressed = { navigationState.navHostController.popBackStack() },
                    feedPost = feedPost
                )
            },
            favouriteScreenContent = { Text(text = "Favourite", color = Color.Red) },
            profileScreenContent = { Text(text = "Profile", color = Color.Red) }

        )
    }
}

