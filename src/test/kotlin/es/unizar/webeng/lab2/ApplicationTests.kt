package es.unizar.webeng.lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ErrorPageTest {
    @LocalServerPort
    private var port: Int = 8443

    private val client = TestRestTemplate(TestRestTemplate.HttpClientOption.SSL)

    @Test
    fun unknownPathRendersErrorHtml() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)
        val response =
            client.exchange(
                "https://localhost:$port/missing",
                HttpMethod.GET,
                HttpEntity<Void>(headers),
                String::class.java,
            )
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertTrue(response.body!!.contains("your marker"))
        assertTrue(response.body!!.contains("/missing"))
    }

    @Test
    fun contextLoads() {
    }
}

@SpringBootTest
@AutoConfigureMockMvc
class TimeControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun timeIsJson() {
        mockMvc
            .perform(get("/time").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.time").exists())
    }
}
