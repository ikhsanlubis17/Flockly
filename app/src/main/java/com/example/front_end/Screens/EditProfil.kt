package com.example.front_end.Screens

import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@Composable
fun EditProfile() {
    var profileImage by remember { mutableIntStateOf(R.drawable.fotofrans) }
    val fullName by remember { mutableStateOf("Frans Rathore") }
    val email by remember { mutableStateOf("fransrathore18@gmail.com") }
    val password by remember { mutableStateOf("********") }
    val birthDate by remember { mutableStateOf("18 Juni 1993") }
    val address by remember { mutableStateOf("Cipondoh, Tangerang RT 010 RW 004") }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEBF0EC))
    ) {
        LayerBlur(
            color = Color(0xFFD6C3A3),
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-100).dp, y = 20.dp)
        )

        LayerBlur(
            color = Color(0xFFD6C3A3),
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .offset(x = (100).dp, y = 150.dp)
        )

        LayerBlur(
            color = Color(0xFFD6C3A3),
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (100).dp, y = 150.dp)
        )

        LayerBlur(
            color = Color(0xFFD6C3A3),
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-120).dp, y = 90.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(title = "Edit Profil", onBackClick = { /* Action */ })
            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ProfileImage(
                    profileImageRes = profileImage,
                    cameraIconRes = R.drawable.kamera,
                    onCameraClick = {
                        profileImage = R.drawable.fotofrans
                    }
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            InputField(
                icon = R.drawable.namalengkap,
                label = "Nama Lengkap",
                value = fullName,
                onClick = { /* Action to edit name */ }
            )
            InputField(
                icon = R.drawable.gmail,
                label = "Email",
                value = email,
                onClick = { /* Action to edit email */ }
            )
            InputField(
                icon = R.drawable.sandi,
                label = "Kata Sandi",
                value = password,
                onClick = { /* Action to edit password */ }
            )
            InputField(
                icon = R.drawable.ultah,
                label = "Tanggal Lahir",
                value = birthDate,
                onClick = { /* Action to edit birthdate */ }
            )
            InputField(
                icon = R.drawable.lokasi,
                label = "Alamat",
                value = address,
                onClick = { /* Action to edit address */ }
            )

            Spacer(modifier = Modifier.height(16.dp))
            UpdateButton(onClick = {
                Toast.makeText(context, "Profil diperbarui", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Composable
fun TopAppBar(title: String, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 1.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back1),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        // Call the onBackClick inside the composable context
                        onBackClick()
                    }
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun ProfileImage(profileImageRes: Int, cameraIconRes: Int, onCameraClick: () -> Unit) {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = profileImageRes),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Box(
            modifier = Modifier
                .size(35.dp)
                .offset(y = 15.dp)
                .clip(CircleShape)
                .background(Color(0xFFCED9D5))
                .align(Alignment.BottomCenter)
                .clickable { onCameraClick() }
        ) {
            Icon(
                painter = painterResource(id = cameraIconRes),
                contentDescription = "Edit Foto",
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun InputField(
    icon: Int,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = Color(0xFFA0937D),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = value,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun UpdateButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 120.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF5F4A43))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.simpan),
                contentDescription = "Update Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Perbarui",
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun LayerBlur(color: Color, modifier: Modifier) {
    Canvas(
        modifier = modifier
    ) {
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                this.color = color.toArgb()
                isAntiAlias = true
                maskFilter = BlurMaskFilter(120f, BlurMaskFilter.Blur.NORMAL)
            }
            canvas.nativeCanvas.drawCircle(
                size.width / 2,
                size.height / 2,
                size.minDimension / 2,
                paint
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    Front_EndTheme {
        EditProfile()
    }
}
