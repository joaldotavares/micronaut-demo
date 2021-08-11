package br.com.zup.controller

import br.com.zup.client.EnderecoClient
import br.com.zup.dto.EnderecoResponse
import br.com.zup.dto.NovoRequest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastrarNovoControllerTest{

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client : HttpClient

    @Test
    internal fun `deve cadastrar um novo`() {

        val novoRequest =  NovoRequest("jota", "jota@email.com", "dale dele", "40720264")

        val enderecoResponse = EnderecoResponse("40720264", "Avenida 2 de Julho de Periperi", "", "Periperi", "Salvador", "BA")
        Mockito.`when`(enderecoClient.consultar(novoRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/novo", novoRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/novo/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock() : EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }
}