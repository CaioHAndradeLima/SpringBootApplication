import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LoginEndpoint
import com.mechanical.infix_utils.toJson
import extensions.mockJson
import junit.framework.Assert.assertEquals
import mocks.newInstanceLoginEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.ResultActions


@WebMvcTest(controllers = [LoginEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [ApplicationTest::class])
class LoginEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var userRepository: UserRepository

    /**
     * When make the call "/loginapi" passing info to do login
     * is needed search user by e-mail or CPF and return user when found
     * with responde code 200
     */
    @Test
    fun whenUserCallLoginPassingLoginEntity_couldReturnUserAndResponseOK() {
        val user = mockJson<User>("user.json")
        val resultActions = doLogin(user)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
                //.andExpect(jsonPath("$.user.toString()", `is`(user.user.toString())))

        val result = resultActions.andReturn()
        assertEquals(result.response.contentAsString, user.toJson())
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and user not found or return null, the server awsner to
     * client unprocessable Entity
     */
    @Test
    fun whenUserCallLoginPassingLoginEntityIncorrect_couldReturnResponseError() {
        val newInstanceLoginEntity = newInstanceLoginEntity()
        given(userRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(null)


        mvc.perform(post("/loginapi")
                .content(newInstanceLoginEntity.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
        //.andExpect(jsonPath("$.user.toString()", `is`(user.user.toString())))
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and user is found and authenticate, is possible now
     * request /api/...
     */
    @Test
    fun whenUserDoLogin_isPossibleRequestPathApi() {
        val user = mockJson<User>("user.json")
        doLogin(user)

        TODO("TEST REQUISITION HERE")
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and user is NOT found and not authenticate, is not possible
     * request /api/...
     */
    @Test
    fun whenUserNotDidLogin_isNotPossibleRequestPathApi() {
        val user = mockJson<User>("user.json")
        doLogin(user)

        mvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }


    /**
     * make the login passing the user
     */
    private fun doLogin(user: User): ResultActions {
        val newInstanceLoginEntity = newInstanceLoginEntity()
        given(userRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(
                user.user
        )

        return mvc.perform(post("/loginapi")
                .content(newInstanceLoginEntity.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }


}