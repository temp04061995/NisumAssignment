package com.doy.nisumassignment.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doy.nisumassignment.R
import com.doy.nisumassignment.model.User
import kotlinx.coroutines.launch

@Composable
fun TabLayoutWithViewPager(user: User) {
    val tabs = listOf("Profile", "Contact")
    val pagerState = remember {
        PagerState { tabs.size }
    }
    val coroutineScope = rememberCoroutineScope()


    Spacer(modifier = Modifier.height(16.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(240.dp).align(Alignment.CenterHorizontally),
            containerColor = Color.Transparent



            ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = {
                        Text(
                            title,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .weight(1f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> ProfileScreen(user)
                1 -> ContactScreen(user)
            }
        }
    }
}

@Composable
fun ContactScreen(user: User) {
    Box(Modifier.fillMaxSize()) {

        val contentColor = if (isSystemInDarkTheme()) {
            Color.White
        } else {
            Color.Black
        }

        val customFontBold = FontFamily(
            Font(
                R.font.space_mono_bold,
                weight = FontWeight.Bold
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {


            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Email : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = user.email)

            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Phone : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = user.phone)


            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Cell  : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = user.cell)

            }


        }


    }
}

@Composable
fun ProfileScreen(user: User) {
    Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {


        val contentColor = if (isSystemInDarkTheme()) {
            Color.White
        } else {
            Color.Black
        }

        val customFontBold = FontFamily(
            Font(
                R.font.space_mono_bold,
                weight = FontWeight.Bold
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {


            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Address  : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = "${user.location.city}, ${user.location.state}")

            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Username : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = user.login.username)


            }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Age      : ",
                    fontFamily = customFontBold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )

                Text(text = "${user.dob.age}")

            }


        }


    }
}

