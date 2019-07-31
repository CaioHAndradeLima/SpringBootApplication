import com.mechanical.Application
import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.UserImpl
import com.mechanical.cassandraRepository.repository.AddressUserRepository
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.endpoint.LoginEndpoint
import com.mechanical.endpoint.UserEndpoint
import com.mechanical.infix_utils.toJson
import extensions.mockJson
import mocks.newInstanceLoginEntity
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import javax.servlet.http.HttpServletRequest

@WebMvcTest(controllers = [LoginEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [ApplicationTest::class])
class LoginEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @InjectMocks
    @Autowired
    private var userImpl: UserImpl = UserImpl()

    @MockBean
    lateinit var userRepository: UserRepository

    @MockBean
    lateinit var addressUserRepository: AddressUserRepository


    @Test
    fun givenEmployees_whenGetEmployees_thenReturnJsonArray() {
        val user = mockJson<User>("user.json")
        userImpl.saveUser(user, true)

        val newInstanceLoginEntity = newInstanceLoginEntity()
        given(userRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(
                user.user
        )

        /*
        Employee alex = new Employee("alex");

        List<Employee> allEmployees = Arrays.asList (alex);

        given(service.getAllEmployees()).willReturn(allEmployees);*/

        mvc.perform(post("/loginapi")
                .content(newInstanceLoginEntity.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
    }
}