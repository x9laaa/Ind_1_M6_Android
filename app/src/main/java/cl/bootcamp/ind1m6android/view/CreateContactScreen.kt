package cl.bootcamp.ind1m6android.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.bootcamp.ind1m6android.components.AppBarView
import cl.bootcamp.ind1m6android.components.ContactTextField
import cl.bootcamp.ind1m6android.model.Contact
import cl.bootcamp.ind1m6android.viewModel.ContactViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateContactScreen(
    id: Long,
    navController: NavHostController,
    contactViewModel: ContactViewModel
) {
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Cargar datos de contacto si el ID no es 0
    if (id != 0L) {
        val contact = contactViewModel.getAnContactById(id)
            .collectAsState(initial = Contact(0L, "", "", "", "", ""))
        contactViewModel.contactNameState = contact.value.nombre
        contactViewModel.contactPhoneState = contact.value.telefono
        contactViewModel.contactMailState = contact.value.correo
        contactViewModel.contactImageState = contact.value.imagenPerfil
        contactViewModel.contactBirthdateState = contact.value.fechaNacimiento
    } else {
        // Limpiar campos si se crea un nuevo contacto
        contactViewModel.contactNameState = ""
        contactViewModel.contactPhoneState = ""
        contactViewModel.contactMailState = ""
        contactViewModel.contactImageState = ""
        contactViewModel.contactBirthdateState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (id == 0L) "Crear Contacto" else "Actualizar Contacto"
            ) { navController.navigateUp() }
        },
        content = { contentPadding ->  // Usar el parámetro contentPadding aquí
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)  // Aplicar contentPadding aquí
                    .padding(16.dp),  // Padding adicional para el contenido
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de nombre
                ContactTextField(
                    label = "Nombre",
                    value = contactViewModel.contactNameState,
                    onValueChange = { contactViewModel.onContacNameChanged(it) }
                )

                // Campo de teléfono
                ContactTextField(
                    label = "Teléfono",
                    value = contactViewModel.contactPhoneState,
                    onValueChange = { contactViewModel.onContacPhoneChanged(it) },
                    keyboardType = KeyboardType.Phone
                )

                // Campo de correo
                ContactTextField(
                    label = "Correo",
                    value = contactViewModel.contactMailState,
                    onValueChange = { contactViewModel.onContacMailChanged(it) },
                    keyboardType = KeyboardType.Email
                )

                // Campo de URL de imagen de perfil
                ContactTextField(
                    label = "URL de Imagen de Perfil",
                    value = contactViewModel.contactImageState,
                    onValueChange = { contactViewModel.onContacImageChanged(it) },
                    keyboardType = KeyboardType.Uri
                )

                // Campo de fecha de nacimiento
                ContactTextField(
                    label = "Fecha de Nacimiento (DD/MM/AAAA)",
                    value = contactViewModel.contactBirthdateState,
                    onValueChange = { contactViewModel.onContacBirthdateChanged(it) },
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = {
                        if (contactViewModel.contactNameState.isNotEmpty() &&
                            contactViewModel.contactPhoneState.isNotEmpty() &&
                            contactViewModel.contactMailState.isNotEmpty() &&
                            contactViewModel.contactImageState.isNotEmpty() &&
                            contactViewModel.contactBirthdateState.isNotEmpty()
                        ) {
                            if (id != 0L) {
                                // Actualizar contacto
                                contactViewModel.updateContact(
                                    Contact(
                                        id = id,
                                        nombre = contactViewModel.contactNameState.trim(),
                                        telefono = contactViewModel.contactPhoneState.trim(),
                                        correo = contactViewModel.contactMailState.trim(),
                                        imagenPerfil = contactViewModel.contactImageState.trim(),
                                        fechaNacimiento = contactViewModel.contactBirthdateState.trim()
                                    )
                                )
                                snackMessage.value = "Modificando contacto"
                            } else {
                                // Crear nuevo contacto
                                contactViewModel.addContact(
                                    Contact(
                                        nombre = contactViewModel.contactNameState.trim(),
                                        telefono = contactViewModel.contactPhoneState.trim(),
                                        correo = contactViewModel.contactMailState.trim(),
                                        imagenPerfil = contactViewModel.contactImageState.trim(),
                                        fechaNacimiento = contactViewModel.contactBirthdateState.trim()
                                    )
                                )
                                snackMessage.value = "Creando contacto"
                            }

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = snackMessage.value,
                                    duration = SnackbarDuration.Short // Duración corta
                                )
                                navController.navigateUp()
                            }

                        } else {
                            snackMessage.value = "Debes ingresar todos los datos de contacto"

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = snackMessage.value,
                                    duration = SnackbarDuration.Short // Duración corta
                                )
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE) // Cambiar color aquí
                    )

                ) {
                    Text(
                        text = if (id != 0L) "Actualizar Contacto" else "Crear Contacto",
                        fontSize = 18.sp
                    )
                }

                SnackbarHost(hostState = snackbarHostState)
            }
        }
    )
}
