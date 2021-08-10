package br.com.zup.controller

import br.com.zup.dto.NovoResponse
import br.com.zup.repository.NovoRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.validation.Validated
import javax.transaction.Transactional

@Validated
@Controller("/novo")
class BuscarAutoresController(val novoRepository: NovoRepository) {

    @Get
    @Transactional
    fun listar(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()) {
            val novo = novoRepository.findAll()
            val resposta = novo.map { novos -> NovoResponse(novos) }
            return HttpResponse.ok(resposta)
        }
        val novo = novoRepository.findByEmail(email)
        if (novo.isEmpty) {
            return HttpResponse.notFound()
        }
        val resposta = novo.get()

        return HttpResponse.ok(NovoResponse(resposta))
    }
}