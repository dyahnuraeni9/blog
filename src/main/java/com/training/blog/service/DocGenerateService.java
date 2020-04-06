package com.training.blog.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.training.blog.model.Blog;

import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocGenerateService {

    public static ByteArrayInputStream writeDoc(Blog blog) throws IOException {
        try(
        XWPFDocument document= new XWPFDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Write the Document in file system
        //FileOutputStream out = new FileOutputStream(new File("src/main/resources/file/blog.docx"));
        ){
        //create paragraph
        XWPFParagraph paragraph = document.createParagraph();
    
        //Set bottom border to paragraph
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
    
        //Set left border to paragraph
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
    
        //Set right border to paragraph
        paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
    
        //Set top border to paragraph
        paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);
    
        XWPFRun run=paragraph.createRun();
        run.setText("Content : "+blog.getContent());
    
        document.write(out);
        return new ByteArrayInputStream(out.toByteArray());
        }
      }
}