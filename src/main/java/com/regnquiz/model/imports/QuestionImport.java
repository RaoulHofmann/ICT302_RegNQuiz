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
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional()
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
                    List<MultipleChoice> mc = new ArrayList<>();

                    question.setDescription(currentRow.getCell(1).getStringCellValue());
                    question.setTime((int) currentRow.getCell(7).getNumericCellValue());
                    Question newQuestion = questionRepository.save(question); // save question to database
                    bookingQuestionRepository.save(new BookingQuestion(bookingRepository.findById(bookingID).get(), newQuestion));

                    if (currentRow.getCell(0).getStringCellValue().equals("TrueFalse")) {
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, "T")));
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, "F")));
                    }else{
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, currentRow.getCell(2).getStringCellValue())));
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, currentRow.getCell(3).getStringCellValue())));
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, currentRow.getCell(4).getStringCellValue())));
                        mc.add(multipleChoiceRepository.save(new MultipleChoice(newQuestion, currentRow.getCell(5).getStringCellValue())));
                    }

                    if (currentRow.getCell(0).getStringCellValue().equals("TrueFalse")) { // true/fase questions
                         if (currentRow.getCell(6).getStringCellValue().equals("T")) {
                             newQuestion.setAnswer(mc.get(0).getMCID());
                         } else if (currentRow.getCell(6).getStringCellValue().equals("F")) {
                             newQuestion.setAnswer(mc.get(1).getMCID());
                         }
                     }else{
                        if (currentRow.getCell(6).getStringCellValue().equals("A1")) {
                            newQuestion.setAnswer(mc.get(0).getMCID());
                        } else if (currentRow.getCell(6).getStringCellValue().equals("A2")) {
                            newQuestion.setAnswer(mc.get(1).getMCID());
                        } else if (currentRow.getCell(6).getStringCellValue().equals("A3")) {
                            newQuestion.setAnswer(mc.get(2).getMCID());
                        } else if (currentRow.getCell(6).getStringCellValue().equals("A4")) {
                            newQuestion.setAnswer(mc.get(3).getMCID());
                        }
                    }
                    questionRepository.save(newQuestion);
                    mc.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

