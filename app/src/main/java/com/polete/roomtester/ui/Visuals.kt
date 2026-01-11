package com.polete.roomtester.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polete.roomtester.data.model.Task

/**
 * Tarjeta en la cual se muestra el título de la 'task'
 * Cada una de las tarjetas es una 'task'
 */
@Composable
fun TaskLabel(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier.fillMaxWidth().height(100.dp).padding(10.dp).clickable(enabled = true, onClick = onClick)
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.labelLarge
            )
        }

    }

}

/**
 * Vista por defecto de cada pantalla, es sobre la cuál se fabrican las
 * pantallas, contiene un título de pantalla
 * @content -> Tiene la capacidad de meter como parámetro
 * de entrada 'composables'
 */
@Composable
fun AppViewDefault(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable (Modifier) -> Unit = {}
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopBar(title = title) }
    ) { paddingValues ->

        content(Modifier
            .fillMaxSize()
            .padding(paddingValues))

    }

}


/**
 * Botón de añadir 'task', la función se introduce como parámetro
 */
@Composable
fun AddButton(
    onClick: () -> Unit
) {

    Box(Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

/**
 * Formulario donde puede rellenarse la información
 * necesaria para crear una 'task'
 */
@Composable
fun FormTask(
    modifier: Modifier = Modifier,
    onAdd: (Task) -> Unit = {}
) {

    var field1: String by remember { mutableStateOf("") }
    var field2: String by remember { mutableStateOf("") }



    //TextFields for insert data for tasks

    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,

    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field1,
            onValueChange = { newText ->
                field1 = newText
            },
            label = { Text("Insert a Title...") }
        )



        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field2,
            onValueChange = { newText ->
                field2 = newText
            },
            label = { Text("Insert a Body...") }
        )

    }

    // Solo cuando los fields no sean null se mostrará el botón de 'AddButton'
    if (!field1.isEmpty() && !field2.isEmpty()) {
        AddButton(
            onClick = {
                onAdd(Task(title = field1, body = field2))
            }
        )
    }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier, title: String) {
    Box(
        modifier
            .height(100.dp)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer

                )
        }

    }
}

@Preview
@Composable
fun ScaffPreview() {
    AppViewDefault(title = "Este es un título de ejemplo")
}

@Preview(showSystemUi = true)
@Composable
private fun FormTaskPreview() {
    FormTask()
}

@Preview(showSystemUi = true)
@Composable
private fun AddButtonPreview() {
    AddButton(
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun TaskLabevPreview() {
    TaskLabel(task = Task(
        1,
        "Título tontorrón",
        "Lore ipsum de la gorda de su madre y tal y pascual"
    ),
        onClick = {}
        )
}