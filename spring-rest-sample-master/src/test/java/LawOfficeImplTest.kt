import com.mechanical.Application
import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import com.mechanical.cassandraRepository.model.LawOffice
import extensions.mockJson
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [Application::class])
class LawOfficeImplTest {

    @Autowired
    lateinit var lawOfficeImpl: LawOfficeImpl

    @Test
    fun whenLawOfficeIsSaved_needReturnCorrect() {
        val lawOffice = mockJson<LawOffice>("lawOffice.json")

        lawOfficeImpl.save(lawOffice)

        assertEquals(
                lawOfficeImpl.getLawOfficeByCpfOwner(lawOffice.cpfOwner)[0].toString(),
                lawOffice.toString()
        )

    }

}