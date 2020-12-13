package starnet.cdc.database.dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class versionGetter {
    fun version():String{
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try {
            statement = con.prepareStatement("SELECT * FROM version")
            rs = statement.executeQuery()
            rs.next()
            return rs.getString(1)
        } catch (erro:SQLException){
            println("Erro ao adquirir versão mais recente do banco de dados")
            return "null"
        } finally {
            conexao.fecharConexao(con, statement, rs)
        }
    }
}