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
    public static void generatePDFReport2(List<ActivoFijoList2Dto> data, String fileName, String imageUrl, String username, String empresa) {

        Document document = new Document();
        PdfWriter writer = null;
        // Color personalizado en formato RGB (FFC436)
        Color customColor = Color.decode("#FFD384");
        try {
            //imageUrl = "https://rotulosmatesanz.com/wp-content/uploads/2017/09/2000px-Google_G_Logo.svg_.png";
            //username = "Carlos Camargo";
            //empresa = "Google";
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

            writer.setPageEvent(new PageNumberDateImageEventHandler(imageUrl,username)); // Usar el nuevo manejador de eventos

            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 7);
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, new Color(169, 169, 169));

            Paragraph title = new Paragraph("Informe de Activos Fijos", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(80f);
            title.setSpacingAfter(5f);
            document.add(title);

            Paragraph subtitle = new Paragraph("Empresa: "+empresa, subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            Paragraph subtitle2 = new Paragraph("Expresado en BOLIVIANOS - BOLIVIA", subtitleFont);
            subtitle2.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle2);

            PdfPTable table = new PdfPTable(11); // Reducir el número de columnas a 9
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de columna
            String[] headers = {
                    "ID", "Nombre", "Valor", "Fecha de Compra", "Tipo de Activo","Ubicacion","Encargado",
                    "Porcentaje Depreciación", "Depreciación","Valor Actual", "Meses Restantes"
            };

            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(header, headerFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(customColor); // gris para encabezados
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
                table.addCell(createCell(activo.getTipoActivoNombre(), contentFont));
                table.addCell(createCell((activo.getCalle()+"/"+activo.getBloqueNombre()), contentFont));
                table.addCell(createCell(activo.getPersonalNombre(), contentFont));
                table.addCell(createCell(activo.getPorcentajeDepreciacion().toString()+"%", contentFont));
                table.addCell(createCell(activo.getValorDepreciacion().toString(), contentFont));
                table.addCell(createCell(activo.getValorActual().toString(),contentFont));
                table.addCell(createCell(activo.getMesesRestantes().toString(),contentFont));
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
                new ActivoFijoList2Dto(1L, "Activo 1", new BigDecimal(100), LocalDate.now().toString(), "Descripción 1", "Tipo 1", "Marca 1", "Calle 1", "Avenida 1", "Bloque 1", "Ciudad 1", "Personal 1", "Estado 1", "Condición 1", 0, new BigDecimal(10), new BigDecimal(10), new BigDecimal(20)),
                new ActivoFijoList2Dto(2L, "Activo 2", new BigDecimal(200), LocalDate.now().toString(), "Descripción 2", "Tipo 2", "Marca 2", "Calle 2", "Avenida 2", "Bloque 2", "Ciudad 2", "Personal 2", "Estado 2", "Condición 2",0, new BigDecimal(20), new BigDecimal(20), new BigDecimal(20)),
                new ActivoFijoList2Dto(3L, "Activo 3", new BigDecimal(300), LocalDate.now().toString(), "Descripción 3", "Tipo 3", "Marca 3", "Calle 3", "Avenida 3", "Bloque 3", "Ciudad 3", "Personal 3", "Estado 3", "Condición 3",0, new BigDecimal(30), new BigDecimal(30), new BigDecimal(20)));
                //generatePDFReport(data, "C:\\Users\\ccama\\OneDrive\\Escritorio\\reporte.pdf");
    }
    // Clase interna para manejar eventos de numeración y fecha de página
    static class PageNumberDateImageEventHandler extends PdfPageEventHelper {

        private String imageUrl;
        private String username;

        public PageNumberDateImageEventHandler(String imageUrl, String username ) {
            this.imageUrl = imageUrl;
            this.username = username;
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
                image.scaleAbsolute(40, 40); // Ajustar el tamaño de la imagen según sea necesario
                image.setAbsolutePosition(document.left() + 7, document.top() - 35);
                document.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Mostrar la fecha en gris claro con el formato "Fecha: dd/MM/yyyy"
            cb.setColorFill(new Color(169, 169, 169)); // Gris claro
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Fecha: " + getCurrentDate(), document.right() - 10, document.top() - 10, 0);

            // Mostrar la hora con el formato "Hora: HH:mm:ss"
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Hora: " + getCurrentTime(), document.right() - 10, document.top() - 20, 0);

            // Mostrar "Generado" debajo de la hora
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Generado por: "+username, document.right() - 10, document.top() - 30, 0);

            cb.setColorFill(Color.BLACK);
            // Mostrar la numeración de página en la esquina inferior derecha
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page " + writer.getPageNumber() + " of " + totalPages, document.right() - 10, document.bottom() - 10, 0);

            cb.endText();
        }

        private String getCurrentDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(new Date());
        }

        private String getCurrentTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            return sdf.format(new Date());
        }
    }
}
