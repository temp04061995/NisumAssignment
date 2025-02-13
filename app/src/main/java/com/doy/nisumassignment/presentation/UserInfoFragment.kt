package com.doy.nisumassignment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.doy.nisumassignment.R
import com.doy.nisumassignment.model.User
import com.doy.nisumassignment.view_model.SharedViewModel

class UserInfoFragment : Fragment() {

    lateinit var viewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                val user by viewModel.selectedUser.observeAsState()
                UserInfoScreen(user = user!!)

            }
        }
    }


    @Composable
    fun UserInfoScreen(user: User) {

        val customFontBold = FontFamily(
            Font(
                R.font.space_mono_bold,
                weight = FontWeight.Bold
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),

            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(35.dp))

            CircleImage(
                user.picture.large,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "${user.name.first} ${user.name.last}",
                fontFamily = customFontBold,
                fontSize = 20.sp
            )

            TabLayoutWithViewPager(user)


        }


    }


}