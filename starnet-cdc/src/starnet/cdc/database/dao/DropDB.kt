/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.database.dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import starnet.cdc.backend.Error

class DropDB {
    fun DropDB(){
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null

        try{
            statement = con.prepareStatement("DROP DATABASE starnet")
            statement.execute();
        } catch (error:SQLException){
            val Error = Error()
            Error.openError("Não foi possível excluir o banco!")
        } finally {
            mysql.fecharConexao(con, statement)
        }
    }
}