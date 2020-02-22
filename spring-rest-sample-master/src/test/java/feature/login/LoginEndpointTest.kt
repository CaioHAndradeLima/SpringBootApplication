package feature.login

import com.mechanical.Application
import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.impl.ManagerProcessImpl
import com.mechanical.cassandraRepository.impl.ProcessMonitoringImpl
import com.mechanical.cassandraRepository.repository.WorkerRepository
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

@WebMvcTest(controllers = [LoginEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [Application::class])
open class LoginEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var workerRepository: WorkerRepository

    @MockBean
    lateinit var lawOfficeRepository: LawOfficeRepository

    @MockBean
    lateinit var eventImpl: EventCassandraImpl

    @MockBean
    lateinit var processMonitoringImpl: ProcessMonitoringImpl

    @MockBean
    lateinit var managerProcess: ManagerProcessImpl


    /**
     * When make the call "/loginapi" passing info to do login
     * is needed search workerSession by e-mail or CPF and return workerSession when found
     * with responde code 200
     */
    @Test
    fun whenUserCallLoginPassingLoginEntity_couldReturnUserAndResponseOK() {
        val user = mockJson<WorkerSession>("user.json")
        val resultActions = doLogin(user, workerRepository, mvc)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
                //.andExpect(jsonPath("$.workerSession.toString()", `is`(workerSession.workerSession.toString())))

        val result = resultActions.andReturn()
        assertEquals(result.response.contentAsString, user.toJson())
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and workerSession not found or return null, the server awsner to
     * client unprocessable Entity
     */
    @Test
    fun whenUserCallLoginPassingLoginEntityIncorrect_couldReturnResponseError() {
        val newInstanceLoginEntity = newInstanceLoginEntity()
        given(workerRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(null)


        mvc.perform(post("/loginapi")
                .content(newInstanceLoginEntity.toJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity)
        //.andExpect(jsonPath("$", hasSize(1)))
        //.andExpect(jsonPath("$[0].name", is(alex.getName())));
        //.andExpect(jsonPath("$.workerSession.toString()", `is`(workerSession.workerSession.toString())))
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and workerSession is found and authenticate, is possible now
     * request /api/...
     */
    @Test
    fun whenUserDoLogin_isPossibleRequestPathApi() {
        val user = mockJson<WorkerSession>("user.json")
        doLogin(user, workerRepository, mvc)

        TODO("ITS NOT WORKING")
    }

    /**
     * When make the call "/loginapi" passing info to do login
     * and workerSession is NOT found and not authenticate, is not possible
     * request /api/...
     */
    @Test
    fun whenUserNotDidLogin_isNotPossibleRequestPathApi() {
        val user = mockJson<WorkerSession>("user.json")
        doLogin(user, workerRepository, mvc)

        mvc.perform(post("/api/workerSession")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }
}

