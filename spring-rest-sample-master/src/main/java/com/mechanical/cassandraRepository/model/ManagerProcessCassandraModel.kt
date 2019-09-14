package com.mechanical.cassandraRepository.model

import com.datastax.driver.core.DataType
import com.mechanical.apiescavador.out.ManagerProcessEscavadorModel
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import java.util.*

data class ManagerProcessCassandraModel(
        val id: Int,
        val processNumber: String,
        val status: StatusManagerProcess?,
        val sendCallback: String,
        val statusCallback: String?,
        val linkApi: String,

        @PrimaryKeyColumn(name = "uuid", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        @CassandraType(type = DataType.Name.UUID)
        val uuid: UUID
) {
    constructor(it: ManagerProcessEscavadorModel) : this(it.id, it.processNumber, it.status, it.sendCallback, it.statusCallback, it.linkApi, UUID.randomUUID())
}

enum class StatusManagerProcess {
    //As informações do processo ainda estão sendo pesquisadas
    PENDENTE,
    // As informações do processo já estão disponíveis
    SUCESSO,
    //Não conseguiu coletar as informações do processo
    ERRO
}