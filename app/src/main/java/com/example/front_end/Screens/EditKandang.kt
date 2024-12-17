package com.example.front_end.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.front_end.Navigasi.Screen
import com.example.front_end.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditKandang(navController: NavHostController) {
    val backgroundColor = Color(0xFFEBF0EC)
    val textColor = Color(0xFF333333)
    val borderColor = Color(0xFFB0B0B0)
    val primaryButtonColor = Color(0xFF5F4A43)
    val buttonTextColor = Color.White

    // Inisialisasi dengan nilai default
    var namaKandang by remember { mutableStateOf("Kandang Ayam") }
    var alamat by remember { mutableStateOf("Jl. Irigasi SIpon Cipondoh RT 010/004") }
    var kota by remember { mutableStateOf("Tangerang") }
    var populasi by remember { mutableStateOf("5 ekor") }
    var jenisKandang by remember { mutableStateOf("Terbuka") }
    var keterangan by remember { mutableStateOf("-") }
    var isKotaDropdownExpanded by remember { mutableStateOf(false) }
    var isJenisDropdownExpanded by remember { mutableStateOf(false) }

    val kotaOptions = listOf("Jakarta", "Bandung", "Surabaya")
    val jenisOptions = listOf("Terbuka", "Tertutup")

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // TopAppBar
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Edit Kandang",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigate(Screen.ProfilKandang.route)}) {
                    Icon(
                        painter = painterResource(id = R.drawable.back1),
                        contentDescription = "Kembali",
                        tint = textColor,
                        modifier = Modifier.size(32.dp) // Increase size of the back icon
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor
            ),
            modifier = Modifier.padding(horizontal = 16.dp) // Add padding to the top app bar
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp) // Add padding to the entire screen
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Konten Formulir
            TextFieldWithLabels(
                label = "Nama Kandang",
                value = namaKandang,
                onValueChange = { namaKandang = it },
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            TextFieldWithLabels(
                label = "Alamat",
                value = alamat,
                onValueChange = { alamat = it },
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            DropdownFieldWithLabels(
                label = "Kota",
                selectedOption = kota,
                options = kotaOptions,
                isExpanded = isKotaDropdownExpanded,
                onExpandChange = { isKotaDropdownExpanded = it },
                onSelectOption = { kota = it },
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldWithLabels(
                label = "Populasi",
                value = populasi,
                onValueChange = { populasi = it },
                keyboardType = KeyboardType.Number,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            DropdownFieldWithLabels(
                label = "Jenis Kandang",
                selectedOption = jenisKandang,
                options = jenisOptions,
                isExpanded = isJenisDropdownExpanded,
                onExpandChange = { isJenisDropdownExpanded = it },
                onSelectOption = { jenisKandang = it },
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldWithLabel(
                label = "Keterangan",
                value = keterangan,
                onValueChange = { keterangan = it },
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                textColor = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .clickable { /* Handle upload photo */ },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.upload),
                        contentDescription = "Upload Icon",
                        tint = Color(0xFF5F4A43),
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Unggah Foto",
                        fontSize = 12.sp,
                        color = textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Data berhasil diedit", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryButtonColor,
                    contentColor = buttonTextColor
                )
            ) {
                Text(
                    text = "Edit",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TextFieldWithLabels(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color
) {
    Column {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = textColor
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = backgroundColor,
                focusedContainerColor = backgroundColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                focusedIndicatorColor = borderColor,
                unfocusedIndicatorColor = borderColor
            )
        )
    }
}

@Composable
fun DropdownFieldWithLabels(
    label: String,
    selectedOption: String,
    options: List<String>,
    isExpanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onSelectOption: (String) -> Unit,
    backgroundColor: Color,
    borderColor: Color,
    textColor: Color
) {
    Column {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = textColor
        )
        Box {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { onExpandChange(true) },
                        tint = textColor
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = backgroundColor,
                    focusedContainerColor = backgroundColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedIndicatorColor = borderColor,
                    unfocusedIndicatorColor = borderColor
                )
            )
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { onExpandChange(false) }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option, color = textColor) },
                        onClick = {
                            onSelectOption(option)
                            onExpandChange(false)
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditKandangPreview() {
    EditKandang(navController = rememberNavController())
}

