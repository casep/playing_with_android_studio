package com.example.caseppythonv2

import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.caseppythonv2.ui.theme.CasepPythonV2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CasepPythonV2Theme {
                initPython()
                val py = Python.getInstance()
                val pyObj = py.getModule("myScript")
                val message = pyObj.callAttr("getMessage", "Python!")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = message.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )


                }
            }
        }
    }

    private fun initPython() {
        try {
            if (!Python.isStarted())
                Python.start(AndroidPlatform(this))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CasepPythonV2Theme {
        val py = Python.getInstance()
        val pyObj = py.getModule("myScript")
        val message = pyObj.callAttr("getMessage", "Hello Python!")
        Greeting(message.toString())
    }
}