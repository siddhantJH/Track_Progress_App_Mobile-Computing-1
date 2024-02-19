package com.example.assignment1mt23097

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1mt23097.ui.theme.Assignment1MT23097Theme
import com.example.assignment1mt23097.Location.Locations
import java.time.format.TextStyle

enum class Metric{
    KM,MILES
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1MT23097Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProgressDetails()
                }
            }
        }
    }
}
fun listOfLocation(): List<Locations> {
    val LocationList1 = listOf<Locations>(
        Locations("IIITD",0.00,11000.00),
        Locations("Govindpuri",1000.00,10000.00),
        Locations("KalkajiMandir",2000.00,8000.00),
        Locations("Nehru Place",3000.00,7000.00),
        Locations("Kailash Colony",4000.00,6000.00),
        Locations("Moolchand",5000.00,5000.00),
        Locations("Lajpat Nagar",6000.00,4000.00),
        Locations("Central Secretariat",7000.00,3000.00),
        Locations("Delhi Gate",8000.00,2000.00),
        Locations("Jama Masjid",9000.00,1000.00),
        Locations("Lal Qila",10000.00,0000.00),
    )
    val LocationList2 = listOf<Locations>(
        Locations("IIITD",0.00,4000.00),
        Locations("Govindpuri",1000.00,3000.00),
        Locations("KalkajiMandir",2000.00,2000.00),
        Locations("Nehru Place",3000.00,1000.00),
        Locations("Kailash Colony",4000.00,0.00)
    )
    return LocationList1
}
@Composable
fun LocationCard(nam:String,modifier:Modifier=Modifier)
{
Card( modifier = Modifier
    .padding(horizontal = 8.dp, vertical = 8.dp)
    .fillMaxWidth()
    .background(color = Color.White),
    shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
    TextField(
       nam, onValueChange = {}, modifier = Modifier
            .width(350.dp)
            .height(80.dp), enabled = false, shape = RoundedCornerShape(corner = CornerSize(16.dp)))
}
}


