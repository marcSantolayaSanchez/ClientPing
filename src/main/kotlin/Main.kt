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
    var contador = 0
    while (true) {
        val client = HttpClient(CIO)
        val mensajeEnviado = "ping"
        val response: HttpResponse = client.post("http://localhost:8080/ping") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(Mensaje(mensajeEnviado)))

        }
        if (contador == 0 || contador % 10 == 0) {
            println("Status del POST: ${response.status}")
        }


        val responseText = response.body<String>()

        val mensaje = Json.decodeFromString<Mensaje>(responseText)
        println("Mensaje que le envio al servidor: $mensajeEnviado")
        println("Mensaje del servidor: ${mensaje.mensaje}\n")
        contador++
        Thread.sleep(2000)
    }
    //client.close()
}