import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Mensaje

suspend fun main() {
    val client = HttpClient(CIO)
    var response: HttpResponse = client.get("http://localhost:8080/customer") {
        contentType(ContentType.Application.Json)
        setBody(Json.encodeToString(Mensaje("ping")))

    }
    response = client.get("http://localhost:8080/customer")
    println("Status: del GET : ${response.status}")
    client.close()

    var pong = Json.decodeFromString<List<Mensaje>>(response.bodyAsText())
    println(pong)
    for (c in pong) {
        println("Mensaje : ${c.mensaje}")
    }
}