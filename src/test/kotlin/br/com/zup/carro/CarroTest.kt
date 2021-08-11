package br.com.zup.carro

import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(
    rollback = false,
    transactionMode = TransactionMode.SINGLE_TRANSACTION, //por padrao e SEPARADE. Executa as transacoes, exeto o AfterEach, que possui uma transacao individual
    transactional = false //por padrao é true
)
internal class CarroTest{

    @field:Inject
    lateinit var carroRepository: CarroRepository

    @BeforeEach
    fun setUp(){
        carroRepository.deleteAll()
    }

    @AfterEach
    fun clearUp(){
        carroRepository.deleteAll()
    }

    @Test
    fun `deve inserir um novo carro`(){
        carroRepository.save(Carro("Uno", "BRA2E19"))

        assertEquals(1, carroRepository.count())
    }

    @Test
    fun `deve encontrar carro pela placa`(){
        carroRepository.save(Carro("Chevete", "BRA2E30"))

        val verify = carroRepository.existsByPlaca("BRA2E30")

        assertTrue(verify)
    }
}