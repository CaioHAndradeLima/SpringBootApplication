package com.mechanical.cassandraRepository

import com.datastax.driver.core.DataType
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

import java.util.UUID

@Table(value = "user")
class User {

    @PrimaryKeyColumn(name = "uuid", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    @CassandraType(type = DataType.Name.UUID)
    lateinit var uuid: UUID

    lateinit var name: String

    var password: String? = null
        set(password) {
            field = PASSWORD_ENCODER.encode(password)
        }

    @JsonIgnore
    var roles: Set<String>? = null

    constructor(name: String) {
        this.name = name
        uuid = UUID.randomUUID()
    }

    constructor(uuid: UUID, name: String) : this(name) {
        this.uuid = uuid
    }

    constructor() {}
}

val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
