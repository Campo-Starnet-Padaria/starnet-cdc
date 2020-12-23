package starnet.cdc.backend

import com.itextpdf.text.*
import com.itextpdf.text.List
import com.itextpdf.text.pdf.PdfWriter
import starnet.cdc.database.bean.clientesFrontEnd
import java.io.FileOutputStream
import java.io.IOException

class gerarPDF {
    fun gerarPDF(clientes:ArrayList<clientesFrontEnd>){
        var documento = Document()

        try {
            PdfWriter.getInstance(documento, FileOutputStream("C:\\Users\\falaf\\Documents\\working\\stanet-cdc\\PDFOla.pdf"))
            documento.open()
            documento.add(Paragraph("Clientes cadastrados:"))
            val list = List()
            val listItem = ListItem

            for (element in clientes) {

            }
        } catch (erro:DocumentException) {
            System.err.println(erro.message)
        } catch(erro: IOException) {
            System.err.println(erro.message)
        } finally {
            documento.close()
        }
    }
}