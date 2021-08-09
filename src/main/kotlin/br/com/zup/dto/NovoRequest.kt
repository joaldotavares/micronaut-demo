package br.com.zup.dto

import br.com.zup.model.Novo
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String
){
    fun toModel(): Novo{
        return Novo(nome, email, descricao)
    }
}
