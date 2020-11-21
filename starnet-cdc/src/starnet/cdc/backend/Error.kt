package starnet.cdc.backend

import javax.swing.JOptionPane

class Error {
    fun openError(comp:String){
        JOptionPane.showMessageDialog(null, "Está faltando alguma informação em $comp", "Erro", JOptionPane.INFORMATION_MESSAGE)
    }
}