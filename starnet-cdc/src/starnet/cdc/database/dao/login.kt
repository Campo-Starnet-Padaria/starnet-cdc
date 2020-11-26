/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author falaf
Created on 20/11/2020
 */
package starnet.cdc.database.dao

import starnet.cdc.backend.Error
import starnet.cdc.database.bean.contaLogada
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.swing.JOptionPane

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

    fun createUser(conta: contaLogada):Boolean{
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null
        val errorClass = Error()
        var check = false


        try {
            statement = con.prepareStatement("INSERT INTO contas (nome, senha, acesso) VALUES (?, ?, ?)")
            statement.setString(1, conta.login)
            statement.setString(2, conta.pass)
            statement.setInt(3, conta.acesso!!)
            check = statement.execute();
        } catch (erro:SQLException){
            errorClass.openError(" $erro")
        } finally {
            conexao.fecharConexao(con, statement, rs)
        }
        return check
    }
}