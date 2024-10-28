package cl.bootcamp.ind1m6android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.ind1m6android.model.Contact
import cl.bootcamp.ind1m6android.viewModel.ContactViewModel

@Composable
fun CreateContactScreen(navController: NavHostController, contactViewModel: ContactViewModel) {

    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var telefono by remember { mutableStateOf(TextFieldValue()) }
    var correo by remember { mutableStateOf(TextFieldValue()) }
    var imagenPerfil by remember { mutableStateOf(TextFieldValue()) }
    var fechaNacimiento by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Crear nuevo contacto", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = imagenPerfil,
            onValueChange = { imagenPerfil = it },
            label = { Text("URL de Imagen de Perfil") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = fechaNacimiento,
            onValueChange = { fechaNacimiento = it },
            label = { Text("Fecha de Nacimiento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                contactViewModel.addContact(
                    Contact(
                        nombre = nombre.text,
                        telefono = telefono.text,
                        correo = correo.text,
                        imagenPerfil = imagenPerfil.text,
                        fechaNacimiento = fechaNacimiento.text)
                )
                navController.navigateUp()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar Contacto")
        }
    }
}