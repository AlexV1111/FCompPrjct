package com.example.firstcomposeproject.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
private fun Test() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Ex4()
    }
}

@Composable
private fun Ex4() {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                NavigationDrawerItem(
                    label = { TextColumn("call phone!?!") },
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Filled.Call, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { TextColumn("date!!!") },
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Filled.DateRange, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { TextColumn("send email") },
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Filled.Email, contentDescription = null) }
                )
            }
        }
    )

    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "AppBar")
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Cyan
                    )
                )
            },
            content = {
                Text(
                    modifier = Modifier.padding(it),
                    text = "This is scaffold content",
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic

                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.Cyan
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Build, contentDescription = null)
                    }
                }
            }
        )
    }
}

@Composable
private fun TextColumn(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Ex1() {
    OutlinedButton(onClick = {}) {
        Text("Hello World")
    }
}

@Composable
private fun Ex2() {
    TextField(
        value = "Value",
        onValueChange = {},
        label = { Text("Label") },
    )
}

@Composable
private fun Ex3() {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Are you sure?") },
        text = { Text("Do you want to delete this file?") },
        confirmButton = {
            Text("Yes")
        },
        dismissButton = {
            Text("No")
        }
    )
}






