package br.com.zup.model

import br.com.zup.dto.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse) {
    val cep = enderecoResponse.cep
    val logradouro = enderecoResponse.logradouro
    val complemento = enderecoResponse.complemento
    val bairro = enderecoResponse.bairro
    val localidade = enderecoResponse.localidade
    val uf = enderecoResponse.uf
}
