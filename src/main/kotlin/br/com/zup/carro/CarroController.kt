package br.com.zup.carro

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/carro")
class CarroController(val carroRepository: CarroRepository) {

    @Post
    fun cadastrar(@Body @Valid carroRequest: CarroRequest): HttpResponse<Any> {

        val carro = carroRequest.toModel()
        carroRepository.save(carro)
        val uri = UriBuilder.of("/carro/{id}").expand(mutableMapOf(Pair("id", carro.id)))
        return HttpResponse.created(uri)
    }
}