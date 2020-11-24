package starnet.cdc.backend

import starnet.cdc.database.bean.clientes

class Conversao {
    //Conversão de String para Float, usada para o banco de dados
    fun converterValorDoPlano(valor: String):Float{
        valor.replace("R$ ", "")
        valor.replace(",", ".")
        return valor.toFloat()
    }

    //Conversão de Float para String, usada no front-end - Sobrecarga da função anterior
    fun converterValorDoPlano(cliente:clientes):String{
        var valorConvertido = cliente.valor.toString()
        valorConvertido.replace(".", ",")
        valorConvertido = "R$ $valorConvertido"
        return valorConvertido
    }
}