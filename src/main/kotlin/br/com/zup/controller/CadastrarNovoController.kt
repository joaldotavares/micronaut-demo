package br.com.zup.controller

import br.com.zup.client.EnderecoClient
import br.com.zup.dto.NovoRequest
import br.com.zup.repository.NovoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/novo")
class CadastrarNovoController(
    val novoRepository: NovoRepository,
    val enderecoClient: EnderecoClient
) {

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
}