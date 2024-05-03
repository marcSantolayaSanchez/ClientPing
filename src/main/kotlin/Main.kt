import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Mensaje

suspend fun main() {
    val client = HttpClient(CIO)
    val mensajeEnviado = "ping"
    val response: HttpResponse = client.post("http://localhost:8080/ping") {
        contentType(ContentType.Application.Json)
        setBody(Json.encodeToString(Mensaje(mensajeEnviado)))
    }
    println("Status del POST: ${response.status}")

    val responseText = response.body<String>()
    println("Respuesta del servidor: $responseText")

        val mensaje = Json.decodeFromString<Mensaje>(responseText)
        println("Mensaje que le envio al servidor $mensajeEnviado")
        println("Mensaje del servidor: ${mensaje.mensaje}")


    client.close()
}