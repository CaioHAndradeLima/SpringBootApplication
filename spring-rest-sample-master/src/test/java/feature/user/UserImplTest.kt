package feature.user

import com.mechanical.Application
import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.UserImpl
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
    lateinit var userImpl: UserImpl

    @Test
    fun whenAddTheUser_AddressIsAddedToo() {
        val user = mockJson<User>("user.json")
        userImpl.saveUser(user)

        var loginEntity = newInstanceLoginEntity()

        //test case success (cpf correct + password correct)
        val userCorrect = userImpl.getAllUser(loginEntity)
        assertEquals(userCorrect, user)

        //test case success (email correct + password correct)
        loginEntity = newInstanceLoginEntity(emailOrCPF = "caiohandradelima@gmail.com")
        val allUserEmailCorrect = userImpl.getAllUser(loginEntity)
        assertEquals(user, allUserEmailCorrect)

        //test case failied(cpf correct + password wrong)
        loginEntity = newInstanceLoginEntity(password = "24244")
        val allUserPassowrdWrong = userImpl.getAllUser(loginEntity)
        assertEquals(null, allUserPassowrdWrong)

        //test case failied(emailOrCpf wrong + password correct)
        loginEntity = newInstanceLoginEntity(emailOrCPF = "24244")
        val allUserCPFWrong = userImpl.getAllUser(loginEntity)
        assertEquals(null, allUserCPFWrong)
    }
}