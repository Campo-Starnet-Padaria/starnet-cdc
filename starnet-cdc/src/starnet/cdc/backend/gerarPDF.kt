package starnet.cdc.backend

import com.itextpdf.text.*
import com.itextpdf.text.List
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets
import starnet.cdc.database.bean.clientesFrontEnd
import java.io.FileOutputStream
import java.io.IOException
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table




class gerarPDF {
    fun gerarPDF(clientes:ArrayList<clientesFrontEnd>){
        var documento = Document()

        try {
            PdfWriter.getInstance(documento, FileOutputStream("C:\\Users\\falaf\\Documents\\working\\stanet-cdc\\PDFOla2.pdf"))
            documento.open()
            documento.add(Paragraph("Starnet Clientes com carnê."))
            val table = PdfPTable(9)
            table.widthPercentage = 80F;
            table.addCell("Nome")
            table.addCell("Documento")
            table.addCell("CPF")
            table.addCell("Valor")
            table.addCell("Vencimento")
            table.addCell("Estado")
            table.addCell("Observação")
            table.addCell("Cidade")
            table.addCell("Bairro")


            for ((index, element) in clientes.withIndex()) {
                table.addCell(element.nome)
                table.addCell(element.documento)
                table.addCell(element.cpf)
                table.addCell(element.valor)
                table.addCell(element.vencimento)
                table.addCell(element.estado.toString())
                table.addCell(element.observacao)
                table.addCell(element.cidade!!.nome)
                table.addCell(element.bairro!!.nome)
                if (index > 10) break
            }
            documento.add(table)
        } catch (erro:DocumentException) {
            System.err.println(erro.message)
        } catch(erro: IOException) {
            System.err.println(erro.message)
        } finally {
            documento.close()
        }
    }
}