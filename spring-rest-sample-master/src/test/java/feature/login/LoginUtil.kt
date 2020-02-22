package feature.login

import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.repository.WorkerRepository
import com.mechanical.infix_utils.toJson
import mocks.newInstanceLoginEntity
import org.mockito.BDDMockito
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

fun doLogin(workerSession: WorkerSession, workerRepository: WorkerRepository, mvc: MockMvc): ResultActions {
    val newInstanceLoginEntity = newInstanceLoginEntity()
    BDDMockito.given(workerRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(
            workerSession.worker
    )

    return mvc.perform(MockMvcRequestBuilders.post("/loginapi")
            .content(newInstanceLoginEntity.toJson())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
}