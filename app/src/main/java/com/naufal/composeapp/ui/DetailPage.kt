package com.naufal.composeapp.ui

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.naufal.composeapp.model.Vacation
import com.naufal.composeapp.model.VacationData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(vacation: Vacation?) {
    val listState = rememberLazyListState()
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            ),
            title = { Text(text = "${vacation?.name}", style = TextStyle(fontSize = 24.sp)) },
            navigationIcon = {
                IconButton(
                    onClick = { onBackPressedDispatcher?.onBackPressed() }) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                }
            })
    }) { innerPadding ->
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                AsyncImage(
                    model = vacation?.photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(250.dp)
                        .clip(CircleShape)
                )
                Text(
                    style = TextStyle(fontSize = 24.sp),
                    text = vacation?.name.toString(),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 8.dp)
                )
                Text(
                    style = TextStyle(),
                    text = vacation?.description.toString(),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun DetailPagePreview() {
    val dataItem = VacationData.vacations.find { it.id == "1" }
    DetailPage(dataItem)
}