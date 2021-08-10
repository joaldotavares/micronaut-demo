package br.com.zup.carro

import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PlacaValidator::class])
annotation class Placa (
    val message: String = "Placa com formato inválido!"
        )