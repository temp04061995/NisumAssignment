package com.doy.nisumassignment.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doy.nisumassignment.R
import com.doy.nisumassignment.model.User
import com.doy.nisumassignment.view_model.SharedViewModel


@Composable
fun UsersListScreen(
    users: List<User> = emptyList(),
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SharedViewModel
) {


    if (users.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {


        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            items(users) { userItem ->
                listItem(user = userItem, modifier = modifier, onClick = {

                    viewModel.updateSelectedUser(userItem)
                    navController.navigate(
                        R.id.action_usersListFragment_to_userInfoFragment
                    )

                })
            }

        }
    }

}

