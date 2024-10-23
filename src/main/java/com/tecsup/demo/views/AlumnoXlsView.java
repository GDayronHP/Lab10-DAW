package com.tecsup.demo.views;

import com.tecsup.demo.domain.entities.Alumno;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component("alumno/verAlumno.xlsx")
public class AlumnoXlsView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"alumno_view.xlsx\"");
        List<Alumno> alumnos = (List<Alumno>)model.get("alumnos");
        Sheet sheet = workbook.createSheet("Lista de Alumnos");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de alumnos");

        row = sheet.createRow(1);
        cell = row.createCell(0);

        CellStyle theaderStyle = workbook.createCellStyle();
        theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        theaderStyle.setBorderTop(BorderStyle.MEDIUM);
        theaderStyle.setBorderRight(BorderStyle.MEDIUM);
        theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
        theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tbodyStyle = workbook.createCellStyle();
        tbodyStyle.setBorderBottom(BorderStyle.THIN);
        tbodyStyle.setBorderTop(BorderStyle.THIN);
        tbodyStyle.setBorderRight(BorderStyle.THIN);
        tbodyStyle.setBorderLeft(BorderStyle.THIN);

        Row header = sheet.createRow(4);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("Nombres");
        header.createCell(2).setCellValue("Apellidos");
        header.createCell(3).setCellValue("Fecha de nacimiento");
        header.createCell(4).setCellValue("Sexo");

        header.getCell(0).setCellStyle(theaderStyle);
        header.getCell(1).setCellStyle(theaderStyle);
        header.getCell(2).setCellStyle(theaderStyle);
        header.getCell(3).setCellStyle(theaderStyle);
        header.getCell(4).setCellStyle(theaderStyle);


        int rownum = 6;

        for (Alumno alumno : alumnos) {
            Row fila = sheet.createRow(rownum++);

            cell = fila.createCell(0);
            cell.setCellValue(alumno.getId());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(1);
            cell.setCellValue(alumno.getNombres());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(2);
            cell.setCellValue(alumno.getApellidos());
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(3);
            // Convertir LocalDate a String
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            cell.setCellValue(alumno.getFechaNacimiento().format(formatter));
            cell.setCellStyle(tbodyStyle);

            cell = fila.createCell(4);
            cell.setCellValue(alumno.getSexo());
            cell.setCellStyle(tbodyStyle);
        }
    }
}

