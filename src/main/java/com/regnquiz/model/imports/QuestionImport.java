package com.regnquiz.model.imports;

import com.regnquiz.model.BookingQuestion;
import com.regnquiz.model.MultipleChoice;
import com.regnquiz.model.Question;
import com.regnquiz.model.repositories.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Raoul Hofmann
 * @comment CSV reader for question class, deposits in database
 */
@Service
public class QuestionImport {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private BookingQuestionRepository bookingQuestionRepository;

    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    @Transactional
    public void ImportQuestion(MultipartFile filename, int bookingID) {
        try { 
            // Open streams and setup xlsx reading
            InputStream is = filename.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next(); // Read first line
            while (iterator.hasNext()) { // while there is data

                Row currentRow = iterator.next();
                if(!currentRow.getCell(0).getStringCellValue().equals("Empty")) {
                    // Deposit data into question object
                    Question question = new Question();

                    question.setDescription(currentRow.getCell(1).getStringCellValue());
                    question.setTime((int) currentRow.getCell(7).getNumericCellValue());
                    if (currentRow.getCell(0).getStringCellValue().equals("TrueFalse")) {
                        question.addMultipleChoice(new MultipleChoice(question, "T"));
                        question.addMultipleChoice(new MultipleChoice(question, "F"));
                    }
                    question.addBookingQuestion(new BookingQuestion(bookingRepository.findById(bookingID).get(), question)); // check for duplicate data
                    Question newq = questionRepository.save(question); // save question to database



                     if (currentRow.getCell(0).getStringCellValue().equals("TrueFalse")) { // true/fase questions
                         if (currentRow.getCell(6).getStringCellValue().equals("T")) {
                             newq.setAnswer(multipleChoiceRepository.findByQuestion_QuestionIDAndDescription(newq.getQID(), "True").getAnswerID());
                         } else if (currentRow.getCell(6).getStringCellValue().equals("F")) {
                             newq.setAnswer(multipleChoiceRepository.findByQuestion_QuestionIDAndDescription(newq.getQID(), "False").getAnswerID());
                         }
                     }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

