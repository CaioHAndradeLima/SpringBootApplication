package com.mechanical.cassandraRepository.model

import com.datastax.driver.core.DataType
import com.google.gson.annotations.SerializedName
import com.mechanical.infix_utils.toJson
import com.mechanical.provider.provideGson
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

@Table("processCassandra")
data class ProcessCassandraModel(
        @PrimaryKeyColumn(name = "numberProcess", type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
        @CassandraType(type = DataType.Name.UUID)
        @SerializedName("numberProcess")
        val numberProcess: String,
        var lastUpdate: Long,

        @SerializedName("instancias")
        val instancias: String
) {

    constructor(
            numberProcess: String,
            lastUpdate: Long,
            im: Array<InstanciasModel>
    ) : this(numberProcess, lastUpdate, im.toJson())

    fun provideInstanciasModel(): Array<InstanciasModel> {
        return provideGson()
                .fromJson(instancias, Array<InstanciasModel>::class.java)
    }
}

data class InstanciasModel(
        //PRIMEIRO_GRAU
        val grau: String,
        val segredo: Boolean,
        val assunto: String,
        val classe: String,
        val area: String,
        //data_distribuicao
        val dataDistribuicao: String,
        val orgaoJulgador: String,
        val valorCausa: String,
        val dados: MutableSet<DadosModel>?,
        val partes: MutableSet<PartesModel>
)

data class DadosModel(
        val tipo: String,
        val valor: String
)

data class PartesModel(
        //Reclamante/Reclamado
        val tipo: String,
        val nome: String,
        val principal: Boolean,
        val documento: DocumentoModel?

)

data class DocumentoModel(
        val tipo: String,
        val numero: String
)