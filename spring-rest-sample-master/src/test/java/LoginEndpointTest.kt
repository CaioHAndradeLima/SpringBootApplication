import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.UserImpl
import com.mechanical.cassandraRepository.repository.AddressUserRepository
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LoginEndpoint
import com.mechanical.infix_utils.toJson
import extensions.mockJson
import mocks.newInstanceLoginEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [LoginEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [ApplicationTest::class])
class LoginEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var userRepository: UserRepository

    @MockBean
    lateinit var addressUserRepository: AddressUserRepository

    @InjectMocks
    @Autowired
    private lateinit var userImpl: UserImpl

    /**
     * When make the call "/loginapi" passing info to do login
     * is needed search user by e-mail or CPF and return user when found
     * with responde code 200
     */
    @Test
    fun whenUserCallLoginPassingLoginEntity_couldReturnUserAndResponseOK() {
        val user = mockJson<User>("user.json")

        val newInstanceLoginEntity = newInstanceLoginEntity()
        given(userRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(
                user.user
        )

        mvc.perform(post("/loginapi")
                .content(newInstanceLoginEntity.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
}