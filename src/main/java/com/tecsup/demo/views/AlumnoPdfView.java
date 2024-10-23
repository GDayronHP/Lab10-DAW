package com.tecsup.demo.views;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tecsup.demo.domain.entities.Alumno;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("alumno/verAlumno")
public class AlumnoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Alumno> alumnos = (List<Alumno>)model.get("alumnos");

        PdfPTable tabla = new PdfPTable(1);
        tabla.setSpacingAfter(20);

        PdfPCell cell = null;

        cell = new PdfPCell(new Phrase("Lista de Alumnos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(5);
        //tabla2.setWidths(new float [] {3.5f, 1, 1, 1});
        tabla2.addCell("id");
        tabla2.addCell("Nombres");
        tabla2.addCell("Apellidos");
        tabla2.addCell("Fecha de nacimiento");
        tabla2.addCell("Sexo");

        for(Alumno alumno: alumnos) {
            tabla2.addCell(""+alumno.getId());
            tabla2.addCell(""+alumno.getNombres());
            tabla2.addCell(""+alumno.getApellidos());
            tabla2.addCell(""+alumno.getFechaNacimiento());
            tabla2.addCell(""+alumno.getSexo());

        }
        document.add(tabla);
        document.add(tabla2);
    }
}
