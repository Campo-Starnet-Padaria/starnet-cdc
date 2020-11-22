/*
  Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
  @author falaf
  Created on 20/11/2020
*/
package starnet.cdc.backend

import starnet.cdc.database.bean.contaLogada

/*

  @author falaf
  Created on 20/11/2020
*/

class validacao {
    fun validarNome(nome:String) = nome.length in 1..100
    fun validarDocumento(doc:String) = doc.length == 7
    fun validarVencimento(venc:String):Boolean{
        if(!venc.isEmpty() && venc.length == 10){
            val pattern = Regex("[0-9][0-9]/[0-9][1-9]/[0-9][0-9][0-9][0-9]") //Regular Expression to validate a date
            return pattern.containsMatchIn(venc)
        } else {
            return false
        }
    }
    fun validarObs(obs:String) = obs.length <= 60

    fun validarLogin(conta: contaLogada):Boolean{
        var checkNome: Boolean
        var checkSenha: Boolean
        var cs:CharSequence

        //Nome
        cs = conta.login.toString()
        var pattern = Regex("[a-z]{2,50}") //Regular Expression to validate a name
        checkNome = pattern.containsMatchIn(cs)

        //Senha
        cs = conta.pass.toString()
        pattern = Regex("[a-z]([a-z]|[0-9]){7,30}") //Regular Expression to validate a password
        checkSenha = pattern.containsMatchIn(cs)

        return checkNome && checkSenha
    }
}