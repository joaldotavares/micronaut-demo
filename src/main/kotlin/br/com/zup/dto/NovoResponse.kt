package br.com.zup.dto

import br.com.zup.model.Endereco
import br.com.zup.model.Novo

class NovoResponse(novo: Novo) {

    val nome = novo.nome
    val email = novo.email
    val descricao = novo.descricao
    val endereco = novo.endereco
}