package starnet.cdc.backend

import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.io.IOException

class gerarPDF {
    fun gerarPDF(){
        var documento = Document()

        try {
            PdfWriter.getInstance(documento, FileOutputStream("C:\\Users\\falaf\\Desktop\\updateSystem"))
            documento.open()
            documento.add(Paragraph("Gerando o pdf por favor espere."))
        } catch (erro:DocumentException) {
            System.err.println(erro.message)
        } catch(erro: IOException) {
            System.err.println(erro.message)
        } finally {
            documento.close()
        }
    }
}