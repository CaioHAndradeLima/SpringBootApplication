import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.impl.UserImpl
import com.mechanical.infix_utils.toJson
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.io.FileReader
import java.lang.reflect.Type

@RunWith(SpringRunner::class)
class UserImplTest {

    companion object {
        @Bean
        @JvmStatic
        fun getUserImplementation() = UserImpl()
    }

    var userImpl: UserImpl = getUserImplementation()


    val gson = Gson()

    @Test
    fun whenAddTheUser_AddressIsAddedToo() {
        val user = mockJson<User>("user.json")
    }

    inline fun <reified T> mockJson(nameFile: String): T {
        val localFile = "src/test/resources/$nameFile"
        val reader = JsonReader(FileReader(localFile))
        return gson.fromJson(reader, T::class.java)
    }

    class TypeTokenJson<T> : TypeToken<T>()
}