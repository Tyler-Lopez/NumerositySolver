package com.company.numerositycheat

import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintSet
import com.company.numerositycheat.ui.theme.NumerosityCheatTheme
import java.lang.NumberFormatException
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumerosityCheatTheme {
                // A surface container using the 'background' color from the theme
                Surface {

                    val showTarget = remember { mutableStateOf(value = false) }
                    val showOptions = remember { mutableStateOf(value = false) }
                    val operand = remember { mutableStateOf(value = Operand.Addition) }
                    val target = remember { mutableStateOf(value = 56) }
                    val options = remember {
                        mutableListOf(
                            10, 17, 14, 8, 5, 2, 1
                        )
                    }
                    val configuration = LocalConfiguration.current
                    var result = calculate(options, target.value, operand.value)

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
                                        var count = 0
                                        for (i in 0..1) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(0.5f)
                                            ) {
                                                for (j in 0..1) {
                                                    val currentOperand =
                                                        when (count) {
                                                            0 -> Operand.Addition
                                                            1 -> Operand.Subtraction
                                                            2 -> Operand.Division
                                                            else -> Operand.Multiplication
                                                        }
                                                    val activeOperand =
                                                        when {
                                                            operand.value == Operand.Addition && count == 0 -> true
                                                            operand.value == Operand.Subtraction && count == 1 -> true
                                                            operand.value == Operand.Division && count == 2 -> true
                                                            operand.value == Operand.Multiplication && count == 3 -> true
                                                            else -> false
                                                        }
                                                    val backgroundColor =
                                                        if (activeOperand)
                                                            ButtonDefaults.buttonColors(
                                                                backgroundColor = Color(43, 120, 64)
                                                            )
                                                        else ButtonDefaults.buttonColors()
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
                                                            operand.value = currentOperand

                                                        },
                                                        colors = backgroundColor,
                                                        contentPadding = PaddingValues(),

                                                        ) {
                                                        Box(
                                                            modifier = Modifier.fillMaxSize(),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            val text = when (count) {
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
                                                            count++
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
                                    showTarget.value = true
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
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
                                            fontSize = 30.sp,
                                        )
                                        Text(
                                            text = target.value.toString(),
                                            fontSize = 50.sp,
                                            fontWeight = FontWeight.Black,
                                            textAlign = TextAlign.Center
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
                            Row(modifier = Modifier.fillMaxSize()) {
                                Button(
                                    modifier = Modifier.weight(0.5f),
                                    contentPadding = PaddingValues(),
                                    onClick = {
                                        showOptions.value = true

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
                                        when (configuration.orientation) {
                                            Configuration.ORIENTATION_LANDSCAPE -> {
                                                Row(
                                                    horizontalArrangement = Arrangement.Center,
                                                    modifier = Modifier
                                                        .horizontalScroll(
                                                            rememberScrollState()
                                                        )
                                                ) {
                                                    for (element in options) {
                                                        Text(
                                                            text = element.toString(),
                                                            textAlign = TextAlign.Center,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.White,
                                                            fontSize = 40.sp,
                                                            modifier = Modifier.padding(horizontal = 5.dp)
                                                        )
                                                    }
                                                }
                                            }
                                            else -> Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,
                                                modifier = Modifier
                                                    .verticalScroll(
                                                        rememberScrollState()
                                                    )
                                            ) {
                                                for (element in options) {
                                                    Text(
                                                        text = element.toString(),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White,
                                                        fontSize = 40.sp,
                                                        modifier = Modifier.padding(vertical = 5.dp)
                                                    )
                                                }
                                            }
                                        }

                                    }
                                }


                                Card(
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(start = 10.dp),
                                    elevation = 10.dp
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.White),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(10.dp),
                                            verticalArrangement = Arrangement.Top,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            if (result.size < 5) {
                                                Text(
                                                    text = "ANSWER",
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 30.sp,
                                                    color = Color.DarkGray
                                                )
                                            }
                                        }

                                        when (configuration.orientation) {
                                            Configuration.ORIENTATION_LANDSCAPE -> {

                                                Row(
                                                    horizontalArrangement = Arrangement.Center,
                                                    modifier = Modifier
                                                        .horizontalScroll(
                                                            rememberScrollState()
                                                        )
                                                ) {
                                                    for (element in result) {
                                                        Text(
                                                            text = element.toString(),
                                                            textAlign = TextAlign.Center,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.DarkGray,
                                                            fontSize = 50.sp,
                                                            modifier = Modifier.padding(horizontal = 5.dp)
                                                        )
                                                    }
                                                    if (result.isEmpty()) {
                                                        Text(
                                                            text = "N / A",
                                                            textAlign = TextAlign.Center,
                                                            fontWeight = FontWeight.Black,
                                                            color = Color.DarkGray,
                                                            fontSize = 50.sp,
                                                            modifier = Modifier.padding(
                                                                horizontal = 10.dp
                                                            )
                                                        )
                                                    }
                                                }
                                            }
                                            else -> Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center,
                                                modifier = Modifier
                                                    .verticalScroll(
                                                        rememberScrollState()
                                                    )
                                            ) {
                                                for (element in result) {
                                                    Text(
                                                        text = element.toString(),
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.DarkGray,
                                                        fontSize = 50.sp,
                                                        modifier = Modifier.padding(vertical = 5.dp)
                                                    )
                                                }
                                                if (result.isEmpty()) {
                                                    Text(
                                                        text = "N / A",
                                                        textAlign = TextAlign.Center,
                                                        fontWeight = FontWeight.Black,
                                                        color = Color.DarkGray,
                                                        fontSize = 50.sp,
                                                        modifier = Modifier.padding(
                                                            horizontal = 10.dp
                                                        )
                                                    )
                                                }
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }
                    if (showTarget.value) {
                        chooseNumber(chosenOperand = {
                            target.value = it
                            showTarget.value = false
                        })
                    }
                    if (showOptions.value) {
                        chooseOptions(chosenNumbers = {
                            options.clear()
                            for (element in it) options.add(element)
                            showOptions.value = false
                        })
                    }
                }
            }
        }
    }
}


@Composable
fun chooseNumber(chosenOperand: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        for (i in 0..100 step 3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0..2) {
                    Button(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(3.dp)
                            .fillMaxHeight(),
                        onClick = {
                            chosenOperand(i + j)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${i + j}",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun chooseOptions(chosenNumbers: (List<Int>) -> Unit) {
    val returnList = remember { mutableListOf<Int>() }
    var text = remember { mutableStateOf("") }

    val changeTest = remember { mutableStateOf(value = false) }
    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Row(modifier = Modifier.fillMaxWidth().height(100.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Card(modifier = Modifier
                .weight(0.5f).fillMaxHeight().align(Alignment.CenterVertically)
                ) {
                Text(modifier = Modifier.fillMaxSize(), text = text.value, textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
            Button(modifier = Modifier
                .weight(0.5f).fillMaxHeight()
                , onClick = {
                chosenNumbers(returnList)
            }) {
                Text(modifier = Modifier.fillMaxSize(), text = "Submit", textAlign = TextAlign.Center, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {


            for (i in 0..100 step 3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (j in 0..2) {
                        Button(
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(3.dp)
                                .fillMaxHeight(),
                            onClick = {
                                returnList.add(i + j)
                                text.value += ("${i + j} ")
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${i + j}",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    chooseNumber(chosenOperand = {
    })
}