package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
              MyApp()
            }
        }
    }
}
@Composable
private fun MyApp() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false})
    }else {
        Greetings()
    }

}
@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics CodeLab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }

    }
}

@Composable
private fun Greetings(names: List<String> = List(100) {"$it"}) {
    Surface(color = MaterialTheme.colorScheme.background) {
       Column(modifier = Modifier.padding(vertical = 4.dp)) {
           LazyColumn {
               item { Text(text = "Header")}
               items(names) { name ->
                   Greeting(name)
               }
           }
       }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember {mutableStateOf(false)}
    val extraPadding by animateDpAsState(targetValue =  if (expanded) 48.dp else 0.dp,
    animationSpec = tween(delayMillis = 1000)
    )
    Surface(color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.padding(8.dp, 4.dp)){
      Row(modifier = Modifier.padding(24.dp)) {
          Column(modifier = Modifier
              .weight(1f)
              .padding(bottom = extraPadding)) {
              Text(text = "Hello")
              Text(text = name)
          }
          ElevatedButton(onClick = { expanded = !expanded }) {
              Text(if (expanded) "show less" else " show more")
          }
          
      }
        }

}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposeTutorialTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
      MyApp()
    }
}