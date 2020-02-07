package com.mechanical.cassandraRepository.model

import com.google.gson.annotations.SerializedName
import com.mechanical.provider.provideGson
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table("user")
data class UserCassandraModel constructor(
        @PrimaryKeyColumn(name = "CPF", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        var cpf: String,
        @PrimaryKeyColumn(name = "isLawyer", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var isLawyer: Boolean,
        @PrimaryKeyColumn(name = "email", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
        var email: String,
        var name: String,
        var phone: String,
        var additionalInfo: String,
        var password: String,
        var oab: String?,
        @Transient var listFiles: Set<String>?,
        var latitude: Double,
        var longitude: Double,
        var street: String,
        var CEP: String,
        var neighborhood: String,
        var identifierHome: String,
        var state: String,
        var whereWork: String
) {
        fun getListWhereWork() = provideGson()
                .fromJson(whereWork, Array<Work>::class.java)
}


data class Work(
        @SerializedName("cpfOwner") val cpfOwner: String,
        @SerializedName("uuid") val uuid: String
)