package com.example.trial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyJobsScreen() {
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
            SearchBar()
            FilterBar()
            JobList()
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
            .padding(16.dp)
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
                    .clickable {
                        selectedFilter = filter
                    },
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
fun JobList() {
    LazyColumn(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        items(count = 2) {
            JobCard()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun JobCard() {
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
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text = "View logs", fontSize = 12.sp)
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
fun MyJobsPreview() {
    MyJobsScreen()
}