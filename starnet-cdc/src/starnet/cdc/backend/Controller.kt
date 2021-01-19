/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.backend

import com.sun.security.ntlm.Client
import starnet.cdc.backend.enums.Estado
import starnet.cdc.database.bean.bairro
import starnet.cdc.database.bean.cidade
import starnet.cdc.database.bean.clientes
import starnet.cdc.database.bean.clientesFrontEnd
import starnet.cdc.database.dao.clientesEntity

class Controller {
    private val Conversao = Conversao()
    fun getClientesDeTodosOsBairros(cidade: cidade, estado:Estado):ArrayList<clientesFrontEnd>{
        val clientesEntity = clientesEntity()
        val encripta = Encripta()
        val Clientes:ArrayList<clientes> = clientesEntity.getClientesEntityOfCity(cidade.nome.toString(), estado)
        val clientesConvertidos = ArrayList<clientesFrontEnd>()
        for (x in 0 until Clientes.size) {
            val cliente = clientesFrontEnd()
            cliente.nome = Clientes[x].nome
            cliente.documento = Clientes[x].documento
            if(Clientes[x].cpf != null){
                cliente.cpf = encripta.descriptografar(Clientes[x].cpf!!)
            } else {
                cliente.cpf = Clientes[x].cpf
            }
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

    fun getClientes(bairro: bairro, estado: Estado):ArrayList<clientesFrontEnd>{
        val clientesEntity = clientesEntity()
        val encripta = Encripta()
        val Clientes:ArrayList<clientes> = clientesEntity.getClientesEntityOfCity(bairro.cidade!!.nome.toString(),
                bairro.nome.toString(), estado)
        val clientesConvertidos = ArrayList<clientesFrontEnd>()
        for (x in 0 until Clientes.size) {
            val cliente = clientesFrontEnd()
            cliente.nome = Clientes[x].nome
            cliente.documento = Clientes[x].documento
            if(Clientes[x].cpf != null){
                cliente.cpf = encripta.descriptografar(Clientes[x].cpf!!)
            } else {
                cliente.cpf = Clientes[x].cpf
            }
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
        val encripta = Encripta()
        val erro = Error()

        //Convertendo um cliente
        cliente.nome = clientesFrontEnd.nome
        cliente.documento = clientesFrontEnd.documento
        cliente.vencimento = clientesFrontEnd.vencimento
        cliente.valor = Conversao.converterValorDoPlano(clientesFrontEnd.valor!!)
        cliente.estado = clientesFrontEnd.estado
        cliente.observacao = clientesFrontEnd.observacao
        cliente.cidade = clientesFrontEnd.cidade
        cliente.bairro = clientesFrontEnd.bairro

        if (clientesFrontEnd.cpf!!.isNotEmpty()) {
            if (vali.validarCPF(clientesFrontEnd.cpf.toString())) {
                cliente.cpf = encripta.encriptar2(clientesFrontEnd.cpf.toString())
            } else {
                erro.openError("CPF")
            }
        }

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
        val encripta = Encripta()

        //Convertendo um Cliente
        cliente.nome = clientesFrontEnd.nome
        cliente.documento = clientesFrontEnd.documento
        cliente.vencimento = clientesFrontEnd.vencimento
        cliente.valor = Conversao.converterValorDoPlano(clientesFrontEnd.valor!!)
        cliente.observacao = clientesFrontEnd.observacao
        cliente.estado = clientesFrontEnd.estado
        cliente.cidade = clientesFrontEnd.cidade
        cliente.bairro = clientesFrontEnd.bairro

        if (clientesFrontEnd.cpf!!.isNotEmpty()) {
            if (vali.validarCPF(clientesFrontEnd.cpf.toString())) {
                cliente.cpf = encripta.encriptar2(clientesFrontEnd.cpf.toString())
            }
        }

        //Validando
        val check = vali.check(cliente)
        if (check) {
            //Alterando o cliente
            clientesEntity.editClientes(cliente, oldDoc)
        }
    }

    fun getClientesDeTodasAsCidades(estado: Estado): ArrayList<clientesFrontEnd> {
        val clientesEntity = clientesEntity()
        val encripta = Encripta()
        val Clientes:ArrayList<clientes> = clientesEntity.getClientesEntityOfAllCities(estado)
        val clientesConvertidos = ArrayList<clientesFrontEnd>()
        for (x in 0 until Clientes.size) {
            val cliente = clientesFrontEnd()
            cliente.nome = Clientes[x].nome
            cliente.documento = Clientes[x].documento
            if(Clientes[x].cpf != null){
                cliente.cpf = encripta.descriptografar(Clientes[x].cpf!!)
            } else {
                cliente.cpf = Clientes[x].cpf
            }
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

    fun getClientByCPF(cpf:String): ArrayList<clientesFrontEnd> {
        val clientesEntity = clientesEntity()
        val encripta = Encripta()
        val Clientes: ArrayList<clientes> = clientesEntity.getClientesByCPF()
        val conversao = Conversao()
        val clienteConvertido = ArrayList<clientesFrontEnd>()

        for (elemento in Clientes) {
            if (cpf == encripta.descriptografar(elemento.cpf!!)) {
                val cliente = clientesFrontEnd()
                cliente.nome = elemento.nome
                cliente.documento = elemento.documento
                cliente.cpf = encripta.descriptografar(elemento.cpf!!)
                cliente.vencimento = elemento.vencimento
                cliente.valor = conversao.converterValorDoPlano(elemento)
                cliente.estado = elemento.estado
                cliente.cidade = elemento.cidade
                cliente.bairro = elemento.bairro
                cliente.observacao = elemento.observacao
                clienteConvertido.add(cliente)
                break
            }
        }
        return clienteConvertido
    }
}