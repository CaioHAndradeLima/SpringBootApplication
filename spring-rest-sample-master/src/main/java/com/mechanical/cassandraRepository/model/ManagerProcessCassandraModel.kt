package com.mechanical.cassandraRepository.model

import com.datastax.driver.core.DataType
import com.mechanical.apiescavador.out.ManagerProcessEscavadorOut
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("ManagerProcessCassandraModel")
data class ManagerProcessCassandraModel(
        val id: Int,
        val processNumber: String,
        val status: StatusManagerProcess?,
        val sendCallback: String,
        val statusCallback: String?,
        val linkApi: String,
        @PrimaryKeyColumn(name = "uuid", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        @CassandraType(type = DataType.Name.UUID)
        val uuid: UUID,
        val timeSearch: SearchType = SearchType.NEVER
) {
    constructor(it: ManagerProcessEscavadorOut) : this(it.id, it.processNumber, it.status, it.sendCallback, it.statusCallback, it.linkApi, UUID.randomUUID())
}

enum class SearchType {
    TODAY, WEEK, NEVER
}

enum class StatusManagerProcess {
    //As informações do processo ainda estão sendo pesquisadas
    PENDENTE,
    // As informações do processo já estão disponíveis
    SUCESSO,
    //Não conseguiu coletar as informações do processo
    ERRO
}