package starnet.cdc.backend

import starnet.cdc.database.bean.bairro
import starnet.cdc.database.bean.cidade
import starnet.cdc.database.bean.clientes
import starnet.cdc.database.bean.clientesFrontEnd
import starnet.cdc.database.dao.clientesEntity

class Controller {
    private val Conversao = Conversao()
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

    fun inserirCliente(clientesFrontEnd: clientesFrontEnd){
        val cliente = clientes()
        val clientesEntity = clientesEntity()
        val vali = validacao()

        //Convertendo um cliente
        cliente.nome = clientesFrontEnd.nome
        cliente.documento = clientesFrontEnd.documento
        cliente.vencimento = clientesFrontEnd.vencimento
        cliente.valor = Conversao.converterValorDoPlano(clientesFrontEnd.valor!!)
        cliente.estado = clientesFrontEnd.estado
        cliente.observacao = clientesFrontEnd.observacao
        cliente.cidade = clientesFrontEnd.cidade
        cliente.bairro = clientesFrontEnd.bairro

        //Validando
        val check = vali.check(cliente)
        if (check) {
            clientesEntity.addClientes(cliente)
        }
    }

    fun alterarCliente(clientesFrontEnd: clientesFrontEnd, oldDoc:String){
        val cliente = clientes()
        val clientesEntity = clientesEntity()
        val vali = validacao()

        //Convertendo um Cliente
        cliente.nome = clientesFrontEnd.nome
        cliente.documento = clientesFrontEnd.documento
        cliente.vencimento = clientesFrontEnd.vencimento
        cliente.valor = Conversao.converterValorDoPlano(clientesFrontEnd.valor!!)
        cliente.estado = clientesFrontEnd.estado
        cliente.cidade = clientesFrontEnd.cidade
        cliente.bairro = clientesFrontEnd.bairro

        //Validando
        val check = vali.check(cliente)
        if (check) {
            //Alterando o cliente
            clientesEntity.editClientes(cliente, oldDoc)
        }
    }
}