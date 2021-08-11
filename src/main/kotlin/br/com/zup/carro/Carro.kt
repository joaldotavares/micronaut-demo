package br.com.zup.carro

import io.micronaut.core.annotation.Introspected
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Carro(
    @field:NotBlank val modelo: String,
    @field: Placa @Column(unique = true) val placa: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}