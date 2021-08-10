package br.com.zup.client

import br.com.zup.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json", consumes = [MediaType.APPLICATION_XML])
    fun consultar(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}