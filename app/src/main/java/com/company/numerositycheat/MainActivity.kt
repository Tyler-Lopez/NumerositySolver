package com.company.numerositycheat

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.company.numerositycheat.ui.theme.NumerosityCheatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumerosityCheatTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    val operand = remember { mutableStateOf(value = Operand.Addition) }
                    val target = remember { mutableStateOf(value = 28) }
                    val options = remember {
                        mutableListOf(
                            10, 17, 18, 15, 14
                        )
                    }
                    val result = calculate(options, target.value, operand.value)

                    // Various mutable variables

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(67, 159, 230),
                                        Color(43, 124, 186),
                                        Color(29, 115, 181),
                                        Color(16, 82, 133),
                                    )
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                                    .fillMaxSize()
                                    .background(Color.Transparent)
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        for (i in 0..1) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(0.5f)
                                            ) {
                                                for (j in 0..1) {
                                                    Button(
                                                        shape = RoundedCornerShape(5.dp),
                                                        modifier = Modifier
                                                            .padding(2.dp)
                                                            .weight(0.5f)
                                                            .fillMaxSize()
                                                            .shadow(5.dp),
                                                        border = BorderStroke(
                                                            5.dp,
                                                            Color(0.2f, 0.2f, 0.2f, 0.3f)
                                                        ),
                                                        onClick = {

                                                        },
                                                        contentPadding = PaddingValues(),

                                                        ) {
                                                        Box(
                                                            modifier = Modifier.fillMaxSize(),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            val text = when (i + j) {
                                                                0 -> "+"
                                                                1 -> "-"
                                                                2 -> "÷"
                                                                else -> "×"
                                                            }
                                                            Text(
                                                                modifier = Modifier.offset(y = -3.dp),
                                                                text = text,
                                                                fontWeight = FontWeight.Black,
                                                                fontSize = 60.sp,
                                                                color = Color.White,
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            Button(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .padding(10.dp)
                                    .fillMaxSize(),
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                    ) {
                                            Text(
                                                text = "TARGET",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 20.sp,
                                            )
                                            Text(
                                                text = "0",
                                                fontWeight = FontWeight.Black,
                                                fontSize = 50.sp,
                                            )

                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp)
                                .fillMaxSize(),
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {

                                Button(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(),
                                    onClick = {

                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(
                                                    listOf(
                                                        Color(85, 32, 153),
                                                        Color(114, 61, 184)
                                                    )
                                                )
                                            )
                                            .border(
                                                5.dp,
                                                Color.White
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(10.dp),
                                            verticalArrangement = Arrangement.Top,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                text = "OPTIONS",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 30.sp,
                                                color = Color.White
                                            )
                                        }
                                        Text(
                                            text = "0",
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Black,
                                            color = Color.White,
                                            fontSize = 60.sp,
                                        )
                                    }

                                }
                            }

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color(30, 165, 186),
                                            Color(38, 208, 235)
                                        )
                                    )
                                )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Column(
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.padding(
                                        horizontal = 20.dp,
                                        vertical = 10.dp
                                    )
                                ) {
                                    Text(
                                        text = "RESULT",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 20.sp,
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = result,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 50.sp,
                                    )
                                }
                            }


                        }

                    }
                }
            }
        }
    }
}

@Composable
fun chooseOperand(chosenOperand: (Operand) -> Operand) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Select Operand")
        Row() {
            Button(onClick = {
                chosenOperand(Operand.Addition)
            }) {
                Text(text = "+")
            }
            Button(onClick = {
                chosenOperand(Operand.Subtraction)
            }) {
                Text(text = "-")
            }
            Button(onClick = {
                chosenOperand(Operand.Division)
            }) {
                Text(text = "÷")
            }
            Button(onClick = {
                chosenOperand(Operand.Multiplication)
            }) {
                Text(text = "×")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumerosityCheatTheme {
    }
}