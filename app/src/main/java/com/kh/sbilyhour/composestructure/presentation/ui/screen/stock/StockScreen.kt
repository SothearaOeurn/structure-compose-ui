package com.kh.sbilyhour.composestructure.presentation.ui.screen.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// 🚀 Stock List Screen
@Composable
fun StockScreen() {
    val stockList = listOf(
        Stock("AAPL", "Apple Inc.", 187.50, "+2.45%"),
        Stock("TSLA", "Tesla Inc.", 250.30, "-1.23%"),
        Stock("AMZN", "Amazon Inc.", 135.90, "+0.75%"),
        Stock("GOOGL", "Alphabet Inc.", 2750.65, "-0.98%"),
    )

    Column {
        StockSummaryCard(totalValue = "$15,320.45", dailyChange = "+3.12%")
        LazyColumn {
            items(stockList) { stock ->
                StockItem(stock)
            }
        }
    }
}

// 🚀 Stock Summary Card
@Composable
fun StockSummaryCard(totalValue: String, dailyChange: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Total Portfolio Value", style = MaterialTheme.typography.titleMedium)
            Text(
                totalValue,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Daily Change: $dailyChange",
                color = if (dailyChange.contains("+")) Color.Green else Color.Red
            )
        }
    }
}

// 🚀 Stock Item
@Composable
fun StockItem(stock: Stock) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(stock.symbol, fontWeight = FontWeight.Bold)
                Text(stock.company, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            Column {
                Text("$${stock.price}", fontWeight = FontWeight.Bold)
                Text(
                    stock.change,
                    color = if (stock.change.contains("+")) Color.Green else Color.Red
                )
            }
        }
    }
}

// 🚀 Data Model
data class Stock(val symbol: String, val company: String, val price: Double, val change: String)