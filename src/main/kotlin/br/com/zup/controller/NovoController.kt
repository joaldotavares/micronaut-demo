package br.com.zup.controller

import br.com.zup.client.EnderecoClient
import br.com.zup.dto.EnderecoResponse
import br.com.zup.dto.NovoRequest
import br.com.zup.dto.NovoResponse
import br.com.zup.repository.NovoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/novo")
class NovoController(val novoRepository: NovoRepository, val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: NovoRequest): HttpResponse<Any> {
        println(request)

        val enderecoResponse = enderecoClient.consultar(request.cep)

        val novo = request.toModel(enderecoResponse.body()!!)
        novoRepository.save(novo)

        val uri = UriBuilder.of("/novo/{id}").expand(mutableMapOf(Pair("id", novo.id)))
        return HttpResponse.created(uri)
    }

    @Put("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, nome: String, descricao: String): HttpResponse<Any> {
        val novo = novoRepository.findById(id)

        if (novo.isEmpty) {
            return HttpResponse.notFound()
        }
        val resposta = novo.get()

        resposta.descricao = descricao
        resposta.nome = nome

        return HttpResponse.ok(NovoResponse(resposta))
    }

    @Delete("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): HttpResponse<Any> {
        val novo = novoRepository.findById(id)

        if (novo.isEmpty) {
            return HttpResponse.notFound()
        }

        novoRepository.deleteById(id)

        return HttpResponse.ok()
    }
}