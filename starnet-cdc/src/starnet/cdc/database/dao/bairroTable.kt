package starnet.cdc.database.dao

import jdk.nashorn.internal.scripts.JO
import starnet.cdc.database.bean.bairro
import starnet.cdc.database.bean.cidade
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.swing.JOptionPane

class bairroTable {
    fun inserirBairro(bairro:bairro){
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        try {
            statement = con.prepareStatement("INSERT INTO bairro (cidade, nome) VALUES (?, ?)")
            statement.setString(1, bairro.cidade!!.nome)
            statement.setString(2, bairro.nome)
            statement.execute();
            JOptionPane.showMessageDialog(null, "O bairro: ${bairro.nome} foi adicionado\nhá " +
                    "cidade: ${bairro.cidade!!.nome}\ncom êxito")
        } catch (erro: SQLException) {
            JOptionPane.showMessageDialog(null, "O bairro: ${bairro.nome} já existe em " +
                    "${bairro.cidade!!.nome} tente colocar II ao final do nome.")
        } finally {
            conexao.fecharConexao(con, statement)
        }
    }

    fun getBairros(cidade: cidade): ArrayList<bairro>{
        val bairros = ArrayList<bairro>()
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        var rs:ResultSet? = null

        try {
            statement = con.prepareStatement("SELECT * FROM bairro WHERE cidade = ?")
            statement.setString(1, cidade.nome.toString());
            rs = statement.executeQuery()
            while(rs.next()){
                val bairro = bairro()
                val cidade = cidade()
                cidade.nome = rs.getString("cidade")
                bairro.cidade = cidade
                bairro.nome = rs.getString("nome")
                bairros.add(bairro)
            }
        } catch (erro: SQLException){
            JOptionPane.showMessageDialog(null, "Erro ao pegar os bairros do banco de dados\n:$erro")
        } finally {
            conexao.fecharConexao(con, statement, rs)
            return bairros
        }
    }
}