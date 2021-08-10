package br.com.zup.client

import br.com.zup.dto.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json")
    fun consultar(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}