package br.com.zup.controller

import br.com.zup.dto.EnderecoResponse
import br.com.zup.dto.NovoResponse
import br.com.zup.model.Endereco
import br.com.zup.model.Novo
import br.com.zup.repository.NovoRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscarAutoresControllerTest{

    @field:Inject
    lateinit var novoRepository: NovoRepository

    /*
    * Recurso para testar a API
    */
    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var novo: Novo
    /*
     * Vai adicionar um novo no banco para ser testado
    */
    @BeforeEach
    internal fun setUp(){

        val enderecoResponse = EnderecoResponse("40720264", "Avenida 2 de Julho de Periperi", "Apto 104", "Periperi", "Salvador", "BA")
        val endereco = Endereco(enderecoResponse)
        novo = Novo("jota", "jota@email.com", "É o novo jota", endereco)
        novoRepository.save(novo)
    }

    /*
    * Apaga todos os dados que foram gerados para o teste no banco
    */
    @AfterEach
    internal fun tearDown(){
        novoRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar os detalhes de um autor`(){

        /*
        * "toBlocking" - Por padrao o client e reativo, entao fazemos ele blocante
        * "exchange" - definicao para qual contexto sera feito o response
        */
        val response = client.toBlocking().exchange("/novo?email=${novo.email}", NovoResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(novo.nome, response.body()!!.nome)
        assertEquals(novo.email, response.body()!!.email)
        assertEquals(novo.descricao, response.body()!!.descricao)
    }
}