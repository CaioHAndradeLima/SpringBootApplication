package feature.lawOffice

import com.mechanical.provider.WorkerProvider
import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.extensions.toUUID
import com.mechanical.cassandraRepository.impl.ProcessMonitoringImpl
import com.mechanical.cassandraRepository.repository.WorkerRepository
import com.mechanical.endpoint.LoginEndpoint
import extensions.mockJson
import io.mockk.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import security.ApplicationTest


@WebMvcTest(controllers = [LoginEndpoint::class, LawOfficeEndpoint::class])
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [ApplicationTest::class])
class LawOfficeEndpointTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var lawRepository: LawOfficeRepository

    @MockBean
    lateinit var workerRepository: WorkerRepository

    @MockBean
    lateinit var eventImpl: EventCassandraImpl

    @MockBean
    lateinit var processManagerImpl: ProcessMonitoringImpl

    @Test
    fun validGET() {

        val lawOffice = mockJson<LawOffice>("lawOffice.json")

        BDDMockito.given(lawRepository.findAll()).willReturn(mutableListOf(lawOffice))

        val result = mvc.perform(get("/api/lawOffice")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()


        assertEquals(lawOffice,
                result.response.contentAsString.fromJson<Array<LawOffice>>()[0])

    }


    @Test
    fun validJobs() {

        val lawOffice = mockJson<LawOffice>("lawOffice.json")
        val user = mockJson<WorkerSession>("user.json")
        val work = user.worker.getListWhereWork()[0]

        BDDMockito.given(lawRepository.findByCpfOwnerAndUuid(work.cpfOwner, work.uuid.toUUID())).willReturn(lawOffice)


        mockkObject(WorkerProvider)
        every { WorkerProvider.provideUser() } returns user
        every { WorkerProvider.provideUserAuthenticate() } returns user.worker
        //BDDMockito.given().willReturn(workerSession.workerSession)

        val result = mvc.perform(get("/api/lawOffice/jobs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        assertEquals(lawOffice,
                result.response.contentAsString.fromJson<Array<LawOffice>>()[0])
    }


}