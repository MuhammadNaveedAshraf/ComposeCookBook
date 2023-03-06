package com.guru.composecookbook.spotify.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.data.AlbumsDataProvider
import com.guru.composecookbook.data.model.Album
import com.guru.composecookbook.spotify.R
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography

@Composable
fun SpotifyHomeGridItem(album: Album,index: Int, onValueChange:(Boolean)->Int) {
    val cardColor = if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    var isSelected by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        backgroundColor = cardColor,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(top = 4.dp)
            .width(200.dp)
            .clickable {
                isSelected = !isSelected
                isSelected = index==onValueChange(isSelected)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if(isSelected){
                Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "Playing" )

            }
            else {
                Text(
                    text = index.toString(),
                    modifier = Modifier.padding(8.dp),
                    style = typography.h6.copy(fontSize = 12.sp)
                )
            }
            Image(
                painter = painterResource(id = album.imageId),
                contentDescription = null,
                modifier = Modifier.size(55.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = album.song,
                style = typography.h6.copy(fontSize = 14.sp),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSpotifyHomeGridItem() {
    val album = remember { AlbumsDataProvider.album }
    SpotifyHomeGridItem(album,0, { return@SpotifyHomeGridItem 0 })
}
@Composable
fun GridItemList(){
    LazyColumn(modifier = Modifier.padding(bottom = 5.dp)){

    }
}