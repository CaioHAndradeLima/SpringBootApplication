package com.mechanical.apiescavador.out

class ProcessEscavadorModel(
        var id: Int,
        var numero_processo: String,
        var status: String,
        var resposta: RespostaEscavadorModel,
        var enviar_callback: String,
        var status_callback: String,
        var link_api: String,
        var created_at: CreatedAtEscavadorModel
)

class CreatedAtEscavadorModel(
        var date: String,
        var timezone_type: Int,
        var timezone: String
)

class RespostaEscavadorModel(
        var numero_unico: String,
        var origem: String,
        var instancias: List<InstanciaEscavadorModel>
)

class InstanciaEscavadorModel(
        var url: String,
        var sistema: String,
        var instancia: String,
        var extra_instancia: String,
        var segredo: Boolean,
        var numero: Any,
        var assunto: String,
        var classe: String,
        var area: String,
        var data_distribuicao: String,
        var orgao_julgador: String,
        var valor_causa: String,
        var last_update_time: String,
        var dados: List<DadosEscavadorModel>?,

        var partes: List<PartesEscavadorModel>,
        var movimentacoes: List<MovimentacoesEscavadorModel>,
        var audiencias: List<AudienciasEscavadorModel>
) {

    companion object {
        private const val JUIZ = "Juiz"
    }


    fun getJuiz(): String? {
        dados?.forEach {
            if(it.tipo == JUIZ) return it.valor
        }

        return null
    }
}

class DadosEscavadorModel(
        var tipo: String,
        var valor: String
)

class PartesEscavadorModel(
        var id: Int,
        var tipo: String,
        var nome: String,
        var principal: Boolean,
        var polo: Any,
        var documento: DocumentoEscavadorModel
)

class DocumentoEscavadorModel(
        var tipo: Any,
        var numero: Any
)

class MovimentacoesEscavadorModel(
        var id: Int,
        var data: String,
        var conteudo: String
)

class AudienciasEscavadorModel(
        var data: String,
        var audiencia: String,
        var situacao: String,
        var numero_pessoas: Int
)

class EnviarCallbackEscavadorModel(

)