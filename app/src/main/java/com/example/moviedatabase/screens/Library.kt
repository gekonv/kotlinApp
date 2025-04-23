package com.example.moviedatabase.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviedatabase.data.local.Watchlist
import com.example.moviedatabase.data.viewModel.LibraryViewModel
import com.example.moviedatabase.navigation.BottomNavigation
import com.example.moviedatabase.navigation.bottomNavItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(bottomNavItems: List<bottomNavItem>,
                  currentDestination: String?,
                  viewModel: LibraryViewModel = viewModel(),
                  onItemClick: (Long) -> Unit
){
    val Watchlists by viewModel.watchlist.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Library")
                }
            )
        },
        bottomBar = { BottomNavigation(
            navigationItems = bottomNavItems,
            currentRoute = currentDestination
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, "add list")
            }
        },
        modifier = Modifier.fillMaxSize(),
    ){ innerPadding ->

        LibraryContent(
            modifier = Modifier.padding(innerPadding),
            watchlists = Watchlists,
            delete = { watchlist ->
                viewModel.deleteWatchlist(watchlist)
            },
            onItemClick = onItemClick
        )
        CreateWatchlistDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onCreate = { name ->
                viewModel.createWatchlist(name)
            }
        )
    }
}

@Composable
fun LibraryContent(
    modifier: Modifier = Modifier,
    watchlists: List<Watchlist>,
    onItemClick: (Long) -> Unit,
    delete: (Watchlist) -> Unit
){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(watchlists) { watchlist ->
            WatchListItem(
                watchlist = watchlist,
                onItemClick = onItemClick,
                delete = delete,
            )
        }
    }
}

@Composable
fun WatchListItem(
    watchlist: Watchlist,
    onItemClick: (Long) -> Unit,
    delete: (Watchlist) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    if (showDeleteDialog) {
        ConfirmDeleteDialog(
            watchlist = watchlist,
            confirm = {
                delete(watchlist)
                showDeleteDialog = false
            },
            cancel = {
                showDeleteDialog = false
            }
        )
    }
    Card(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 10.dp),
        onClick = { onItemClick(watchlist.listId) }
    ){
        Row (
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 25.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                watchlist.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete watchlist",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { showDeleteDialog = true }
            )
        }
    }
}

@Composable
fun CreateWatchlistDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {
    var watchlistName by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                watchlistName = ""
                showError = false
                onDismiss()
            },
            title = { Text("Create New Watchlist") },
            text = {
                Column {
                    Text("Enter a name for your watchlist:")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = watchlistName,
                        onValueChange = {
                            watchlistName = it
                            if (it.isNotBlank()) showError = false
                        },
                        placeholder = { Text("Watchlist name") },
                        isError = showError,
                        singleLine = true
                    )
                    if (showError) {
                        Text(
                            text = "Name can't be empty",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (watchlistName.isBlank()) {
                            showError = true
                        } else {
                            onCreate(watchlistName.trim())
                            watchlistName = ""
                            showError = false
                            onDismiss()
                        }
                    }
                ) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        watchlistName = ""
                        showError = false
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ConfirmDeleteDialog(
    watchlist: Watchlist,
    confirm: () -> Unit,
    cancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = cancel,
        title = { Text("Delete Watchlist") },
        text = {
            Text("Are you sure you want to delete \"${watchlist.name}\"? This will also remove all the saved movies in the watchlist.")
        },
        confirmButton = {
            TextButton(onClick = confirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = cancel) {
                Text("Cancel")
            }
        }
    )
}