package com.mechanical.apiescavador.request

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.apiescavador.retrofit.provideRetrofitEscavadorWithoutLink
import com.mechanical.cassandraRepository.extensions.newLog
import com.mechanical.isDebug
import com.mechanical.provider.provideGson

object ProcessEscavador {

    fun requestProcessByLink(linkApi: String): ProcessEscavadorModel? {

        var process: ProcessEscavadorModel? = null


        provideRetrofitEscavadorWithoutLink()
                .requestProcess(linkApi)
                .subscribe({
                    val body = it.body()

                    if (it.isSuccessful && body != null) {

                        process = body
                    } else if (isDebug) {
                        System.out.println("class ManagerProcessEscavador.requestToEscavadorSearchNewProcess response code= ${it.code()} and response message= ${it.message()}")
                    }

                }) {
                    it.newLog()
                }


        //return process
        return provideGson()
                .fromJson(json, ProcessEscavadorModel::class.java)
    }

    val json = "{\n" +
            "  \"id\": 40,\n" +
            "  \"numero_processo\": \"0000000-00.2019.8.26.0000\",\n" +
            "  \"status\": \"SUCESSO\",\n" +
            "  \"resposta\": {\n" +
            "    \"numero_unico\": \"0000000-00.2019.8.26.0000\",\n" +
            "    \"origem\": \"TJSP\",\n" +
            "    \"instancias\": [\n" +
            "      {\n" +
            "        \"url\": \"https://esaj.tjsp.jus.br\",\n" +
            "        \"sistema\": \"ESAJ\",\n" +
            "        \"instancia\": \"PRIMEIRO_GRAU\",\n" +
            "        \"extra_instancia\": \"29000101E0000\",\n" +
            "        \"segredo\": false,\n" +
            "        \"numero\": null,\n" +
            "        \"assunto\": \"Compra e Venda\",\n" +
            "        \"classe\": \"Reclamação Pré-processual\",\n" +
            "        \"area\": \"Cível\",\n" +
            "        \"data_distribuicao\": \"22/02/2019\",\n" +
            "        \"orgao_julgador\": \"CEJUSC (Pré-Processual) - Foro de Adamantina\",\n" +
            "        \"valor_causa\": \"662.9900\",\n" +
            "        \"last_update_time\": \"26/02/2019 17:20\",\n" +
            "        \"dados\": [\n" +
            "          {\n" +
            "            \"tipo\": \"Processo\",\n" +
            "            \"valor\": \"0000000-00.2019.8.26.0000\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"tipo\": \"Controle\",\n" +
            "            \"valor\": \"2019/000156\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"tipo\": \"Juiz\",\n" +
            "            \"valor\": \"FULANO DE SOUZA\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"partes\": [\n" +
            "          {\n" +
            "            \"id\": 12,\n" +
            "            \"tipo\": \"Reclamante\",\n" +
            "            \"nome\": \"Nome da Pessoa\",\n" +
            "            \"principal\": true,\n" +
            "            \"polo\": null,\n" +
            "            \"documento\": {\n" +
            "              \"tipo\": null,\n" +
            "              \"numero\": null\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 13,\n" +
            "            \"tipo\": \"Reclamado\",\n" +
            "            \"nome\": \"Outro Nome de Pessoa\",\n" +
            "            \"principal\": true,\n" +
            "            \"polo\": null,\n" +
            "            \"documento\": {\n" +
            "              \"tipo\": null,\n" +
            "              \"numero\": null\n" +
            "            }\n" +
            "          }\n" +
            "        ],\n" +
            "        \"movimentacoes\": [\n" +
            "          {\n" +
            "            \"id\": 81,\n" +
            "            \"data\": \"25/02/2019\",\n" +
            "            \"conteudo\": \"Carta de Intimação Expedida\\nCarta - Convite - (Exclusivo CEJUSC)\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 82,\n" +
            "            \"data\": \"25/02/2019\",\n" +
            "            \"conteudo\": \"Atermação Expedida\\nTermo de Ajuizamento - CEJUSC A\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 83,\n" +
            "            \"data\": \"22/02/2019\",\n" +
            "            \"conteudo\": \"Designada Audiência de Conciliação\\nConciliação Data: 22/03/2019 Hora 15:30 Local: Sala de audiencias - 1ª vara Civel Situacão: Pendente\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 84,\n" +
            "            \"data\": \"22/02/2019\",\n" +
            "            \"conteudo\": \"Distribuído por Direcionamento (movimentação exclusiva do distribuidor)\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"audiencias\": [\n" +
            "          {\n" +
            "            \"data\": \"22/03/2019\",\n" +
            "            \"audiencia\": \"Conciliação\",\n" +
            "            \"situacao\": \"Pendente\",\n" +
            "            \"numero_pessoas\": 2\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"enviar_callback\": \"SIM\",\n" +
            "  \"status_callback\": \"SUCESSO\",\n" +
            "  \"link_api\": \"https://api.escavador.com/api/v1/async/resultados/40\",\n" +
            "  \"created_at\": {\n" +
            "    \"date\": \"2019-02-26 20:21:17.000000\",\n" +
            "    \"timezone_type\": 3,\n" +
            "    \"timezone\": \"UTC\"\n" +
            "  }\n" +
            "}"

}