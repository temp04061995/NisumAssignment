package com.doy.nisumassignment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.doy.nisumassignment.model.Coordinates
import com.doy.nisumassignment.model.Dob
import com.doy.nisumassignment.model.Id
import com.doy.nisumassignment.model.Location
import com.doy.nisumassignment.model.Login
import com.doy.nisumassignment.model.Name
import com.doy.nisumassignment.model.Picture
import com.doy.nisumassignment.model.Registered
import com.doy.nisumassignment.model.Street
import com.doy.nisumassignment.model.Timezone
import com.doy.nisumassignment.model.User
import com.doy.nisumassignment.theme.CryptoTrackerTheme


@Composable
fun listItem(user: User, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    CryptoTrackerTheme {


        val nameTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black

            Box(
                modifier = modifier
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White)

            ) {

                Row(
                    modifier = modifier
                        .clickable(onClick = onClick)
                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Column(horizontalAlignment = Alignment.End) {
                        CircleImage(user.picture.thumbnail)
                    }

                    Column(modifier = modifier.weight(1f)) {
                        Text(
                            text = "${user.name.first} ${user.name.last}",
                            fontSize = 18.sp,
                            color = nameTextColor
                        )
                        Text(
                            text = "${user.location.city}, ${user.location.state} ",
                            fontSize = 14.sp,
                            color = nameTextColor
                        )
                    }


                }


            }

    }

}


@Composable
fun CircleImage(imageUrl: String = "", modifier: Modifier = Modifier) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(android.R.drawable.ic_menu_report_image)
            .build(),
        contentDescription = "User image",
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
    )

}


@Preview
@PreviewLightDark
@Composable
fun listItemPreview(modifier: Modifier = Modifier) {

    listItem(
        user = User(
            "Male", Name("", "Satya", "K"),
            Location(
                Street(1, ""), "Hyd", "Telangana", "", "",
                Coordinates("", ""), Timezone("", "")
            ),
            "",
            Login("", "", "", "", "", "", ""),
            Dob("", 22), Registered("", 23), "", "",
            Id("", ""), Picture("", "", "https://randomuser.me/api/portraits/thumb/men/48.jpg"), ""
        ),
        modifier = modifier
    )

}


