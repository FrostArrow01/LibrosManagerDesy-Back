package com.example.Ejercicio1ApiLibros.view;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class LibrosExcelView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"Libros.xls\"");
        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Libros");
        sheet.setDefaultColumnWidth(30);
        Font fuenteCabecera = workbook.createFont();
        fuenteCabecera.setFontName("Verdana");
        fuenteCabecera.setFontHeightInPoints((short) 8);
        fuenteCabecera.setBold(true);
        fuenteCabecera.setColor(IndexedColors.BLACK.getIndex());
        CellStyle styleCabecera = workbook.createCellStyle();
        styleCabecera.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        styleCabecera.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleCabecera.setFont(fuenteCabecera);
        // create header row
        Row header = sheet.createRow(0);
        String[] titulos = { 
                "Titulo", "Edicion", "Nombre Autor", "Categoria"
                };
        for (int i = 0; i < titulos.length; i++) {
            header.createCell(i).setCellValue(titulos[i]);
            header.getCell(i).setCellStyle(styleCabecera);
        }
        // Si no eliminamos este error, no puede ordenar bien y da error de parseo
        model.remove("org.springframework.validation.BindingResult.libro");
        Set<String> s = model.keySet();
        String[] libro = new String[s.size()];
        libro = (String[]) s.toArray(libro);
        for (int j = 0; j < libro.length; j++) {
            String articulo = libro[j];
            try {
                String[] valores = ((String[]) (model.get(articulo)));
                Row row = sheet.createRow(j);
                for (int i = 0; i < valores.length; i++) {
                    //row.createCell(i).setCellValue();
                    String campo = valores[i];
                    try {
                        Double d = Double.parseDouble(campo);
                        row.createCell(i).setCellValue(d);
                    } catch (Exception e) {
                        row.createCell(i).setCellValue(campo);
                    }
                }
            } catch (Exception e) {
//                log.error("Se ha producido un error al generar el fichero de guardias " + e.getMessage());
            }
        }
	}

}
