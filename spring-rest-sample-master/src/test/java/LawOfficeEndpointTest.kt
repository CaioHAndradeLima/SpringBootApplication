import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.repository.LawOfficeRepository
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LawOfficeEndpoint
import com.mechanical.endpoint.LoginEndpoint
import com.mechanical.infix_utils.toJson
import extensions.mockJson
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers



@WebMvcTest(controllers = [LawOfficeEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [ApplicationTest::class])
class LawOfficeEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var lawRepository: LawOfficeRepository

    @MockBean
    lateinit var userRepository: UserRepository


    @Test
    fun validGET() {
        val user = mockJson<User>("user.json")
        doLogin(user, userRepository, mvc)

        val lawOffice = mockJson<LawOffice>("lawOffice.json")
        BDDMockito.given(lawRepository.findAll()).willReturn(mutableListOf(lawOffice))

        val result = mvc.perform(MockMvcRequestBuilders.get("/api/lawOffice")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        assertEquals(result.response.contentAsString, mutableListOf(lawRepository).toJson())


    }

}