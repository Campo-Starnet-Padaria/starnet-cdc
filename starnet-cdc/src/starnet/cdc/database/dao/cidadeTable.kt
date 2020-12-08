/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
Created on 20/11/2020
 */
package starnet.cdc.database.dao

import jdk.nashorn.internal.scripts.JO
import starnet.cdc.database.bean.cidade
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.swing.JOptionPane

class cidadeTable {
    fun inserirCidades(city: cidade){
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        try {
            statement = con.prepareStatement("INSERT INTO cidade VALUES (?)")
            statement.setString(1, city.nome)
            statement.execute();
            JOptionPane.showMessageDialog(null, "A cidade ${city.nome} foi adicionada com êxito")
        }catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Não é possível adicionar cidades com o mesmo nome!",
            "Info", JOptionPane.WARNING_MESSAGE)
        } finally {
            conexao.fecharConexao(con, statement)
        }
    }

    fun getCidade():ArrayList<cidade>{
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null
        val cidades = ArrayList<cidade>()
        try {
            statement = con.prepareStatement("SELECT * FROM cidade")
            rs = statement.executeQuery()
            while(rs.next()){
                var cidade = cidade()
                cidade.nome = rs.getString("nome")
                cidades.add(cidade)
            }
        } catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Não foi possível pegar as cidades do banco de dados!")
        } finally {
            conexao.fecharConexao(con, statement, rs)
            return cidades
        }
    }
}