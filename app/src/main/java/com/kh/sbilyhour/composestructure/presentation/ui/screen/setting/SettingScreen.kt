package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Setting", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Use LazyColumn to handle scrollable content
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize() // Ensure the LazyColumn fills the screen
                .padding(paddingValues)
        ) {
            // Add header as a separate item
            item {
                Text(
                    text = "Header Section",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // List of ExpandableItems
            items(30) { index ->
                ExpandableListItem(
                    title = "I. Loan Account information $index",
                    description = "Description for item $index."
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandableListItem(title: String, description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(if (isExpanded) 180f else 0f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = Color.Blue,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expand",
                modifier = Modifier
                    .rotate(rotationAngle)
                    .size(28.dp)
                    .clickable { isExpanded = !isExpanded }
            )
        }

        val detailsList = listOf(
            Section(
                title = "1. Staff Information",
                sub_list = listOf(
                    SubListItem(keyword = "Code", key_value = "KH0010001"),
                    SubListItem(keyword = "Name", key_value = "Head Office"),
                    SubListItem(keyword = "RM-Name", key_value = "Chy Sarat")
                )
            ),
            Section(
                title = "2. Customer Information",
                sub_list = listOf(
                    SubListItem(keyword = "CIF#", key_value = "1166508"),
                    SubListItem(keyword = "Acc Number", key_value = "000372579"),
                    SubListItem(keyword = "Account Name", key_value = "MARK SREYLONG"),
                    SubListItem(
                        keyword = "Address",
                        key_value = "Phnom Penh/Chamkar Mon/Tonle Basak/Phum 9"
                    )
                )
            ),
            Section(
                title = "1.  Loan Information",
                sub_list = listOf(
                    SubListItem(keyword = "Currency", key_value = "USD"),
                    SubListItem(keyword = "Disburse Amount", key_value = "0.00"),
                    SubListItem(keyword = "Disburse Date", key_value = "06-10-2020"),
                    SubListItem(keyword = "Outstanding Amount", key_value = "187,939.59"),
                    SubListItem(keyword = "Product Type", key_value = "MORTGAGE.HOUSING.LOAN.LT"),
                )
            ),
        )

        AnimatedVisibility(visible = isExpanded) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(12.dp)
            ) {
                detailsList.forEach { section ->
                    // Subtitle
                    Text(
                        text = section.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Wrap in a Box with max height constraints to prevent infinite height issue
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp) // Prevents infinite height issues
                            .verticalScroll(rememberScrollState()) // Make it scrollable
                    ) {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            maxItemsInEachRow = 3 // Ensures 2-column grid
                        ) {
                            section.sub_list.forEach { item ->
                                Column(
                                    modifier = Modifier
                                        .background(
                                            Color.LightGray,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(8.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = item.keyword,
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        maxLines = 1,
                                        text = item.key_value,
                                        fontSize = 13.sp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

data class SubListItem(
    val keyword: String,
    val key_value: String
)

data class Section(
    val title: String,
    val sub_list: List<SubListItem>
)






