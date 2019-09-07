import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.repository.UserRepository
import com.mechanical.infix_utils.toJson
import mocks.newInstanceLoginEntity
import org.mockito.BDDMockito
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

fun doLogin(user: User, userRepository: UserRepository, mvc: MockMvc): ResultActions {
    val newInstanceLoginEntity = newInstanceLoginEntity()
    BDDMockito.given(userRepository.findBycpf(newInstanceLoginEntity.emailOrCPF)).willReturn(
            user.user
    )

    return mvc.perform(MockMvcRequestBuilders.post("/loginapi")
            .content(newInstanceLoginEntity.toJson())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
}