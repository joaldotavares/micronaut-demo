package br.com.zup.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Novo(
    var nome: String,
    val email: String,
    var descricao: String,
    val endereco: Endereco
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()
}