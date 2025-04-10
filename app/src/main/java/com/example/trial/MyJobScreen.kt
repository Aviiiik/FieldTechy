package com.example.trial

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyJobsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Jobs", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState()) // Make the entire content scrollable
        ) {
            ProfileCard(navController)
            ServicesSection()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "In Progress",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            // In Progress Jobs Section - vertical layout
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Multiple job cards stacked vertically
                JobCard(navController, "Angular Developer", "New Jersey, USA", "$1200")
                JobCard(navController, "React Developer", "San Francisco, CA", "$1500")
                JobCard(navController, "Flutter Developer", "Austin, TX", "$1300")
            }

            // Add some bottom padding for better scrolling experience
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileCard(navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Default Profile",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.55f) // allocate part of the row width
            ) {
                Text(text = "Hello, User", fontWeight = FontWeight.Bold)
                Text(
                    text = "Create your profile to get started",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            OutlinedButton(onClick = {
                navController.navigate("register")
            }) {
                Text("Create Profile")
            }
        }
    }
}

@Composable
fun ServicesSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Available Services",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ServiceChip("All")
            ServiceChip("Cloud")
            ServiceChip("Support")
            ServiceChip("Network")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "My Jobs", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Horizontally scrollable row for job status cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // All three cards with fixed equal width
            JobStatCard(
                title = "Job Scheduled",
                count = "4 Jobs",
                bgColor = Color(0xFFFFF9C4),
                modifier = Modifier.width(120.dp)
            )
            JobStatCard(
                title = "Job Completed",
                count = "92 Jobs",
                bgColor = Color(0xFFC8E6C9),
                modifier = Modifier.width(120.dp)
            )
            JobStatCard(
                title = "Job Cancelled",
                count = "12 Jobs",
                bgColor = Color(0xFFB3E5FC),
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
fun ServiceChip(text: String) {
    Surface(
        color = Color.LightGray.copy(alpha = 0.2f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
fun JobStatCard(
    title: String,
    count: String,
    bgColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                imageVector = Icons.Default.WorkOutline,
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = title,
                fontSize = 12.sp,
                maxLines = 1,
                softWrap = false
            )
            Text(
                text = count,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                softWrap = false
            )
            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("View Jobs", fontSize = 12.sp, maxLines = 1, softWrap = false)
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        color = Color(0xFFE0E0E0),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
fun JobCard(
    navController: NavController,
    jobTitle: String = "Angular Developer",
    location: String = "New Jersey, USA",
    salary: String = "$1200"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = jobTitle, fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
                Text(text = location, color = Color.Gray)
                Text(text = salary, fontWeight = FontWeight.Bold, color = Color.Green)
                // "Create a Profile" button removed
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyJobsScreenPreview() {
    val navController = rememberNavController()
    MyJobsScreen(navController)
}