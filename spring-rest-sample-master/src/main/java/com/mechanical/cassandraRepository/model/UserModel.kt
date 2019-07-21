package com.mechanical.cassandraRepository.model

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table
data class UserCassandraModel(
        @PrimaryKeyColumn(name = "CPF", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        var cpf: String,
        @PrimaryKeyColumn(name = "email", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var email: String,
        @PrimaryKeyColumn(name = "uuidAddress", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var UUIDAddress: UUID,
        var name: String,
        var maritalStatus: String,
        var phone: String,
        var additionalInfo: String,
        var qualificationPart: String,
        var password: String,
        var isLawyer: Boolean,
        @Transient var listFiles: Set<String>)

@Table
data class AddressUserCassandraModel(
        @PrimaryKeyColumn(name = "uuid", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        var uuid: UUID,
        var latitude: Double,
        var longitude: Double,
        var street: String,
        var CEP: String,
        var neighborhood: String,
        var identifierHome: String,
        var state: String
)