package com.guru.composecookbook.spotify.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.guru.composecookbook.data.AlbumsDataProvider.albums
import com.guru.composecookbook.data.AlbumsDataProvider.shows
import com.guru.composecookbook.data.EPG
import com.guru.composecookbook.data.model.Album

@Composable
fun MyGrid(albums:MutableList<Album>){
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ){
        itemsIndexed(albums) {index,item->

            CompleteRow(album = item,index) {isSelected->
                if(isSelected){
                    selectedIndex = index
                    return@CompleteRow index
                }else{
                    return@CompleteRow -1
                }
            }
        }

     }
}

@Composable
fun CompleteRow(album: Album, index:Int, onValueChange:(Boolean)->Int){
    Row(modifier = Modifier) {
        SpotifyHomeGridItem(album = album, index, onValueChange)
        LazyRow(
            modifier = Modifier
                .padding(top = 4.dp)
        ){
            items(
                items = shows,
                itemContent = {EPGItem(show = it)}
            )
        }
    }
}

@Composable
fun EPGItem(show: EPG){
    Box(
        modifier = Modifier
            .height(55.dp)
            .width(show.width!!)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = show.show!!,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()

        )
    }
}
@Preview
@Composable
fun EPGItemPreview(){
    EPGItem(show = shows[0])
}

@Preview(widthDp = 1080)
@Composable
fun MygridPreview(){
    MyGrid(albums = albums)
}
