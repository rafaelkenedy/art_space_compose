package com.example.artspacecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacecompose.model.Frame
import com.example.artspacecompose.ui.theme.ArtSpaceComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier) {

    val numberOfFrames = 3
    var actualFrame by rememberSaveable { mutableStateOf(1) }

    fun nextStep() {
        if (actualFrame == numberOfFrames) {
            actualFrame = 1
        } else {
            actualFrame++
        }
    }

    fun previousStep() {
        if (actualFrame == 1)
            return
        else {
            actualFrame--
        }
    }

    val isPossibleBack = when (actualFrame) {
        1 -> false
        else -> true
    }

    val frame = getFrame(actualFrame)

    Column(
        modifier = modifier
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        ShowFrame(modifier = modifier, image = frame.image)



        Spacer(modifier.height(24.dp))

        InfoCard(
            modifier = modifier,
            stringResource(id = frame.title),
            stringResource(id = frame.author),
            stringResource(id = frame.year)
        )


        Spacer(modifier.height(24.dp))

        Navigation(modifier = modifier, { previousStep() }, { nextStep() }, isPossibleBack)
    }
}


@Composable
fun ShowFrame(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .padding(8.dp)
            .wrapContentWidth(),
        border = BorderStroke(4.dp, Color.Gray),
        elevation = 8.dp
    ) {
        Image(
            modifier = modifier
                .padding(36.dp),
            painter = painterResource(image),
            contentDescription = null
        )
    }


}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    previousStep: () -> Unit,
    nextStep: () -> Unit,
    isPossibleBack: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {

        Button(
            enabled = isPossibleBack,
            modifier = Modifier.weight(1f),
            onClick = { previousStep() }
        ) {
            Text("Previous")
        }

        Spacer(modifier.width(16.dp))

        Button(
            modifier = Modifier.weight(1f),
            onClick = { nextStep() }
        ) {
            Text("Next")
        }
    }
}

private fun getFrame(actualFrame: Int) = when (actualFrame) {
    1 -> Frame(
        R.drawable.monalisa,
        R.string.monalisa,
        R.string.leonardo_da_vinci,
        R.string.year_monalisa
    )
    2 -> Frame(
        R.drawable.thescream,
        R.string.thescream,
        R.string.edvard_munch,
        R.string.year_thescream
    )
    else -> Frame(
        R.drawable.salvatormundi,
        R.string.salvatormundi,
        R.string.leonardo_da_vinci,
        R.string.year_salvatormundi
    )
}

@Composable
fun InfoCard(modifier: Modifier = Modifier, title: String, author: String, year: String) {
    Surface(
        modifier = modifier
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = modifier
                .wrapContentWidth()
                .padding(24.dp)
        ) {

            Text(
                fontSize = 26.sp,
                fontWeight = FontWeight.Light,
                text = title
            )
            Row() {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = author
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    fontWeight = FontWeight.Light,
                    text = "($year)"
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceComposeTheme {
        Screen()
    }
}