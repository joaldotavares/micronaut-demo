package br.com.zup.controller

import br.com.zup.dto.NovoRequest
import br.com.zup.dto.NovoResponse
import br.com.zup.repository.NovoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/novo")
class NovoController (val novoRepository: NovoRepository){

    @Post
    fun cadastra(@Body @Valid request: NovoRequest): HttpResponse<Any>{
        println(request)

        val novo = request.toModel()
        novoRepository.save(novo)

        val uri = UriBuilder.of("/novo/{id}").expand(mutableMapOf(Pair("id", novo.id)))
        return HttpResponse.created(uri)
    }

    @Get
    fun listar(): HttpResponse<List<NovoResponse>> {
        val novos = novoRepository.findAll()

        val resposta = novos.map {
            novo -> NovoResponse(novo)
        }

        return HttpResponse.ok(resposta)
    }

    @Put("/{id}")
    fun atualizar(@PathVariable id: Long, nome: String, descricao: String) : HttpResponse<Any>{
        val novo = novoRepository.findById(id)

        if(novo.isEmpty){
            return HttpResponse.notFound()
        }
        val resposta = novo.get()

        resposta.descricao = descricao
        resposta.nome = nome

        novoRepository.update(resposta)

        return HttpResponse.ok(NovoResponse(resposta))
    }

    @Delete("/{id}")
    fun deletar(@PathVariable id: Long) : HttpResponse<Any>{
        val novo = novoRepository.findById(id)

        if(novo.isEmpty){
            return HttpResponse.notFound()
        }

        novoRepository.deleteById(id)

        return HttpResponse.ok()
    }
}