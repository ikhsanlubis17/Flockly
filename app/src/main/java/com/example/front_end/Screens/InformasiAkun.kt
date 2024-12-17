package com.example.front_end.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.front_end.R
import com.example.front_end.ui.theme.Front_EndTheme

@Composable
fun InformasiAkun(
    backIconResource: Int,
    profileImageResource: Int,
    editIconResource: Int
) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFEBF0EC))) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(backIconResource),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(32.dp).clip(CircleShape).clickable { },
                    tint = Color(0xFF000000)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Informasi Akun",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Box(modifier = Modifier.fillMaxSize().padding(top = 200.dp), contentAlignment = Alignment.TopCenter) {
            UserInfoCard(editIconResource)
            ProfileImage(profileImageResource, modifier = Modifier.align(Alignment.TopCenter).offset(y = (-60).dp))
        }

        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color(0xFFD6C3A3))))
        )
    }
}

@Composable
fun ProfileImage(profileImageResource: Int, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(120.dp).clip(CircleShape).border(3.dp, Color(0xFF5F4A43), CircleShape)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = profileImageResource),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(120.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Edit Profil", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000))
    }
}

@Composable
fun UserInfoCard(editIconResource: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(0.90f).clip(RoundedCornerShape(40.dp))
            .background(Color(0xFFD6C3A3))
            .padding(top = 100.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(text = "Informasi Akun", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000))
        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "Email", value = "fransrathore18@gmail.com")
        InfoRow(label = "Kata Sandi", value = "******")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Informasi Pribadi", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000))
        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "Nama Lengkap", value = "Frans Rathore")
        InfoRow(label = "Tanggal Lahir", value = "18 Juni 1993")
        InfoRow(label = "Alamat", value = "Cipondoh, Tangerang")
        InfoRow(label = "RT/RW", value = "RT 010 RW 004")
        InfoRow(label = "Kelurahan", value = "Cipondoh")
        InfoRow(label = "Kecamatan", value = "Cipondoh")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .shadow(elevation = 15.dp, shape = RoundedCornerShape(10.dp), ambientColor = Color.Gray, spotColor = Color.Black),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F4A43))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = editIconResource), contentDescription = "Edit Icon", tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Edit", color = Color.White)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, fontSize = 14.sp, color = Color.Black)
        Text(text = value, fontSize = 14.sp, color = Color.Black, textAlign = TextAlign.End)
    }
}

@Preview(showBackground = true)
@Composable
fun InformasiAkunPreview() {
    Front_EndTheme {
        InformasiAkun(
            backIconResource = R.drawable.back1,
            profileImageResource = R.drawable.fotofrans,
            editIconResource = R.drawable.edit
        )
    }
}
