package com.example.contactapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactLisScreen(navController: NavHostController) {
    val viewModel: ContactListViewModel = viewModel(
        modelClass = ContactListViewModel::class.java
    )
    val contactListState = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Button(onClick = {navController.navigate(NavRoute.SEARCH_SCREEN.route)},
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.LightGray,
                                    contentColor = Color.Black,
                                )) { Text("Tìm kiếm",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 0.dp),
                                textAlign = TextAlign.Start) } },
                            leadingIcon = {
                                IconButton(onClick = {
                                    navController.navigate(NavRoute.SEARCH_SCREEN.route)
                                }) {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = "Tìm kiếm",
                                    )
                                }
                            },
                            trailingIcon = {
                                Row {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Rounded.QrCode2,
                                            contentDescription = null
                                        )
                                    }
                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Rounded.Settings,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedLabelColor = Color.Transparent,
                                containerColor = Color.LightGray
                            ),

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    navController.navigate(NavRoute.SEARCH_SCREEN.route)
                                }
                        )

                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = {
                    navController.navigate(NavRoute.DETAIL_SCREEN.route)
                },
                    colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Thêm Danh Bạ",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(contactListState.contacts) { contact ->
                CardInfo(
                    name = contact.FullName,
                    phone = contact.Phone,
                    onClickCard = {
                        navController.navigate(NavRoute.DETAIL_SCREEN.route + "?id=${contact.Id}")
                    },
                    onDelete = {
                        viewModel.deleteContact(contact)
                    }
                )
            }
            item {
                CardInfo(name = "Nguyên Lê", phone = "0912123456", onClickCard = {}, onDelete = {})
            }
            item {
                CardInfo(name = "Tiến Lữ", phone = "0932987456", onClickCard = {}, onDelete = {})
            }
        }
    }
}
