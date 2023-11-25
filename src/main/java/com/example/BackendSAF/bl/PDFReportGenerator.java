package com.example.BackendSAF.bl;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.BackendSAF.dto.ActivoFijoList2Dto;
import com.example.BackendSAF.entity.Repository.EmpresaRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PDFReportGenerator {
    private static int totalPages;
    public static void generatePDFReport2(List<ActivoFijoList2Dto> data, String fileName, String imageUrl) {
        Document document = new Document();
        PdfWriter writer = null;
        try {
            //String imageUrl = "https://us.123rf.com/450wm/outchill/outchill1712/outchill171204062/91291455-texto-del-ejemplo-escrito-en-el-sello-vintage-de-goma-del-c%C3%ADrculo-simple-verde.jpg";
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

            writer.setPageEvent(new PageNumberDateImageEventHandler(imageUrl)); // Usar el nuevo manejador de eventos

            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 8);

            Paragraph title = new Paragraph("Informe de Activos Fijos", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(9); // Reducir el número de columnas a 9
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de columna
            String[] headers = {
                    "ID", "Nombre", "Valor (Bs.)", "Fecha de Compra", "Tipo de Activo","Ciudad",
                    "Porcentaje Depreciación", "Depreciación","Valor Actual (Bs.)"
            };

            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(header, headerFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(Color.pink); // Fondo gris para encabezados
                headerCell.setBorderWidth(1); // Agregar bordes
                table.addCell(headerCell);
            }
            // Inicializar el contador de páginas
            totalPages = 0;
            // Datos de activos fijos (sin incluir las columnas omitidas)
            for (ActivoFijoList2Dto activo : data) {
                table.addCell(createCell(activo.getId().toString(), contentFont));
                table.addCell(createCell(activo.getNombre(), contentFont));
                table.addCell(createCell(activo.getValor().toString(), contentFont));
                table.addCell(createCell(activo.getFechaCompra(), contentFont));
                //table.addCell(createCell(activo.getDescripcion(), contentFont));
                table.addCell(createCell(activo.getTipoActivoNombre(), contentFont));
                table.addCell(createCell(activo.getCiudadNombre(), contentFont));
                table.addCell(createCell(activo.getPorcentajeDepreciacion().toString(), contentFont));
                table.addCell(createCell(activo.getValorDepreciacion().toString(), contentFont));
                table.addCell(createCell(activo.getValorActual().toString(),contentFont));
            }
            totalPages = writer.getPageNumber();

            document.add(table);
            document.close();

            System.out.println("PDF report generated: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    public static void main(String[] args) {
        List<ActivoFijoList2Dto> data = List.of(
                new ActivoFijoList2Dto(1L, "Activo 1", new BigDecimal(100), LocalDate.now().toString(), "Descripción 1", "Tipo 1", "Marca 1", "Calle 1", "Avenida 1", "Bloque 1", "Ciudad 1", "Personal 1", "Estado 1", "Condición 1", 0, new BigDecimal(10), new BigDecimal(10)),
                new ActivoFijoList2Dto(2L, "Activo 2", new BigDecimal(200), LocalDate.now().toString(), "Descripción 2", "Tipo 2", "Marca 2", "Calle 2", "Avenida 2", "Bloque 2", "Ciudad 2", "Personal 2", "Estado 2", "Condición 2",0, new BigDecimal(20), new BigDecimal(20)),
                new ActivoFijoList2Dto(3L, "Activo 3", new BigDecimal(300), LocalDate.now().toString(), "Descripción 3", "Tipo 3", "Marca 3", "Calle 3", "Avenida 3", "Bloque 3", "Ciudad 3", "Personal 3", "Estado 3", "Condición 3",0, new BigDecimal(30), new BigDecimal(30)));
                //generatePDFReport(data, "C:\\Users\\ccama\\OneDrive\\Escritorio\\reporte.pdf");
    }
    // Clase interna para manejar eventos de numeración y fecha de página
    static class PageNumberDateImageEventHandler extends PdfPageEventHelper {

        private String imageUrl;

        public PageNumberDateImageEventHandler(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            try {
                cb.setFontAndSize(BaseFont.createFont(), 8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Mostrar la imagen en la esquina superior izquierda
            try {
                URL url = new URL(imageUrl);
                Image image = Image.getInstance(url);
                image.scaleAbsolute(45, 45); // Ajustar el tamaño de la imagen según sea necesario
                image.setAbsolutePosition(document.left() + 7, document.top() - 25);
                document.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Mostrar la fecha de creación en la esquina superior derecha
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, getCurrentDate(), document.right() - 10, document.top() - 10, 0);

            // Mostrar la numeración de página en la esquina inferior derecha
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page " + writer.getPageNumber() + " of " + totalPages, document.right() - 10, document.bottom() - 10, 0);

            cb.endText();
        }

        private String getCurrentDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(new Date());
        }
    }
}
