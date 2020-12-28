package starnet.cdc.backend

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import starnet.cdc.database.bean.clientesFrontEnd
import java.io.FileOutputStream
import java.io.IOException
import com.itextpdf.text.Paragraph
import jdk.nashorn.internal.scripts.JO
import java.awt.Desktop
import java.io.File
import java.nio.file.Path
import javax.swing.JOptionPane


class gerarPDF {
    fun gerarPDF(clientes:ArrayList<clientesFrontEnd>, local:Path){
        val documento = Document()
        documento.pageSize = PageSize.A4.rotate();
        try {
            PdfWriter.getInstance(documento, FileOutputStream("$local\\Clientes.pdf"))
            documento.open()

            val f = Font(Font.FontFamily.COURIER, 20F, Font.BOLD)
            val p1 = Paragraph("Starnet carnês de clientes", f)
            p1.alignment = Element.ALIGN_CENTER
            documento.add(p1)

            val table = PdfPTable(9)
            table.paddingTop = 10F
            table.widthPercentage = 100F;
            val cell = PdfPCell()
            cell.fixedHeight = 20F
            cell.phrase = Phrase("Nome")
            table.addCell(cell)
            cell.phrase = Phrase("Documento")
            table.addCell(cell)
            cell.phrase = Phrase("CPF")
            table.addCell(cell)
            cell.phrase = Phrase("Valor")
            table.addCell(cell)
            cell.phrase = Phrase("Vencimento")
            table.addCell(cell)
            cell.phrase = Phrase("Estado")
            table.addCell(cell)
            cell.phrase = Phrase("Observação")
            table.addCell(cell)
            cell.phrase = Phrase("Cidade")
            table.addCell(cell)
            cell.phrase = Phrase("Bairro")
            table.addCell(cell)

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
            }
            documento.add(table)
            Desktop.getDesktop().open(File("$local\\Clientes.pdf"))
        } catch (erro:DocumentException) {
            System.err.println(erro.message)
            JOptionPane.showMessageDialog(null, erro.message, "Erro", JOptionPane.ERROR_MESSAGE)
        } catch(erro: IOException) {
            System.err.println(erro.message)
            JOptionPane.showMessageDialog(null, erro.message, "Erro", JOptionPane.ERROR_MESSAGE)
        } finally {
            documento.close()
        }
    }
}