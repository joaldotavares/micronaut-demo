package br.com.zup.dto

import br.com.zup.model.Endereco
import br.com.zup.model.Novo
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String
){
    fun toModel(enderecoResponse: EnderecoResponse): Novo{
        val endereco = Endereco(enderecoResponse)
        return Novo(nome, email, descricao, endereco)
    }
}
