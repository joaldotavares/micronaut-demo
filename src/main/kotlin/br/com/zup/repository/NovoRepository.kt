package br.com.zup.repository

import br.com.zup.model.Novo
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface NovoRepository : JpaRepository<Novo, Long> {
    fun findByEmail(email: String): Optional<Novo>
}