/**
Copyright (C) Starnet [2020] - [2030] All Rights Reserved.
@author falaf
Created on 20/11/2020
 */
package starnet.cdc.backend

import javax.swing.JOptionPane

class Error {
    fun openError(comp:String){
        JOptionPane.showMessageDialog(null, "Está faltando alguma informação em $comp", "Erro", JOptionPane.INFORMATION_MESSAGE)
    }
}