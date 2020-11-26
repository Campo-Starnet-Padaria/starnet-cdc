/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author falaf
Created on 20/11/2020
 */
package starnet.cdc.backend.enums

enum class Estado {
    ATIVO, INATIVO, TODOS;
    fun estadoToBoolean(state: Estado):Boolean{
        return state == Estado.ATIVO
    }
}