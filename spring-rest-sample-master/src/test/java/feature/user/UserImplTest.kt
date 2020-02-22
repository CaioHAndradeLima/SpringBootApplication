package feature.user

import com.mechanical.Application
import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.impl.WorkerImpl
import extensions.mockJson
import mocks.newInstanceLoginEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [Application::class])
class UserImplTest {

    @Before
    fun setUp() {

    }

    @Autowired
    lateinit var workerImpl: WorkerImpl

    @Test
    fun whenAddTheUser_AddressIsAddedToo() {
        val user = mockJson<WorkerSession>("user.json")
        workerImpl.saveUser(user)

        var loginEntity = newInstanceLoginEntity()

        //test case success (cpf correct + password correct)
        val userCorrect = workerImpl.getAllUser(loginEntity)
        assertEquals(userCorrect, user)

        //test case success (email correct + password correct)
        loginEntity = newInstanceLoginEntity(emailOrCPF = "caiohandradelima@gmail.com")
        val allUserEmailCorrect = workerImpl.getAllUser(loginEntity)
        assertEquals(user, allUserEmailCorrect)

        //test case failied(cpf correct + password wrong)
        loginEntity = newInstanceLoginEntity(password = "24244")
        val allUserPassowrdWrong = workerImpl.getAllUser(loginEntity)
        assertEquals(null, allUserPassowrdWrong)

        //test case failied(emailOrCpf wrong + password correct)
        loginEntity = newInstanceLoginEntity(emailOrCPF = "24244")
        val allUserCPFWrong = workerImpl.getAllUser(loginEntity)
        assertEquals(null, allUserCPFWrong)
    }
}