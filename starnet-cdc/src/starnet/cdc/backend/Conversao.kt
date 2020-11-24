package starnet.cdc.backend

class Conversao {
    //Conversão de String para Float, usada para o banco de dados
    fun converterValorDoPlano(valor: String):Float{
        valor.replace("R$ ", "")
        valor.replace(",", ".")
        return valor.toFloat()
    }

    //Conversão de Float para String, usada no front-end - Sobrecarga da função anterior
    fun converterValorDoPlano(valor:Float):String{
        var valorConvertido = valor.toString()
        valorConvertido.replace(".", ",")
        valorConvertido = "R$ $valorConvertido";
        return valorConvertido
    }
}