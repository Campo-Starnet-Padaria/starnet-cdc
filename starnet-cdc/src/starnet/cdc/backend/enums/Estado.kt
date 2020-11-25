package starnet.cdc.backend.enums

enum class Estado {
    ATIVO, INATIVO, TODOS;
    fun estadoToBoolean(state: Estado):Boolean{
        return state == Estado.ATIVO
    }
}