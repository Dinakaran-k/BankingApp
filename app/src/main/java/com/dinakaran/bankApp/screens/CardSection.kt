package com.dinakaran.bankApp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinakaran.bankApp.R
import com.dinakaran.bankApp.data.Card
import com.dinakaran.bankApp.ui.theme.BlueEnd
import com.dinakaran.bankApp.ui.theme.BlueStart
import com.dinakaran.bankApp.ui.theme.GreenEnd
import com.dinakaran.bankApp.ui.theme.GreenStart
import com.dinakaran.bankApp.ui.theme.OrangeEnd
import com.dinakaran.bankApp.ui.theme.OrangeStart
import com.dinakaran.bankApp.ui.theme.PurpleEnd
import com.dinakaran.bankApp.ui.theme.PurpleStart


val cards = listOf(
    Card(
        cardType = "VISA",
        cardNumber = "123456799",
        cardName = "Business",
        balance = 52.66,
        color = getGradient(PurpleStart, PurpleEnd)
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = "123456799",
        cardName = "savings",
        balance = 652.66,
        color = getGradient(BlueStart, BlueEnd)
    ),
    Card(
        cardType = "VISA",
        cardNumber = "123456799",
        cardName = "school",
        balance = 562.66,
        color = getGradient(OrangeStart, OrangeEnd)
    ),
    Card(
        cardType = "MASTER CARD",
        cardNumber = "123456799",
        cardName = "shopping",
        balance = 528.66,
        color = getGradient(GreenStart, GreenEnd)
    )

)

fun getGradient(
    startColor : Color,
    endColor : Color
) : Brush {
    return Brush.linearGradient(
        colors = listOf(
            startColor,
            endColor
        )
    )
}

@Preview
@Composable
fun CardSection() {
    LazyRow {
        items(cards.size){ index ->
            CardItem(index)
        }
    }
}

@Composable
fun CardItem(index: Int) {

    val card = cards[index]
    var lastItemPaddingEnd = 0.dp
    if (index == cards.size - 1 ){
        lastItemPaddingEnd = 16.dp
    }

    var image = painterResource(id = R.drawable.visa_img)
    if (card.cardType == "MASTER CARD") {
        image = painterResource(id = R.drawable.mastercard_img)
    }

    Box(modifier = Modifier
        .padding(start = 16.dp, end = lastItemPaddingEnd)
    ) {
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(250.dp)
                .height(160.dp)
                .clickable {  }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = image,
                contentDescription = card.cardNumber,
                modifier = Modifier.width(60.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = card.cardName,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${card.balance}",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )


        }
    }


}
