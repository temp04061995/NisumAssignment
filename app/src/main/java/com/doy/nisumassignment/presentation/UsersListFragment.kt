package com.doy.nisumassignment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.doy.nisumassignment.view_model.SharedViewModel

class UsersListFragment : Fragment() {


    lateinit var viewModel: SharedViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        navController = findNavController()
        return ComposeView(requireContext()).apply {
            setContent {
                UserScreen()
            }

        }
    }

    @Composable
    @Preview
    fun UserScreenPreview() {
        UserScreen()
    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { char -> char.isDigit() }
    }

    @Composable
    fun UserScreen() {

        val errorMsg by viewModel.responseFailed.observeAsState("")

        if (errorMsg.isNotEmpty())
            Toast.makeText(requireContext(), "$errorMsg", Toast.LENGTH_SHORT).show()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)

                .background(MaterialTheme.colorScheme.background)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                var countEntered by remember { mutableStateOf(0) }

                TextField(
                    value = if (countEntered == 0) "" else "$countEntered",
                    onValueChange = { updatedText ->

                        countEntered = if (updatedText.isNotEmpty() && isNumeric(updatedText)) {
                            updatedText.toInt()
                        } else {
                            0
                        }

                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


                Button(
                    onClick = {
                        viewModel.fetchUsers(countEntered) },
                    modifier = Modifier
                        .width(100.dp)
                        .height(62.dp)
                ) {
                    Text(text = "Get")
                }

            }

            Spacer(modifier = Modifier.height(5.dp))
            val users by viewModel.usersList.observeAsState(emptyList())

            if (users != null)
                UsersListScreen(users = users, Modifier, navController, viewModel)

        }


    }


}