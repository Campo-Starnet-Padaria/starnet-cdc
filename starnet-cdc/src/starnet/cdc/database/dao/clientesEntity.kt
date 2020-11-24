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
}