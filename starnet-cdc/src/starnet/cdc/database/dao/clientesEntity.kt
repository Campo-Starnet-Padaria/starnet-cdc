/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author falaf
Created on 20/11/2020
 */
package starnet.cdc.database.dao

import starnet.cdc.backend.enums.Estado
import starnet.cdc.database.bean.bairro
import starnet.cdc.database.bean.cidade
import starnet.cdc.database.bean.clientes
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.swing.JOptionPane

class clientesEntity {
    fun getClientesEnetityOfCity(cidade:String):ArrayList<clientes>{
        val clientes = ArrayList<clientes>()
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try{
            statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ?")
            statement.setString(1, cidade)
            rs = statement.executeQuery()
            while(rs.next()){
                val state:Estado = if (rs.getBoolean("Estado")) {
                    Estado.ATIVO
                } else {
                    Estado.INATIVO
                }

                val cidade = cidade()
                cidade.nome = rs.getString("cidade")

                val bairro = bairro()
                bairro.cidade = cidade
                bairro.nome = rs.getString("bairro")

                val cliente = clientes()
                cliente.nome = rs.getString("nome")
                cliente.documento = rs.getString("documento")
                cliente.vencimento = rs.getString("vencimento")
                cliente.valor = rs.getFloat("valor")
                cliente.observacao = rs.getString("observacao")
                cliente.bairro = bairro
                cliente.cidade = cidade
                cliente.estado = state
                clientes.add(cliente)
            }
        }catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar os clientes\nerro:$erro")
        } finally {
            conexao.fecharConexao(con, statement, rs)
            return clientes
        }
    }

    fun getClientesEnetityOfCity(cidade: String, bairro:String):ArrayList<clientes>{
        val clientes = ArrayList<clientes>()
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try{
            statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ? AND bairro = ?")
            statement.setString(1, cidade)
            statement.setString(2, bairro)
            rs = statement.executeQuery()
            while(rs.next()){
                val state:Estado = if (rs.getBoolean("Estado")) {
                    Estado.ATIVO
                } else {
                    Estado.INATIVO
                }

                val cidade = cidade()
                cidade.nome = rs.getString("cidade")

                val bairro = bairro()
                bairro.cidade = cidade
                bairro.nome = rs.getString("bairro")

                val cliente = clientes()
                cliente.nome = rs.getString("nome")
                cliente.documento = rs.getString("documento")
                cliente.vencimento = rs.getString("vencimento")
                cliente.valor = rs.getFloat("valor")
                cliente.observacao = rs.getString("observacao")
                cliente.bairro = bairro
                cliente.cidade = cidade
                cliente.estado = state
                clientes.add(cliente)
            }
        }catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar os clientes\nerro:$erro")
        } finally {
            conexao.fecharConexao(con, statement, rs)
            return clientes
        }
    }

    fun addClientes(cliente: clientes){
        val con:Connection = conexao.conexao();
        var statement:PreparedStatement? = null
        var check = false


        //Um exemplo de inserção na classe clientes
        // 'Ronsâgela De Souza Rodrigues', '6608755', '01/02/2021', '80.00', 'true', 'Testando no momento', 'Pouso Alegre', 'Árvore Grande'
        try {
            statement = con.prepareStatement("INSERT INTO clientes (nome, documento, vencimento, valor, estado, observacao, cidade, bairro)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
            println("cidade: ${cliente.cidade?.nome}")
            println("cidade: ${cliente.cidade?.nome}, bairro: ${cliente.bairro?.nome}")
            statement.setString(1, cliente.nome)
            statement.setString(2, cliente.documento)
            statement.setString(3, cliente.vencimento)
            statement.setFloat(4, cliente.valor!!)
            statement.setBoolean(5, cliente.estado!!.estadoToBoolean(cliente.estado!!))
            statement.setString(6, cliente.observacao)
            statement.setString(7, cliente.cidade?.nome.toString())
            statement.setString(8, cliente.bairro?.nome.toString())
            check = statement.execute()
        } catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente no banco de dados.", "Sem êxito", JOptionPane.ERROR_MESSAGE)
        } finally {
            conexao.fecharConexao(con, statement)
        }
    }

    fun editClientes(cliente:clientes, oldDoc:String):Boolean{
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var check = false

        try {
            //UPDATE `starnet`.`clientes` SET `nome` = 'Ana Maria Meilheres', `documento` = '6610102', `vencimento` = '10/01/2021',
            // `valor` = '150', `estado` = '0', `observacao` = 'ab', `cidade` = 'Pouso Alegre', `bairro` = 'Árvore Grande'
            // WHERE (`documento` = '6610101');
            statement = con.prepareStatement("UPDATE clientes SET nome = ?, documento = ?, vencimento = ?, valor = ?," +
                    "estado = ?, observacao = ?, cidade = ?, bairro = ? WHERE (documento = ?)")
            statement.setString(1, cliente.nome)
            statement.setString(2, cliente.documento)
            statement.setString(3, cliente.vencimento)
            statement.setFloat(4, cliente.valor!!)
            statement.setBoolean(5, cliente.estado!!.estadoToBoolean(cliente.estado!!))
            statement.setString(6, cliente.observacao)
            statement.setString(7, cliente.cidade!!.nome.toString())
            statement.setString(8, cliente.bairro!!.nome.toString())
            statement.setString(9,oldDoc)
            check = statement.execute()
        } catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Erro não foi possível atualizar o cliente. Codigo do erro:\n$erro")
        } finally {
            conexao.fecharConexao(con, statement)
            return check
        }
    }
}