package com.example.walletapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walletapp.ui.theme.gray
import com.example.walletapp.ui.theme.green
import com.example.walletapp.util.formatToPrice
import java.util.*

@Composable
fun TransactionItem(
    color: Color,
    description: String,
    value: Float,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                description,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Text(
                date,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        }
        Text(
            value.formatToPrice(Locale.getDefault(),true),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionItemPreview() {
    TransactionItem(
        color = green,
        description = "Deposit",
        value = 21.89f,
        date = "12.01.2023 11:15",
        modifier = Modifier
            .fillMaxWidth()
            .background(gray.copy(0.2f), RoundedCornerShape(25.dp))
            .padding(12.dp)
    )
}