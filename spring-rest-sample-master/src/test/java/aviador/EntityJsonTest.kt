package aviador

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.apiescavador.out.AuthenticationEscavadorOut
import com.mechanical.cassandraRepository.WorkerSession
import com.mechanical.cassandraRepository.extensions.cutSpaces
import com.mechanical.infix_utils.toJson
import extensions.getJson
import extensions.mockJson
import junit.framework.Assert.assertEquals
import org.junit.Test

class EntityJsonTest {

    @Test
    fun validateJsonEscavadorModel() {
        validateEntity(PairMutable<String, ProcessEscavadorModel?>("processEscavador.json"))
        validateEntity(PairMutable<String, AuthenticationEscavadorOut?>("authenticEscavador.json"))
    }

    @Test
    fun validateJsonModel() {
        validateEntity(PairMutable<String, WorkerSession?>("user.json"))
        validateEntity(PairMutable<String,LawOffice?>("lawOffice.json"))
    }

    inline fun <reified T : Any> validateEntity(list: Array<PairMutable<String, T?>>) {
        for (pairMutable in list) {
            validateEntity(pairMutable)
        }
    }

    inline fun <reified T> validateEntity(pair: PairMutable<String, T?>) {
        pair.second = mockJson(pair.first)

        assertEquals(
                getJson(pair.first).cutSpaces(),
                pair.second!!.toJson().cutSpaces()
        )
    }
}

data class PairMutable<out A, B>(
        val first: A,
        var second: B? = null
)
