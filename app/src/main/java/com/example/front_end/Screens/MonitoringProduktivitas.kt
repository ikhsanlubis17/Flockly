package com.example.front_end.Screens

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import android.graphics.Color as AndroidColor
import com.example.front_end.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Monitoring(navController: NavHostController) {
    var selectedTimeRange by remember { mutableStateOf("Mingguan") } // Pilihan rentang waktu
    val timeRanges = listOf("Mingguan", "Bulanan", "Tahunan")

    // Mengambil data produksi untuk masing-masing kategori
    val dataAyam = getDataForTimeRange(selectedTimeRange, "Ayam")
    val dataBebek = getDataForTimeRange(selectedTimeRange, "Bebek")
    val dataPuyuh = getDataForTimeRange(selectedTimeRange, "Puyuh")

    // Menghitung total jumlah dan persentase perubahan
    val totalAyam = dataAyam.sum()
    val totalBebek = dataBebek.sum()
    val totalPuyuh = dataPuyuh.sum()

    val changeAyam = calculatePercentageChange(dataAyam)
    val changeBebek = calculatePercentageChange(dataBebek)
    val changePuyuh = calculatePercentageChange(dataPuyuh)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
            .padding(16.dp)
    ) {
        // TopAppBar with custom drawable back icon
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Ensures title is centered
                ) {
                    Text(
                        text = "Monitoring Produktivitas",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigate(Screen.HomePage.route)}) {
                    val backIcon: Painter = painterResource(id = R.drawable.back1) // Replace with your custom icon
                    Icon(
                        painter = backIcon,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp) // Increase icon size here
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            // Set background color
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEBF0EC))
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Pilihan rentang waktu
        PeriodDropdown1(
            selectedPeriod = selectedTimeRange,
            onPeriodChange = { selectedTimeRange = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Header Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                InfoCard(title = "Ayam", value = totalAyam.toString(), change = changeAyam)
            }
            Box(modifier = Modifier.weight(1f)) {
                InfoCard(title = "Bebek", value = totalBebek.toString(), change = changeBebek)
            }
            Box(modifier = Modifier.weight(1f)) {
                InfoCard(title = "Puyuh", value = totalPuyuh.toString(), change = changePuyuh, isNegative = totalPuyuh < 0)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Produksi Telur Chart
        ChartCard(
            title = "Perbandingan Produksi Telur",
            labels = listOf("Ayam", "Bebek", "Puyuh"),
            dataAyam = dataAyam,
            dataBebek = dataBebek,
            dataPuyuh = dataPuyuh,
            colors = listOf(AndroidColor.parseColor("#2196F3"), AndroidColor.parseColor("#4CAF50"), AndroidColor.parseColor("#FFEB3B"))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Produksi Daging Chart
        ChartCard(
            title = "Perbandingan Produksi Daging",
            labels = listOf("Ayam", "Bebek", "Puyuh"),
            dataAyam = dataAyam,
            dataBebek = dataBebek,
            dataPuyuh = dataPuyuh,
            colors = listOf(AndroidColor.parseColor("#2196F3"), AndroidColor.parseColor("#4CAF50"), AndroidColor.parseColor("#FFEB3B"))
        )
    }
}


fun calculatePercentageChange(data: List<Float>): String {
    if (data.isEmpty()) return "0%"

    val startValue = data.first()
    val endValue = data.last()

    val change = ((endValue - startValue) / startValue) * 100
    return String.format("%.1f%%", change)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodDropdown1(selectedPeriod: String, onPeriodChange: (String) -> Unit) {
    val periodOptions = listOf("Mingguan", "Bulanan", "Tahunan")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedPeriod,
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Pilih Rentang Waktu", color = Color.Black) // Set label text color to black
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(color = Color.Black) // Set the text color inside the field to black
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

// Fungsi untuk mengambil data sesuai rentang waktu yang dipilih
fun getDataForTimeRange(timeRange: String, animal: String): List<Float> {
    return when (timeRange) {
        "Mingguan" -> {
            // Data mingguan
            when (animal) {
                "Ayam" -> listOf(2450f, 2400f, 2500f, 2600f, 2550f, 2700f, 2650f)
                "Bebek" -> listOf(1800f, 1750f, 1850f, 1800f, 1820f, 1900f, 1850f)
                "Puyuh" -> listOf(3200f, 3100f, 3250f, 3300f, 3400f, 3200f, 3150f)
                else -> emptyList()
            }
        }
        "Bulanan" -> {
            // Data bulanan
            when (animal) {
                "Ayam" -> listOf(2450f, 2500f, 2600f, 2450f, 2550f, 2700f, 2750f, 2600f, 2680f, 2650f, 2700f, 2800f)
                "Bebek" -> listOf(1800f, 1850f, 1900f, 1820f, 1780f, 1850f, 1880f, 1800f, 1750f, 1800f, 1850f, 1900f)
                "Puyuh" -> listOf(3200f, 3100f, 3250f, 3300f, 3400f, 3200f, 3150f, 3250f, 3300f, 3400f, 3450f, 3500f)
                else -> emptyList()
            }
        }
        "Tahunan" -> {
            // Data tahunan
            when (animal) {
                "Ayam" -> listOf(30000f, 32000f, 33000f, 34000f, 35000f)
                "Bebek" -> listOf(22000f, 22500f, 23000f, 23500f, 24000f)
                "Puyuh" -> listOf(38000f, 39000f, 40000f, 41000f, 42000f)
                else -> emptyList()
            }
        }
        else -> emptyList()
    }
}

@Composable
fun ChartCard(title: String, labels: List<String>, dataAyam: List<Float>, dataBebek: List<Float>, dataPuyuh: List<Float>, colors: List<Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Draw Line Chart
            LineChartView(labels, dataAyam, dataBebek, dataPuyuh, colors)
        }
    }
}

@Composable
fun LineChartView(labels: List<String>, dataAyam: List<Float>, dataBebek: List<Float>, dataPuyuh: List<Float>, colors: List<Int>) {
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
            // Prepare the chart data for Ayam
            val entriesAyam = dataAyam.mapIndexed { index, value ->
                Entry(index.toFloat(), value)
            }
            val dataSetAyam = LineDataSet(entriesAyam, "Ayam").apply {
                color = colors[0]
                setCircleColor(colors[0])
                lineWidth = 2f
                circleRadius = 4f
            }

            // Prepare the chart data for Bebek
            val entriesBebek = dataBebek.mapIndexed { index, value ->
                Entry(index.toFloat(), value)
            }
            val dataSetBebek = LineDataSet(entriesBebek, "Bebek").apply {
                color = colors[1]
                setCircleColor(colors[1])
                lineWidth = 2f
                circleRadius = 4f
            }

            // Prepare the chart data for Puyuh
            val entriesPuyuh = dataPuyuh.mapIndexed { index, value ->
                Entry(index.toFloat(), value)
            }
            val dataSetPuyuh = LineDataSet(entriesPuyuh, "Puyuh").apply {
                color = colors[2]
                setCircleColor(colors[2])
                lineWidth = 2f
                circleRadius = 4f
            }

            // Set data for the chart
            val data = LineData(dataSetAyam, dataSetBebek, dataSetPuyuh)
            chart.data = data
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            chart.invalidate()
        }
    )
}

@Composable
fun InfoCard(title: String, value: String, change: String, isNegative: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                change,
                fontSize = 14.sp,
                color = if (isNegative) Color.Red else Color(0xFF008000)
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewMonitoring() {
    Monitoring(navController = rememberNavController())
}
