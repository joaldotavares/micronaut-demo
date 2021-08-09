package br.com.zup.repository

import br.com.zup.model.Novo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface NovoRepository : JpaRepository<Novo, Long> {

}