package com.example.trial

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyJobsScreen(navController: NavController) {
    // Simulated user profile state
    var userName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Jobs", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ProfileCard(userName = userName, onCreateProfile = {
                navController.navigate("register")
            })
            SearchBar()
            FilterBar()
            JobList(navController = navController)
        }
    }
}

@Composable
fun ProfileCard(userName: String, onCreateProfile: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Photo",
                    modifier = Modifier.padding(16.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (userName.isEmpty()) "Hello, User" else "Hello, $userName",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = if (userName.isEmpty()) "Create your profile to get started" else "Welcome back!",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            TextButton(onClick = onCreateProfile) {
                Text(text = if (userName.isEmpty()) "Create Profile" else "Edit")
            }
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        placeholder = { Text("Search Jobs") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun FilterBar() {
    val filters = listOf("All", "Today", "In Progress", "In Hold", "Draft", "Cancelled")
    var selectedFilter by remember { mutableStateOf("All") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { filter ->
            Text(
                text = filter,
                color = if (filter == selectedFilter) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier
                    .clickable { selectedFilter = filter },
                fontWeight = if (filter == selectedFilter) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
    Text(
        text = "Total 37 jobs",
        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
        color = Color.Gray
    )
}

@Composable
fun JobList(navController: NavController) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(2) {
            JobCard(navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun JobCard(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Angular Developer", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "In Progress", color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Chip(text = "On Site")
                Chip(text = "8 hours")
                Chip(text = "Dedicated Service")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "New Jersey, USA", color = Color.Gray)
                Text(text = "$1200", fontWeight = FontWeight.Bold, color = Color.Green)
                OutlinedButton(onClick = {
                    navController.navigate("register")
                }) {
                    Text(text = "Create a Profile", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun MyJobsScreenPreview() {
    val navController = rememberNavController()
    MyJobsScreen(navController)
}

