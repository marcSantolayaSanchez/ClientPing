package models
import kotlinx.serialization.Serializable

//Simula la Base de Dades
val customerStorage = mutableListOf<Mensaje>()

@Serializable
data class Mensaje(val mensaje : String)