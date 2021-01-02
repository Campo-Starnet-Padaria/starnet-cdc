package starnet.cdc.database.dao

import starnet.cdc.database.db.mysql
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class versionGetter {
    fun version():String?{
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        return try {
            statement = con.prepareStatement("SELECT * FROM version")
            rs = statement.executeQuery()
            rs.next()
            rs.getString(1)
        } catch (erro:SQLException){
            println("Erro ao adquirir versão mais recente do banco de dados")
            null
        } finally {
            mysql.fecharConexao(con, statement, rs)
        }
    }
}