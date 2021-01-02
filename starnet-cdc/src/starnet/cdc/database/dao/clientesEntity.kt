/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author Felipe Alafy
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

    fun getClientesEntityOfAllCities(estado:Estado):ArrayList<clientes>{
        val clientes = ArrayList<clientes>()
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null
        try{
            if (estado.toString() == "TODOS") {
                statement = con.prepareStatement("SELECT * FROM clientes ORDER BY (nome)")
            } else {
                statement = con.prepareStatement("SELECT * FROM clientes WHERE estado = ? ORDER BY (nome)")
                statement.setBoolean(1, estado.estadoToBoolean(estado))
            }

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
                cliente.cpf = rs.getString("cpf")
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
            mysql.fecharConexao(con, statement, rs)
            return clientes
        }
    }

    fun getClientesEntityOfCity(cidade:String, estado: Estado):ArrayList<clientes>{
        val clientes = ArrayList<clientes>()
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try{
            if (estado.toString() == "TODOS") {
                statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ? ORDER BY (nome)")
                statement.setString(1, cidade)
            } else {
                statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ? AND estado = ? ORDER BY (nome)")
                statement.setString(1, cidade)
                statement.setBoolean(2, estado.estadoToBoolean(estado))
            }

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
                cliente.cpf = rs.getString("cpf")
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
            mysql.fecharConexao(con, statement, rs)
            return clientes
        }
    }

    fun getClientesEntityOfCity(cidade: String, bairro:String, estado:Estado):ArrayList<clientes>{
        val clientes = ArrayList<clientes>()
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try{
            if (estado.toString() == "TODOS") {
                statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ? AND bairro = ? ORDER BY (nome)")
                statement.setString(1, cidade)
                statement.setString(2, bairro)
            } else {
                statement = con.prepareStatement("SELECT * FROM clientes WHERE cidade = ? AND bairro = ? AND estado = ? ORDER BY (nome)")
                statement.setString(1, cidade)
                statement.setString(2, bairro)
                statement.setBoolean(3, estado.estadoToBoolean(estado))
            }
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
                cliente.cpf = rs.getString("cpf")
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
            mysql.fecharConexao(con, statement, rs)
            return clientes
        }
    }

    fun addClientes(cliente: clientes){
        val con:Connection = mysql.conexao();
        var statement:PreparedStatement? = null
        var check = false
        try {
            statement = con.prepareStatement("INSERT INTO clientes (nome, documento, cpf, vencimento, valor, estado, observacao, cidade, bairro)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            statement.setString(1, cliente.nome)
            statement.setString(2, cliente.documento)
            statement.setString(3,cliente.cpf)
            statement.setString(4, cliente.vencimento)
            statement.setFloat(5, cliente.valor!!)
            statement.setBoolean(6, cliente.estado!!.estadoToBoolean(cliente.estado!!))
            statement.setString(7, cliente.observacao)
            statement.setString(8, cliente.cidade?.nome.toString())
            statement.setString(9, cliente.bairro?.nome.toString())
            check = statement.execute()
        } catch (erro:SQLException){
            println(erro)
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente no banco de dados.", "Sem êxito", JOptionPane.ERROR_MESSAGE)
        } finally {
            mysql.fecharConexao(con, statement)
        }
    }

    fun editClientes(cliente:clientes, oldDoc:String):Boolean{
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var check = false
        try {
            statement = con.prepareStatement("UPDATE clientes SET nome = ?, documento = ?, cpf = ? vencimento = ?, " +
                    "valor = ?, estado = ?, observacao = ?, cidade = ?, bairro = ? WHERE (documento = ?)")
            statement.setString(1, cliente.nome)
            statement.setString(2, cliente.documento)
            statement.setString(3, cliente.cpf)
            statement.setString(4, cliente.vencimento)
            statement.setFloat(5, cliente.valor!!)
            statement.setBoolean(6, cliente.estado!!.estadoToBoolean(cliente.estado!!))
            statement.setString(7, cliente.observacao)
            statement.setString(8, cliente.cidade!!.nome.toString())
            statement.setString(9, cliente.bairro!!.nome.toString())
            statement.setString(10,oldDoc)
            check = statement.execute()
        } catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Erro não foi possível atualizar o cliente. Codigo do erro:\n$erro")
        } finally {
            mysql.fecharConexao(con, statement)
            return check
        }
    }

    fun getClientesByCPF(): ArrayList<clientes> {
        val con:Connection = mysql.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null
        val clientes = ArrayList<clientes>()
        var check = false
        try {
            statement = con.prepareStatement("SELECT * FROM clientes WHERE cpf != 'null'")
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
                cliente.cpf = rs.getString("cpf")
                cliente.vencimento = rs.getString("vencimento")
                cliente.valor = rs.getFloat("valor")
                cliente.observacao = rs.getString("observacao")
                cliente.bairro = bairro
                cliente.cidade = cidade
                cliente.estado = state
                clientes.add(cliente)
            }
        } catch (erro:SQLException){
            JOptionPane.showMessageDialog(null, "Não foi possível adquirir, ou não existe\num cliente com esse CPF.\nFavor caso primeira opção contar o Programador.",
            "Erro", JOptionPane.ERROR)
        } finally {
            mysql.fecharConexao(con, statement, rs)
        }
        return clientes
    }
}