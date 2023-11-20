package com.example.BackendSAF.bl;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.BackendSAF.dto.ActivoFijoList2Dto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

public class PDFReportGenerator {
    public static void generatePDFReport(List<ActivoFijoList2Dto> data, String fileName) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph title = new Paragraph("Informe de Activos Fijos", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(16);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Encabezados de columna
            String[] headers = {
                    "ID", "Nombre", "Valor", "Fecha de Compra", "Descripción", "Tipo de Activo",
                    "Marca", "Calle", "Avenida", "Bloque", "Ciudad", "Personal", "Estado",
                    "Condición", "% Depreciación", "Depreciación"
            };

            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(header, headerFont));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }

            // Datos de activos fijos
            for (ActivoFijoList2Dto activo : data) {
                table.addCell(createCell(activo.getId().toString(), contentFont));
                table.addCell(createCell(activo.getNombre(), contentFont));
                table.addCell(createCell(activo.getValor().toString(), contentFont));
                table.addCell(createCell(activo.getFechaCompra(), contentFont));
                table.addCell(createCell(activo.getDescripcion(), contentFont));
                table.addCell(createCell(activo.getTipoActivoNombre(), contentFont));
                table.addCell(createCell(activo.getMarcaNombre(), contentFont));
                table.addCell(createCell(activo.getCalle(), contentFont));
                table.addCell(createCell(activo.getAvenida(), contentFont));
                table.addCell(createCell(activo.getBloqueNombre(), contentFont));
                table.addCell(createCell(activo.getCiudadNombre(), contentFont));
                table.addCell(createCell(activo.getPersonalNombre(), contentFont));
                table.addCell(createCell(activo.getEstadoNombre(), contentFont));
                table.addCell(createCell(activo.getCondicionNombre(), contentFont));
                table.addCell(createCell(activo.getPorcentajeDepreciacion().toString(), contentFont));
                table.addCell(createCell(activo.getValorDepreciacion().toString(), contentFont));
            }

            document.add(table);
            document.close();

            System.out.println("PDF report generated: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generatePDFReport2(List<ActivoFijoList2Dto> data, String fileName) {
        Document document = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            writer.setPageEvent(new PageNumberEventHandler());
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
    // Clase para manejar eventos de numeración de páginas
    static class PageNumberEventHandler extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            cb.beginText();
            try {
                cb.setFontAndSize(BaseFont.createFont(), 8); // Establecer fuente y tamaño
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page " + writer.getPageNumber(), document.right() - 10, document.bottom() - 10, 0);
            cb.endText();
        }
    }
}