@Composable
fun ProgressDetails(modifier: Modifier = Modifier.fillMaxSize()) {
    var list = listOfLocation()
    var sliderPos by remember { mutableStateOf(0f) }
    var currentDes by remember { mutableStateOf("0") }
    var nextDest by remember { mutableStateOf("1") }
    var str1 by remember { mutableStateOf("") }
    var str2 by remember { mutableStateOf("") }
    var dist by remember { mutableStateOf(list[list.size-1].distFromSource) }
    var distCov by remember { mutableStateOf(0.0) }
    var distLeft by remember { mutableStateOf(list[list.size-1].distFromSource) }
    var currentMetric by remember { mutableStateOf(Metric.KM) }
    var con by remember { mutableStateOf(1000) }
    var incre by remember { mutableStateOf(0.1f) }
    str1=list[currentDes.toInt()].name
    if(nextDest.toInt()<11) {
        str2 = list[nextDest.toInt()].name
    }else{
        str2 = "No destination ahead"
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.10f)
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Slider(value = sliderPos, onValueChange = {}, steps = list.size - 2)
        }
        BasicTextField(value ="Progress : "+((distCov/1000)*10)+"%", onValueChange = { })
        Row(
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .background(color = Color.LightGray)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Current Destination", modifier = Modifier.padding(5.dp))
                TextField(
                    value = str1, onValueChange = {}, modifier = Modifier
                        .width(350.dp)
                        .height(90.dp), enabled = false
                )
                Row(
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(0.5f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Distance")
                        Text(text = "Total Distance Covered")
                        Text(text = "Total Distance Left")
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(0.5f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(
                            value = String.format("%.2f", (dist / con)),
                            onValueChange = { })
                        BasicTextField(
                            value = String.format("%.2f", (distCov / con)),
                            onValueChange = { })
                        BasicTextField(
                            value = String.format("%.2f", (distLeft / con)),
                            onValueChange = { })
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(0.5f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(value = currentMetric.toString(), onValueChange = { })
                        BasicTextField(value = currentMetric.toString(), onValueChange = { })
                        BasicTextField(value = currentMetric.toString(), onValueChange = { })
                    }
                }
                if(list.size<10) {
                    Text(
                        text = "Next Stop in terms of Normal List",
                        modifier = Modifier.padding(5.dp)
                    )
                }else{
                    Text(
                        text = "Next Stop in terms of Lazy List",
                        modifier = Modifier.padding(5.dp)
                    )
                }
                val lazyListState = rememberLazyListState()
                if (list.size >= 10) {
                    LazyColumn(state = lazyListState, modifier = Modifier.height(250.dp)) {
                        var si = list.size - 1

                        for (i in currentDes.toInt() + 1..si) {
                            item {
                                TextField(
                                    value = list[i].name+" | Tot Dist Cov: "+list[i].distFromSource/1000+"km | Tot Dist Left "+list[i].distFromDest/1000, onValueChange = {}, modifier = Modifier
                                        .width(350.dp)
                                        .height(80.dp), enabled = false
                                )
                            }
                        }

                        item {
                            TextField(
                                value = "No stops ahead", onValueChange = {},
                                modifier = Modifier
                                    .width(350.dp)
                                    .height(80.dp),
                                enabled = false,

                                )
                        }

//                   itemsIndexed(list)
//                   { index, item ->
//                       if (index <= currentDes.toInt()) {
//                           TextField(
//                               value = "" + list[index+1].name, onValueChange = {}, modifier = Modifier
//                                   .width(350.dp)
//                                   .height(80.dp), enabled = false
//                           )
//                       }else{
//                           TextField(
//                               value = "" + list[index+1].name, onValueChange = {}, modifier = Modifier
//                                   .width(350.dp)
//                                   .height(80.dp), enabled = false
//                           )
//                       }
//                   }
                    }
                } else {
                    LazyColumn(state = lazyListState, modifier = Modifier
                        .height(250.dp)
                        .background(
                            color = Color.LightGray
                        )) {
                        var si = list.size - 1

                        for (i in currentDes.toInt() + 1..si) {
                            item() {
                                TextField(
                                    value = list[i].name+" |Dist Cov:"+list[i].distFromSource/1000+"km |TDL "+list[i].distFromDest/1000, onValueChange = {}, modifier = Modifier
                                        .width(350.dp)
                                        .height(50.dp), enabled = false, shape = RoundedCornerShape(corner = CornerSize(16.dp)))
                            }
                        }
                        item {
                            TextField(
                                value = "No stops ahead", onValueChange = {},
                                modifier = Modifier
                                    .width(350.dp)
                                    .height(55.dp), enabled = false, shape = RoundedCornerShape(corner = CornerSize(16.dp))

                                )
                        }

//                   itemsIndexed(list)
//                   { index, item ->
//                       if (index <= currentDes.toInt()) {
//                           TextField(
//                               value = "" + list[index+1].name, onValueChange = {}, modifier = Modifier
//                                   .width(350.dp)
//                                   .height(80.dp), enabled = false
//                           )
//                       }else{
//                           TextField(
//                               value = "" + list[index+1].name, onValueChange = {}, modifier = Modifier
//                                   .width(350.dp)
//                                   .height(80.dp), enabled = false
//                           )
//                       }
//                   }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxHeight(1f)
                .background(color = Color.DarkGray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.padding(start = 10.dp,))
            ElevatedButton(modifier = Modifier.height(60.dp), onClick = { if(currentMetric==Metric.KM){
                currentMetric=Metric.MILES
                con=1609
            }else{
                currentMetric=Metric.KM
                con=1000
            }
            }) {
                Text(text = stringResource(id = R.string.ToggleMetric))
            }
            Button(onClick =
                {
                    var c = currentDes.toInt()
                    c=0
                    var n = nextDest.toInt()
                    n=1
                    currentDes=""+c
                    nextDest=""+n
                    distCov=0.0
                    distLeft = list[list.size-1].distFromSource
                    if(list.size<10)
                    {
                        incre=0.25f
                    }else{
                        incre=0.1f
                    }
                    sliderPos = 0f
                },modifier= Modifier
                .padding(10.dp)
                .width(40.dp)
                .height(40.dp)) {
                Text(text = "R")
            }
            ElevatedButton(
                modifier = Modifier.height(60.dp),
                onClick =
                { sliderPos = sliderPos + incre
                        var c = currentDes.toInt()
                    if(c!=list.size-1) {
                        c++
                    }
                        var n = nextDest.toInt()
                    if(c!=list.size-1) {
                        n++
                    }
                        currentDes=""+c
                        nextDest=""+n
                        distCov=list[c].distFromSource
                        distLeft=(dist-distCov)
                }) {
                Text(text = stringResource(id = R.string.DestinationReached))
            }
            Spacer(modifier = Modifier.padding(end = 11.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment1MT23097Theme {
        ProgressDetails()
    }
}