package com.example.trial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EngineerRegistrationPage() {
    var currentStep by remember { mutableIntStateOf(0) }
    val steps = listOf("Profile", "Skills", "Jobs", "Payment", "Wallet")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Engineer Registration") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Stepper
            LinearProgressIndicator(
                progress = (currentStep + 1).toFloat() / steps.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                steps.forEachIndexed { index, step ->
                    Text(
                        text = step,
                        color = if (index <= currentStep) MaterialTheme.colorScheme.primary else Color.Gray,
                        fontWeight = if (index == currentStep) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }

            // Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    when (currentStep) {
                        0 -> ProfileSection()
                        1 -> SkillsSection()
                        2 -> JobsSection()
                        3 -> PaymentRequirementsSection()
                        4 -> WalletSection()
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (currentStep > 0) {
                            OutlinedButton(
                                onClick = { currentStep-- },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Previous")
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }

                        Button(
                            onClick = {
                                if (currentStep < steps.size - 1) currentStep++
                                else {
                                    // Submit form
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(if (currentStep == steps.size - 1) "Submit" else "Next")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSection() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Personal Information",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Profile Picture
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(50.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.weight(1f),
                leadingIcon = { Icon(Icons.Default.Person, "First Name") }
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.weight(1f),
                leadingIcon = { Icon(Icons.Default.Person, "Last Name") }
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Email, "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Phone, "Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsSection() {
    var newSkill by remember { mutableStateOf("") }
    val skills = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Skills & Expertise",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Add skill input
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newSkill,
                onValueChange = { newSkill = it },
                label = { Text("Add a skill") },
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    if (newSkill.isNotBlank()) {
                        skills.add(newSkill)
                        newSkill = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Skill")
            }
        }

        // Skills list
        if (skills.isNotEmpty()) {
            Text(
                text = "Your Skills",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(200.dp)
            ) {
                items(skills) { skill ->
                    SkillItem(
                        skill = skill,
                        onDelete = { skills.remove(skill) }
                    )
                }
            }
        }

        // Suggested skills
        Text(
            text = "Suggested Skills",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        FlowRow(
            skills = listOf("JavaScript", "React", "Node.js", "Python", "Java", "Kotlin", "SQL", "AWS"),
            onSkillClick = {
                if (!skills.contains(it)) {
                    skills.add(it)
                }
            }
        )
    }
}

@Composable
fun SkillItem(skill: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = skill)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Skill")
            }
        }
    }
}

@Composable
fun FlowRow(skills: List<String>, onSkillClick: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.take(4).forEach { skill ->
                SuggestedSkillChip(skill = skill, onClick = { onSkillClick(skill) })
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.drop(4).forEach { skill ->
                SuggestedSkillChip(skill = skill, onClick = { onSkillClick(skill) })
            }
        }
    }
}

@Composable
fun SuggestedSkillChip(skill: String, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = skill,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Icon(
                Icons.Default.Add,
                contentDescription = "Add Skill",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsSection() {
    var jobTitle by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }
    val jobTypes = listOf("Full-time", "Part-time", "Contract", "Freelance", "Remote")
    val selectedJobTypes = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Job Preferences",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = jobTitle,
            onValueChange = { jobTitle = it },
            label = { Text("Desired Job Title") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Work, "Job Title") }
        )

        OutlinedTextField(
            value = jobDescription,
            onValueChange = { jobDescription = it },
            label = { Text("Job Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )

        Text(
            text = "Job Types",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Column {
            jobTypes.forEach { jobType ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = selectedJobTypes.contains(jobType),
                        onCheckedChange = {
                            if (it) selectedJobTypes.add(jobType)
                            else selectedJobTypes.remove(jobType)
                        }
                    )
                    Text(jobType)
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Location preferences
        Text(
            text = "Location Preferences",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        var remoteOnly by remember { mutableStateOf(false) }
        var preferredLocation by remember { mutableStateOf("") }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Switch(
                checked = remoteOnly,
                onCheckedChange = { remoteOnly = it }
            )
            Text("Remote Only", modifier = Modifier.padding(start = 8.dp))
        }

        if (!remoteOnly) {
            OutlinedTextField(
                value = preferredLocation,
                onValueChange = { preferredLocation = it },
                label = { Text("Preferred Location") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentRequirementsSection() {
    var paymentType by remember { mutableStateOf("Hourly") }
    var hourlyRate by remember { mutableStateOf("") }
    var annualSalary by remember { mutableStateOf("") }
    var sliderValue by remember { mutableFloatStateOf(50f) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Payment Requirements",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Payment type selection
        Text(
            text = "Payment Type",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable { paymentType = "Hourly" }
            ) {
                RadioButton(
                    selected = paymentType == "Hourly",
                    onClick = { paymentType = "Hourly" }
                )
                Text("Hourly Rate")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable { paymentType = "Annual" }
            ) {
                RadioButton(
                    selected = paymentType == "Annual",
                    onClick = { paymentType = "Annual" }
                )
                Text("Annual Salary")
            }
        }

        // Rate input based on selection
        if (paymentType == "Hourly") {
            OutlinedTextField(
                value = hourlyRate,
                onValueChange = { hourlyRate = it },
                label = { Text("Hourly Rate ($)") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.AttachMoney, "Hourly Rate") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Adjust your hourly rate:",
                style = MaterialTheme.typography.bodyMedium
            )

            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    hourlyRate = (it.toInt()).toString()
                },
                valueRange = 10f..200f,
                steps = 19
            )

            Text(
                text = "$${sliderValue.toInt()} per hour",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        } else {
            OutlinedTextField(
                value = annualSalary,
                onValueChange = { annualSalary = it },
                label = { Text("Annual Salary ($)") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.AttachMoney, "Annual Salary") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "Adjust your annual salary:",
                style = MaterialTheme.typography.bodyMedium
            )

            Slider(
                value = sliderValue,
                onValueChange = {
                    sliderValue = it
                    annualSalary = ((it * 1000).toInt()).toString()
                },
                valueRange = 30f..200f,
                steps = 17
            )

            Text(
                text = "$${(sliderValue * 1000).toInt()} per year",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletSection() {
    var selectedPaymentMethod by remember { mutableStateOf("") }
    var showAddFundsDialog by remember { mutableStateOf(false) }
    var walletBalance by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Wallet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Wallet balance card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Current Balance",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$${String.format("%.2f", walletBalance)}",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Button(
                    onClick = { showAddFundsDialog = true },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Funds")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add $10 to Wallet")
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Payment methods
        Text(
            text = "Payment Methods",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        val paymentMethods = listOf(
            PaymentMethod("Credit Card", Icons.Default.CreditCard),
            PaymentMethod("Debit Card", Icons.Default.CreditCard),
            PaymentMethod("PayPal", Icons.Default.Payment),
            PaymentMethod("UPI", Icons.Default.Payment)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            paymentMethods.forEach { method ->
                PaymentMethodItem(
                    method = method,
                    isSelected = selectedPaymentMethod == method.name,
                    onClick = { selectedPaymentMethod = method.name }
                )
            }
        }

        // Transaction history
        if (walletBalance > 0) {
            Text(
                text = "Transaction History",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Added funds via $selectedPaymentMethod")
                        Text("Today", color = Color.Gray, fontSize = 12.sp)
                    }

                    Text(
                        text = "+$10.00",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Add funds dialog
    if (showAddFundsDialog) {
        Dialog(onDismissRequest = { showAddFundsDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Add $10 to Wallet",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text("Select payment method:")

                    val paymentMethods = listOf(
                        PaymentMethod("Credit Card", Icons.Default.CreditCard),
                        PaymentMethod("Debit Card", Icons.Default.CreditCard),
                        PaymentMethod("PayPal", Icons.Default.Payment),
                        PaymentMethod("UPI", Icons.Default.Payment)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        paymentMethods.forEach { method ->
                            PaymentMethodItem(
                                method = method,
                                isSelected = selectedPaymentMethod == method.name,
                                onClick = { selectedPaymentMethod = method.name }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showAddFundsDialog = false }) {
                            Text("Cancel")
                        }

                        Button(
                            onClick = {
                                if (selectedPaymentMethod.isNotBlank()) {
                                    walletBalance += 10.0
                                    showAddFundsDialog = false
                                }
                            },
                            enabled = selectedPaymentMethod.isNotBlank()
                        ) {
                            Text("Add Funds")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentMethodItem(method: PaymentMethod, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = method.icon,
                contentDescription = method.name,
                tint = if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = method.name,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.weight(1f))

            RadioButton(
                selected = isSelected,
                onClick = onClick
            )
        }
    }
}

data class PaymentMethod(val name: String, val icon: ImageVector)

// Individual section previews
@Preview(showBackground = true, name = "Profile Section")
@Composable
fun ProfileSectionPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            ProfileSection()
        }
    }
}

@Preview(showBackground = true, name = "Skills Section")
@Composable
fun SkillsSectionPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            SkillsSection()
        }
    }
}

@Preview(showBackground = true, name = "Jobs Section")
@Composable
fun JobsSectionPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            JobsSection()
        }
    }
}

@Preview(showBackground = true, name = "Payment Requirements Section")
@Composable
fun PaymentRequirementsSectionPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            PaymentRequirementsSection()
        }
    }
}

@Preview(showBackground = true, name = "Wallet Section")
@Composable
fun WalletSectionPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            WalletSection()
        }
    }
}

// Full page preview
@Preview(showBackground = true, name = "Full Registration Page")
@Composable
fun EngineerRegistrationPreview() {
    MaterialTheme {
        EngineerRegistrationPage()
    }
}