package cl.bootcamp.ind1m6android.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val imagenPerfil: String, // URL para la imagen de perfil
    val fechaNacimiento: String
)
//Prueba de lista
object DummyContacts{
    val contactList = listOf(
        Contact(1,"alex","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(2,"aex","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(3,"xale","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(1,"alex","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(2,"aex","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(3,"xale","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(3,"xale","961282570","alex@coreo.cl","", "16-05-12"),
        Contact(3,"xale","961282570","alex@coreo.cl","", "16-05-12")
    )

}