package starnet.cdc.backend

import starnet.cdc.database.bean.bairro
import starnet.cdc.database.bean.cidade
import starnet.cdc.database.bean.clientes
import starnet.cdc.database.bean.clientesFrontEnd
import starnet.cdc.database.dao.clientesEntity

class Transmissao {
    val Conversao = Conversao()
    fun getClientesDeTodosOsBairros(cidade: cidade):ArrayList<clientesFrontEnd>{
        val clientesEntity = clientesEntity()
        val Clientes:ArrayList<clientes> = clientesEntity.getClientesEnetityOfCity(cidade.nome.toString())
        val clientesConvertidos = ArrayList<clientesFrontEnd>()
        for (x in 0 until Clientes.size) {
            val cliente = clientesFrontEnd()
            cliente.nome = Clientes[x].nome
            cliente.documento = Clientes[x].documento
            cliente.vencimento = Clientes[x].vencimento
            cliente.valor = Conversao.converterValorDoPlano(Clientes[x])
            cliente.estado = Clientes[x].estado
            cliente.observacao = Clientes[x].observacao
            cliente.cidade = Clientes[x].cidade
            cliente.bairro = Clientes[x].bairro
            clientesConvertidos.add(cliente)
        }
        return clientesConvertidos
    }

    fun getClientes(bairro: bairro):ArrayList<clientesFrontEnd>{
        val clientesEntity = clientesEntity()
        val Clientes:ArrayList<clientes> = clientesEntity.getClientesEnetityOfCity(bairro.cidade!!.nome.toString(),
                bairro.nome.toString())
        val clientesConvertidos = ArrayList<clientesFrontEnd>()
        for (x in 0 until Clientes.size) {
            val cliente = clientesFrontEnd()
            cliente.nome = Clientes[x].nome
            cliente.documento = Clientes[x].documento
            cliente.vencimento = Clientes[x].vencimento
            cliente.valor = Conversao.converterValorDoPlano(Clientes[x])
            cliente.estado = Clientes[x].estado
            cliente.observacao = Clientes[x].observacao
            cliente.cidade = Clientes[x].cidade
            cliente.bairro = Clientes[x].bairro
            clientesConvertidos.add(cliente)
        }
        return clientesConvertidos
    }
}