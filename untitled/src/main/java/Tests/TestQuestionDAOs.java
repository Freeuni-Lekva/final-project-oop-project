package Tests;

import DAOs.*;
import Model.Answer;
import Model.DatabaseConnector;
import Model.Question;
import junit.framework.TestCase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestQuestionDAOs extends TestCase {

    private StandardTextQuestionDAO standardDao;
    private Question standardTextQuestion1;
    private Question standardTextQuestion2;
    private Answer standardTextAnswer1;
    private Answer standardTextAnswer2;

    private FillTheBlankQuestionDAO fillTheBlankDao;
    private Question fillTheBlankQuestion1;
    private Question fillTheBlankQuestion2;
    private Answer fillTheBlankAnswer1;
    private Answer fillTheBlankAnswer2;

    private MultipleChoiceQuestionDAO multipleChoiceDao;
    private Question multipleChoiceQuestion1;
    private Question multipleChoiceQuestion2;
    private Answer multipleChoiceAnswer11;
    private Answer multipleChoiceAnswer12;
    private Answer multipleChoiceAnswer13;
    private Answer multipleChoiceAnswer14;
    private Answer multipleChoiceAnswer21;
    private Answer multipleChoiceAnswer22;
    private Answer multipleChoiceAnswer23;

    private PictureResponseQuestionDAO pictureResponseDao;
    private Question pictureResponseQuestion1;
    private Question pictureResponseQuestion2;
    private Answer pictureResponseAnswer1;
    private Answer pictureResponseAnswer2;
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        Connection myConn = DatabaseConnector.getConnection();
        standardDao = new StandardTextQuestionDAO(myConn);
        fillTheBlankDao = new FillTheBlankQuestionDAO(myConn);
        multipleChoiceDao = new MultipleChoiceQuestionDAO(myConn);
        pictureResponseDao = new PictureResponseQuestionDAO(myConn);
    }

    public void setStandardUp() throws SQLException {
        standardTextQuestion1 = new Question("What was the name of the first President of Georgia?",
                                             1, "", 1);
        standardTextQuestion2 = new Question("What is the name of the Capital of Georgia?",
                                             1, "", 1);
        standardDao.addQuestion(standardTextQuestion1, 1);
        standardDao.addQuestion(standardTextQuestion2, 1);
        standardTextAnswer1 = new Answer("Zviad Gamsakhurdia", standardTextQuestion1.getId(), true);
        standardTextAnswer2 = new Answer("Tbilisi", standardTextQuestion2.getId(), true);
        ArrayList<Answer> answer1 = new ArrayList<>();
        ArrayList<Answer> answer2 = new ArrayList<>();
        answer1.add(standardTextAnswer1);
        answer2.add(standardTextAnswer2);
        standardDao.addAnswer(answer1, standardTextQuestion1.getId());
        standardDao.addAnswer(answer2, standardTextQuestion2.getId());
    }

    public void setFillTheBlankUp() throws SQLException {
        fillTheBlankQuestion1 = new Question("The famous statue of a woman in Tbilisi is called ______",
                                             1, "", 1);
        fillTheBlankQuestion2 = new Question("20% of Georgia's territory is occupied by _____",
                                             1, "", 1);
        fillTheBlankDao.addQuestion(fillTheBlankQuestion1, 1);
        fillTheBlankDao.addQuestion(fillTheBlankQuestion2, 1);
        fillTheBlankAnswer1 = new Answer("Qartlis Deda", fillTheBlankQuestion1.getId(), true);
        fillTheBlankAnswer2 = new Answer("Russia", fillTheBlankQuestion2.getId(), true);
        ArrayList<Answer> answer1 = new ArrayList<>();
        ArrayList<Answer> answer2 = new ArrayList<>();
        answer1.add(fillTheBlankAnswer1);
        answer2.add(fillTheBlankAnswer2);
        fillTheBlankDao.addAnswer(answer1, fillTheBlankQuestion1.getId());
        fillTheBlankDao.addAnswer(answer2, fillTheBlankQuestion2.getId());
    }

    public void setMultipleChoiceUp() throws SQLException {
        multipleChoiceQuestion1 = new Question("Name the first king of Iberia", 1, "", 4);
        multipleChoiceQuestion2 = new Question("Name the first king of united Georgia", 1, "", 3);
        multipleChoiceDao.addQuestion(multipleChoiceQuestion1, 1);
        multipleChoiceDao.addQuestion(multipleChoiceQuestion2, 1);
        multipleChoiceAnswer11 = new Answer("Pharsman I", multipleChoiceQuestion1.getId(), false);
        multipleChoiceAnswer12 = new Answer("Mirvan I", multipleChoiceQuestion1.getId(), false);
        multipleChoiceAnswer13 = new Answer("Vakhtang Gorgasali", multipleChoiceQuestion1.getId(), false);
        multipleChoiceAnswer14 = new Answer("Pharnavaz I", multipleChoiceQuestion1.getId(), true);
        multipleChoiceAnswer21 = new Answer("Bagrat II", multipleChoiceQuestion2.getId(), false);
        multipleChoiceAnswer22 = new Answer("Bagrat IV", multipleChoiceQuestion2.getId(), false);
        multipleChoiceAnswer23 = new Answer("Bagrat III", multipleChoiceQuestion2.getId(), true);
        ArrayList<Answer> answer1 = new ArrayList<>();
        ArrayList<Answer> answer2 = new ArrayList<>();
        answer1.add(multipleChoiceAnswer11);
        answer1.add(multipleChoiceAnswer12);
        answer1.add(multipleChoiceAnswer13);
        answer1.add(multipleChoiceAnswer14);
        answer2.add(multipleChoiceAnswer21);
        answer2.add(multipleChoiceAnswer22);
        answer2.add(multipleChoiceAnswer23);
        multipleChoiceDao.addAnswer(answer1, multipleChoiceQuestion1.getId());
        multipleChoiceDao.addAnswer(answer2, multipleChoiceQuestion2.getId());
    }

    public void setPictureResponseUp() throws SQLException {
        pictureResponseQuestion1 = new Question("Name an Animal", 1, "roaring-lion-url", 1);
        pictureResponseQuestion2 = new Question("Name a Bird", 1, "flying-eagle-url", 1);
        pictureResponseDao.addQuestion(pictureResponseQuestion1, 1);
        pictureResponseDao.addQuestion(pictureResponseQuestion2,1);
        pictureResponseAnswer1 = new Answer("Lion", pictureResponseQuestion1.getId(), true);
        pictureResponseAnswer2 = new Answer("Eagle", pictureResponseQuestion2.getId(), true);
        ArrayList<Answer> answer1 = new ArrayList<>();
        ArrayList<Answer> answer2 = new ArrayList<>();
        answer1.add(pictureResponseAnswer1);
        answer2.add(pictureResponseAnswer2);
        pictureResponseDao.addAnswer(answer1, pictureResponseQuestion1.getId());
        pictureResponseDao.addAnswer(answer2, pictureResponseQuestion2.getId());
    }

    public void testStandard() throws SQLException {
        setStandardUp();
        Question test1 = standardDao.getQuestion(standardTextQuestion1.getId());
        assertEquals(standardTextQuestion1.getText(), test1.getText());
        assertEquals(standardTextQuestion1.getId(), test1.getId());
        assertEquals(standardTextQuestion1.getQuizId(), test1.getQuizId());
        assertEquals(standardTextQuestion1.getImageUrl(), test1.getImageUrl());
        assertEquals(standardTextQuestion1.getNumOfAnswers(), test1.getNumOfAnswers());
        Question test2 = standardDao.getQuestion(standardTextQuestion2.getId());
        assertEquals(standardTextQuestion2.getText(), test2.getText());
        assertEquals(standardTextQuestion2.getId(), test2.getId());
        assertEquals(standardTextQuestion2.getQuizId(), test2.getQuizId());
        assertEquals(standardTextQuestion2.getImageUrl(), test2.getImageUrl());
        assertEquals(standardTextQuestion2.getNumOfAnswers(), test2.getNumOfAnswers());
        List<Answer> ans1 = standardDao.getAnswer(standardTextQuestion1.getId());
        assertEquals(standardTextAnswer1.getQuestionId(), ans1.get(0).getQuestionId());
        assertEquals(standardTextAnswer1.getText(), ans1.get(0).getText());
        assertEquals(standardTextAnswer1.getId(), ans1.get(0).getId());
        List<Answer> ans2 = standardDao.getAnswer(standardTextQuestion2.getId());
        assertEquals(standardTextAnswer2.getQuestionId(), ans2.get(0).getQuestionId());
        assertEquals(standardTextAnswer2.getText(), ans2.get(0).getText());
        assertEquals(standardTextAnswer2.getId(), ans2.get(0).getId());
        List<Question> allQuestions = standardDao.getAllQuestions(standardTextQuestion1.getQuizId());
        assertEquals(2, allQuestions.size());
        assertEquals(standardTextQuestion1.getText(), allQuestions.get(0).getText());
        assertEquals(standardTextQuestion2.getText(), allQuestions.get(1).getText());
    }

    public void testFillTheBlank() throws SQLException {
        setFillTheBlankUp();
        Question test1 = fillTheBlankDao.getQuestion(fillTheBlankQuestion1.getId());
        assertEquals(fillTheBlankQuestion1.getText(), test1.getText());
        assertEquals(fillTheBlankQuestion1.getId(), test1.getId());
        assertEquals(fillTheBlankQuestion1.getQuizId(), test1.getQuizId());
        assertEquals(fillTheBlankQuestion1.getImageUrl(), test1.getImageUrl());
        assertEquals(fillTheBlankQuestion1.getNumOfAnswers(), test1.getNumOfAnswers());
        Question test2 = fillTheBlankDao.getQuestion(fillTheBlankQuestion2.getId());
        assertEquals(fillTheBlankQuestion2.getText(), test2.getText());
        assertEquals(fillTheBlankQuestion2.getId(), test2.getId());
        assertEquals(fillTheBlankQuestion2.getQuizId(), test2.getQuizId());
        assertEquals(fillTheBlankQuestion2.getImageUrl(), test2.getImageUrl());
        assertEquals(fillTheBlankQuestion2.getNumOfAnswers(), test2.getNumOfAnswers());
        List<Answer> ans1 = fillTheBlankDao.getAnswer(fillTheBlankQuestion1.getId());
        assertEquals(fillTheBlankAnswer1.getQuestionId(), ans1.get(0).getQuestionId());
        assertEquals(fillTheBlankAnswer1.getText(), ans1.get(0).getText());
        assertEquals(fillTheBlankAnswer1.getId(), ans1.get(0).getId());
        List<Answer> ans2 = fillTheBlankDao.getAnswer(fillTheBlankQuestion2.getId());
        assertEquals(fillTheBlankAnswer2.getQuestionId(), ans2.get(0).getQuestionId());
        assertEquals(fillTheBlankAnswer2.getText(), ans2.get(0).getText());
        assertEquals(fillTheBlankAnswer2.getId(), ans2.get(0).getId());
        List<Question> allQuestions = fillTheBlankDao.getAllQuestions(fillTheBlankQuestion1.getQuizId());
        assertEquals(2, allQuestions.size());
        assertEquals(fillTheBlankQuestion1.getText(), allQuestions.get(0).getText());
        assertEquals(fillTheBlankQuestion2.getText(), allQuestions.get(1).getText());
    }

    public void testMultipleChoice() throws SQLException {
        setMultipleChoiceUp();
        Question test1 = multipleChoiceDao.getQuestion(multipleChoiceQuestion1.getId());
        assertEquals(multipleChoiceQuestion1.getText(), test1.getText());
        assertEquals(multipleChoiceQuestion1.getId(), test1.getId());
        assertEquals(multipleChoiceQuestion1.getQuizId(), test1.getQuizId());
        assertEquals(multipleChoiceQuestion1.getImageUrl(), test1.getImageUrl());
        assertEquals(multipleChoiceQuestion1.getNumOfAnswers(), test1.getNumOfAnswers());
        Question test2 = multipleChoiceDao.getQuestion(multipleChoiceQuestion2.getId());
        assertEquals(multipleChoiceQuestion2.getText(), test2.getText());
        assertEquals(multipleChoiceQuestion2.getId(), test2.getId());
        assertEquals(multipleChoiceQuestion2.getQuizId(), test2.getQuizId());
        assertEquals(multipleChoiceQuestion2.getImageUrl(), test2.getImageUrl());
        assertEquals(multipleChoiceQuestion2.getNumOfAnswers(), test2.getNumOfAnswers());
        List<Answer> ans1 = multipleChoiceDao.getAnswer(multipleChoiceQuestion1.getId());
        assertEquals(4, ans1.size());
        assertEquals(multipleChoiceAnswer11.getQuestionId(), ans1.get(0).getQuestionId());
        assertEquals(multipleChoiceAnswer11.getText(), ans1.get(0).getText());
        assertEquals(multipleChoiceAnswer11.getId(), ans1.get(0).getId());
        assertEquals(multipleChoiceAnswer11.isCorrect(), ans1.get(0).isCorrect());
        assertEquals(multipleChoiceAnswer12.getQuestionId(), ans1.get(1).getQuestionId());
        assertEquals(multipleChoiceAnswer12.getText(), ans1.get(1).getText());
        assertEquals(multipleChoiceAnswer12.getId(), ans1.get(1).getId());
        assertEquals(multipleChoiceAnswer12.isCorrect(), ans1.get(1).isCorrect());
        assertEquals(multipleChoiceAnswer13.getQuestionId(), ans1.get(2).getQuestionId());
        assertEquals(multipleChoiceAnswer13.getText(), ans1.get(2).getText());
        assertEquals(multipleChoiceAnswer13.getId(), ans1.get(2).getId());
        assertEquals(multipleChoiceAnswer13.isCorrect(), ans1.get(2).isCorrect());
        assertEquals(multipleChoiceAnswer14.getQuestionId(), ans1.get(3).getQuestionId());
        assertEquals(multipleChoiceAnswer14.getText(), ans1.get(3).getText());
        assertEquals(multipleChoiceAnswer14.getId(), ans1.get(3).getId());
        assertEquals(multipleChoiceAnswer14.isCorrect(), ans1.get(3).isCorrect());
        List<Answer> ans2 = multipleChoiceDao.getAnswer(multipleChoiceQuestion2.getId());
        assertEquals(3, ans2.size());
        assertEquals(multipleChoiceAnswer21.getQuestionId(), ans2.get(0).getQuestionId());
        assertEquals(multipleChoiceAnswer21.getText(), ans2.get(0).getText());
        assertEquals(multipleChoiceAnswer21.getId(), ans2.get(0).getId());
        assertEquals(multipleChoiceAnswer21.isCorrect(), ans2.get(0).isCorrect());
        assertEquals(multipleChoiceAnswer22.getQuestionId(), ans2.get(1).getQuestionId());
        assertEquals(multipleChoiceAnswer22.getText(), ans2.get(1).getText());
        assertEquals(multipleChoiceAnswer22.getId(), ans2.get(1).getId());
        assertEquals(multipleChoiceAnswer22.isCorrect(), ans2.get(1).isCorrect());
        assertEquals(multipleChoiceAnswer23.getQuestionId(), ans2.get(2).getQuestionId());
        assertEquals(multipleChoiceAnswer23.getText(), ans2.get(2).getText());
        assertEquals(multipleChoiceAnswer23.getId(), ans2.get(2).getId());
        assertEquals(multipleChoiceAnswer23.isCorrect(), ans2.get(2).isCorrect());
        List<Question> allQuestions = multipleChoiceDao.getAllQuestions(multipleChoiceQuestion1.getQuizId());
        assertEquals(2, allQuestions.size());
        assertEquals(multipleChoiceQuestion1.getText(), allQuestions.get(0).getText());
        assertEquals(multipleChoiceQuestion2.getText(), allQuestions.get(1).getText());
    }

    public void testPictureResponse() throws SQLException {
        setPictureResponseUp();
        Question test1 = pictureResponseDao.getQuestion(pictureResponseQuestion1.getId());
        assertEquals(pictureResponseQuestion1.getText(), test1.getText());
        assertEquals(pictureResponseQuestion1.getId(), test1.getId());
        assertEquals(pictureResponseQuestion1.getQuizId(), test1.getQuizId());
        assertEquals(pictureResponseQuestion1.getImageUrl(), test1.getImageUrl());
        assertEquals(pictureResponseQuestion1.getNumOfAnswers(), test1.getNumOfAnswers());
        Question test2 = pictureResponseDao.getQuestion(pictureResponseQuestion2.getId());
        assertEquals(pictureResponseQuestion2.getText(), test2.getText());
        assertEquals(pictureResponseQuestion2.getId(), test2.getId());
        assertEquals(pictureResponseQuestion2.getQuizId(), test2.getQuizId());
        assertEquals(pictureResponseQuestion2.getImageUrl(), test2.getImageUrl());
        assertEquals(pictureResponseQuestion2.getNumOfAnswers(), test2.getNumOfAnswers());
        List<Answer> ans1 = pictureResponseDao.getAnswer(pictureResponseQuestion1.getId());
        assertEquals(pictureResponseAnswer1.getQuestionId(), ans1.get(0).getQuestionId());
        assertEquals(pictureResponseAnswer1.getText(), ans1.get(0).getText());
        assertEquals(pictureResponseAnswer1.getId(), ans1.get(0).getId());
        List<Answer> ans2 = pictureResponseDao.getAnswer(pictureResponseQuestion2.getId());
        assertEquals(pictureResponseAnswer2.getQuestionId(), ans2.get(0).getQuestionId());
        assertEquals(pictureResponseAnswer2.getText(), ans2.get(0).getText());
        assertEquals(pictureResponseAnswer2.getId(), ans2.get(0).getId());
        List<Question> allQuestions = pictureResponseDao.getAllQuestions(pictureResponseQuestion1.getQuizId());
        assertEquals(2, allQuestions.size());
        assertEquals(pictureResponseQuestion1.getText(), allQuestions.get(0).getText());
        assertEquals(pictureResponseQuestion2.getText(), allQuestions.get(1).getText());
    }
}
