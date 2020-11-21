/*
  Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
  @author falaf
  Created on 20/11/2020
*/
package starnet.cdc.backend

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
}