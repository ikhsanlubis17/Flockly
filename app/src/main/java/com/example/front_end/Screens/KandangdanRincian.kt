package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KandangdanRincian(
    navController: NavHostController,
    backIcon: Int,
    searchIcon: Int,
    addIcon: Int,
    kandangAyamImage: Int,
    kandangBebekImage: Int,
    kandangPuyuhImage: Int,
    searchIconSize: Dp = 20.dp,
    addIconSize: Dp = 50.dp
) {
    var searchText by remember { mutableStateOf("") }

    val kandangList = listOf(
        KandangItem("Kandang Ayam", "Cipondoh, RT 010/004 Tangerang", kandangAyamImage),
        KandangItem("Kandang Bebek", "Cipondoh, RT 010/004 Tangerang", kandangBebekImage),
        KandangItem("Kandang Puyuh", "Cipondoh, RT 010/004 Tangerang", kandangPuyuhImage)
    )

    val filteredKandangList = kandangList.filter {
        it.title.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        // Menggunakan Box untuk memastikan teks di tengah layar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 0.dp, end = 50.dp), // padding tambahan agar tidak terhalang oleh icon
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Kandang",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        }
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = backIcon),
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(55.dp) // Ukuran ikon lebih besar dari sebelumnya
                                .clickable {navController.navigate(Screen.HomePage.route)}
                                .padding(10.dp),
                            tint = Color.Black
                        )
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFFEBF0EC)
                    )
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEBF0EC))
                        .padding(16.dp)
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Apa yang kamu cari ?") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = searchIcon),
                                contentDescription = "Search Icon",
                                tint = Color.Black,
                                modifier = Modifier.size(searchIconSize)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(Screen.TambahKandang.route)},
                containerColor = Color(0xFF5F4A43)
            ) {
                Icon(
                    painter = painterResource(id = addIcon),
                    contentDescription = "Add",
                    modifier = Modifier.size(addIconSize),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center, // Menempatkan FAB di tengah

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFEBF0EC))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                filteredKandangList.forEachIndexed { index, kandang ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6C3A3)),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = kandang.imageRes),
                                contentDescription = "${kandang.title} Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .height(150.dp)
                                    .clickable {
                                        navController.navigate(Screen.ProfilKandang.route)
                                        // Tindakan yang ingin diambil saat gambar diklik
                                        // Contoh: navigasi ke halaman detail atau aksi lainnya
                                    }
                            )

                            Spacer(modifier = Modifier.height(2.dp))
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = kandang.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Text(
                                text = kandang.location,
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class KandangItem(
    val title: String,
    val location: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun KandangdanRincianPreview() {
    Front_EndTheme {
        KandangdanRincian(
            navController = rememberNavController(),
            backIcon = R.drawable.back1,
            searchIcon = R.drawable.search,
            addIcon = android.R.drawable.ic_input_add,
            kandangAyamImage = R.drawable.fotokandang,
            kandangBebekImage = R.drawable.fotokandang,
            kandangPuyuhImage = R.drawable.fotokandang
        )
    }
}
