package starnet.cdc.database.dao

import jdk.nashorn.internal.scripts.JO
import starnet.cdc.database.bean.bairro
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import javax.swing.JOptionPane

class bairroTable {
    fun inserirBairro(bairro:bairro){
        val con:Connection = conexao.conexao()
        var statement:PreparedStatement? = null
        try {
            statement = con.prepareStatement("INSERT INTO bairro (cidade, nome) VALUES (?, ?)")
            statement.setString(1, bairro.cidade.toString())
            statement.setString(2, bairro.nome.toString())
            statement.execute();
            JOptionPane.showMessageDialog(null, "O bairro: ${bairro.nome} foi adicionado\nhá " +
                    "cidade: ${bairro.cidade}\ncom êxito")
        } catch (erro: SQLException) {
            JOptionPane.showMessageDialog(null, "O bairro: ${bairro.nome} já existe em " +
                    "${bairro.cidade} tente colocar II ao final do nome.")
        } finally {
            conexao.fecharConexao(con, statement)
        }
    }
}