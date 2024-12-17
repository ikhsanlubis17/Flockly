package com.example.front_end.Screens

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import android.graphics.Color as AndroidColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmAnalyticsScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Analisis Data",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFEBF0EC)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEBF0EC))
                    .verticalScroll(scrollState) // Membuat layar dapat digulir
                    .padding(paddingValues) // Menambahkan padding agar konten tidak terpotong
                    .padding(10.dp) // Padding tambahan untuk konten
            ) {
                // Konten layar yang bisa digulir
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatisticCard(
                        title = "Total Pendapatan",
                        amount = "Rp.${getMonthlyTotal().first}",
                        percentage = "+12.5%",
                        percentageColor = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    StatisticCard(
                        title = "Total Pengeluaran",
                        amount = "Rp.${getMonthlyTotal().second}",
                        percentage = "-8.3%",
                        percentageColor = Color(0xFFF44336),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                ProductionStatistics("Bulanan") { /* Callback */ }

                Spacer(modifier = Modifier.height(16.dp))

                LivestockHealth()

                Spacer(modifier = Modifier.height(16.dp))

                IndicatorExplanationCard()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}


@Composable
fun IndicatorExplanationCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Penjelasan Indikator",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color(0xFFF44336), shape = RoundedCornerShape(50))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Merah: Buruk", fontSize = 14.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color(0xFFFFEB3B), shape = RoundedCornerShape(50))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Kuning: Kurang Baik", fontSize = 14.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color(0xFF4CAF50), shape = RoundedCornerShape(50))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Hijau: Baik", fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}

@Composable
fun StatisticCard(
    title: String,
    amount: String,
    percentage: String,
    percentageColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = amount,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = percentage,
                fontSize = 14.sp,
                color = percentageColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProductionStatistics(selectedPeriod: String, onPeriodChange: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Statistik Produksi", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(15.dp))

            PeriodDropdown(selectedPeriod, onPeriodChange)

            Spacer(modifier = Modifier.height(15.dp))

            AndroidView(
                factory = { context ->
                    LineChart(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            400
                        )
                    }
                },
                update = { chart ->
                    val data = if (selectedPeriod == "Bulanan") getMonthlyData() else getYearlyData()

                    val revenueDataSet = LineDataSet(data.first, "Pendapatan").apply {
                        color = AndroidColor.parseColor("#4CAF50")
                        lineWidth = 2f
                        circleRadius = 4f
                        setCircleColor(AndroidColor.parseColor("#4CAF50"))
                    }

                    val expenseDataSet = LineDataSet(data.second, "Pengeluaran").apply {
                        color = AndroidColor.parseColor("#F44336")
                        lineWidth = 2f
                        circleRadius = 4f
                        setCircleColor(AndroidColor.parseColor("#F44336"))
                    }

                    chart.data = LineData(revenueDataSet, expenseDataSet)
                    chart.description.isEnabled = false
                    chart.invalidate()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodDropdown(selectedPeriod: String, onPeriodChange: (String) -> Unit) {
    val periodOptions = listOf("Bulanan", "Tahunan")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedPeriod,
            onValueChange = {},
            readOnly = true,
            label = { Text("Pilih Periode") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            periodOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        expanded = false
                        onPeriodChange(option)
                    }
                )
            }
        }
    }
}

@Composable
fun LivestockHealth() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Kesehatan Ternak", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, // Menyebar elemen dengan jarak rata
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularIndicator("25%", "Ayam Pedaging")
                Spacer(modifier = Modifier.weight(1f)) // Menambahkan Spacer agar jarak rata
                CircularIndicator("40%", "Ayam Petelur")
                Spacer(modifier = Modifier.weight(1f)) // Menambahkan Spacer agar jarak rata
                CircularIndicator("85%", "Bebek")
                Spacer(modifier = Modifier.weight(1f)) // Menambahkan Spacer agar jarak rata
                CircularIndicator("95%", "Puyuh")
            }
        }
    }
}

@Composable
fun CircularIndicator(percentage: String, label: String) {
    val percentageValue = percentage.removeSuffix("%").toInt()
    val backgroundColor = when {
        percentageValue in 0..35 -> Color(0xFFF44336)
        percentageValue in 36..70 -> Color(0xFFFFEB3B)
        percentageValue in 71..100 -> Color(0xFF4CAF50)
        else -> Color.Gray
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(backgroundColor, shape = RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = percentage, color = Color.Black, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

fun getMonthlyData(): Pair<List<Entry>, List<Entry>> {
    val revenueEntries = listOf(Entry(0f, 10f), Entry(1f, 20f), Entry(2f, 15f))
    val expenseEntries = listOf(Entry(0f, 5f), Entry(1f, 15f), Entry(2f, 10f))
    return Pair(revenueEntries, expenseEntries)
}

fun getYearlyData(): Pair<List<Entry>, List<Entry>> {
    val revenueEntries = listOf(Entry(0f, 200f), Entry(1f, 300f), Entry(2f, 400f))
    val expenseEntries = listOf(Entry(0f, 150f), Entry(1f, 250f), Entry(2f, 300f))
    return Pair(revenueEntries, expenseEntries)
}

fun getMonthlyTotal(): Pair<Int, Int> = Pair(100000, 50000)
fun getYearlyTotal(): Pair<Int, Int> = Pair(1200000, 800000)

@Preview(showBackground = true)
@Composable
fun PreviewFarmAnalyticsScreen() {
    FarmAnalyticsScreen()
}
