/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author falaf
Created on 20/11/2020
 */
package starnet.cdc.database.dao

import starnet.cdc.database.bean.estatisticas
import java.sql.Connection
import starnet.cdc.backend.Error
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class estatiscasConsume {
    fun getEstatisticas():estatisticas{
        val es = estatisticas()
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try{
            statement = con.prepareStatement("SELECT count(documento) FROM clientes") //Get Users
            rs = statement.executeQuery()
            rs.next()
            es.clientesTotal = rs.getInt(1)

            statement = con.prepareStatement("SELECT count(documento) FROM clientes WHERE estado = 1") //Get Active Users
            rs = statement.executeQuery()
            rs.next()
            es.clientesAtivos = rs.getInt(1)

            statement = con.prepareStatement("SELECT count(documento) FROM clientes WHERE estado = 0") //Get Inativo Users
            rs = statement.executeQuery()
            rs.next()
            es.clientesInativos = rs.getInt(1)

            statement = con.prepareStatement("SELECT sum(valor) FROM clientes WHERE estado = 1") //Lucro total Usuarios Ativos
            rs = statement.executeQuery()
            rs.next()
            es.lucro = rs.getFloat(1)
        } catch (error:SQLException){
            val erro = Error()
            erro.openError(" adquirir estatisticas:\n$error")
        } finally {
            mysql.fecharConexao(con, statement, rs)
            return es
        }
    }
}