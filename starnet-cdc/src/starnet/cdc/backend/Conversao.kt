/**
Copyright (C) Felipe Alafy All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.backend

import starnet.cdc.backend.enums.Estado
import starnet.cdc.database.bean.clientes

class Conversao {
    //Conversão de String para Float, usada para o banco de dados
    fun converterValorDoPlano(valor: String):Float{
        var nValor = valor
        nValor = nValor.removeRange(0, 2)
        nValor = nValor.replace(",", ".")
        return nValor.toFloat()
    }

    //Conversão de Float para String, usada no front-end - Sobrecarga da função anterior
    fun converterValorDoPlano(cliente:clientes):String{
        var valorConvertido = cliente.valor.toString()
        valorConvertido.replace(".", ",")
        valorConvertido = "R$ $valorConvertido"
        return valorConvertido
    }
}