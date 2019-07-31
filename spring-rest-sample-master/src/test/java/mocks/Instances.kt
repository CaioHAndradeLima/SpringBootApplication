package mocks

import com.mechanical.endpoint.LoginEntity
import java.util.*

fun newInstanceLoginEntity(
        emailOrCPF: String = "44574551801",
        password: String = "senha do cara",
        macAddress: String = "23424r52",
        keyOfRequests: String = UUID.randomUUID().toString()
) = LoginEntity(
        emailOrCPF,
        password,
        macAddress,
        keyOfRequests
)


