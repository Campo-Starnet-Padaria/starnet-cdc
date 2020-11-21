package starnet.cdc.database.dao

import starnet.cdc.backend.Error
import starnet.cdc.database.bean.contaLogada
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class login {
    fun login(conta: contaLogada): contaLogada {
        val contaFinal = contaLogada()
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null
        val errorClass = Error()
        try {
            statement = con.prepareStatement("SELECT * FROM contas WHERE nome = ? and senha = ?")
            statement.setString(1, conta.login)
            statement.setString(2, conta.pass)
            rs = statement.executeQuery()
            if (rs.next()) {
                contaFinal.id = rs.getInt("id")
                contaFinal.login = rs.getString("nome")
                contaFinal.pass = rs.getString("senha")
                contaFinal.acesso = rs.getInt("acesso")
            }
        } catch (error:SQLException){
            errorClass.openError("erro de SQL em ${error.toString()}")
        } finally {
            conexao.fecharConexao(con, statement, rs)
            return contaFinal
        }
    }
}