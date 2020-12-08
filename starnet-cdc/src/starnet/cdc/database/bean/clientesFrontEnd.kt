/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.database.bean

import starnet.cdc.backend.enums.Estado

class clientesFrontEnd {
    var nome:String? = null
    var documento:String? = null
    var vencimento:String? = null
    var valor:String? = null
    var estado: Estado? = null
    var observacao:String? = null
    var cidade:cidade? = null
    var bairro:bairro? = null
}