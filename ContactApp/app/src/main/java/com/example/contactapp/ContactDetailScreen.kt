package com.example.contactapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(navController: NavHostController, id: Int = -1) {
    val viewModel = viewModel<ContactDetailViewModel>(
        factory = ContactDetailViewModelFactor(id)
    )
    val contactState = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    Text(
                        text = if (id < 0) {
                            "Thêm mới liên hệ"
                        } else {
                            "Cập nhật liên hệ"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = contactState.fullname,
                onValueChange = viewModel::onChangeFullName,
                label = { Text(text = "Họ và Tên") }
            )
            OutlinedTextField(
                value = contactState.phone,
                onValueChange = viewModel::onChangeFullPhone,
                label = { Text(text = "Số điện thoại") }
            )
            OutlinedTextField(
                value = contactState.email,
                onValueChange = viewModel::onChangeFullEmail,
                label = { Text(text = "Email") }
            )
            Button(
                onClick = {
                    if (id > 0) {
                        val contact = Contact(
                            id, contactState.phone,
                            contactState.fullname,
                            contactState.email
                        )
                        viewModel.updateContact(contact)
                    } else {
                        val contact = Contact(
                            Phone = contactState.phone,
                            FullName = contactState.fullname,
                            Email = contactState.email
                        )
                        viewModel.insertContact(contact)
                    }
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(10)
            ) {
                Text(text = "Lưu")
            }
        }
    }
}
