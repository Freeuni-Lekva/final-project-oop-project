package Quiz.Tests;

import Quiz.DAOs.QuizDAO;
import Quiz.DAOs.StandardTextQuestionDAO;
import Quiz.Model.Answer;
import Quiz.Model.DatabaseConnector;
import Quiz.Model.Question;
import Quiz.Model.Quiz;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestQuizDAO extends TestCase {

    private QuizDAO quizDao;
    private Quiz quiz1;
    private Quiz quiz2;
    private Quiz quiz3;
    private Quiz quiz4;
    private Question standardTextQuestion1;
    private Question standardTextQuestion2;
    private Question standardTextQuestion3;

    public void setUp() throws SQLException, ClassNotFoundException {
        Connection myConn = DatabaseConnector.getConnection();
        quizDao = new QuizDAO(myConn);
        quiz1 = new Quiz("Quiz#1", 1, false, false,
                         false, false);
        quizDao.addQuiz(quiz1);
        quiz2 = new Quiz("Quiz#2", 1, false, false,
                         false, false);
        quizDao.addQuiz(quiz2);
        quiz3 = new Quiz("Quiz#3", 1, false, false,
                         false, false);
        quizDao.addQuiz(quiz3);
        quiz4 = new Quiz("Quiz#4", 2, false, false,
                         false, false);
        quizDao.addQuiz(quiz4);
        StandardTextQuestionDAO standardDao = new StandardTextQuestionDAO(myConn);
        standardTextQuestion1 = new Question("What was the name of the first President of Georgia?",
                1, "", 1);
        standardTextQuestion2 = new Question("What is the name of the Capital of Georgia?",
                1, "", 1);
        standardTextQuestion3 = new Question("Name the Founder of Tbilisi", 1, "", 1);
        standardDao.addQuestion(standardTextQuestion1, 1);
        standardDao.addQuestion(standardTextQuestion2, 1);
        standardDao.addQuestion(standardTextQuestion3, 1);
        Answer standardTextAnswer1 = new Answer("Zviad Gamsakhurdia", standardTextQuestion1.getId(), true);
        Answer standardTextAnswer2 = new Answer("Tbilisi", standardTextQuestion2.getId(), true);
        Answer standardTextAnswer3 = new Answer("Vakhtang Gorgasali", standardTextQuestion2.getId(), true);
        ArrayList<Answer> answer1 = new ArrayList<>();
        ArrayList<Answer> answer2 = new ArrayList<>();
        ArrayList<Answer> answer3 = new ArrayList<>();
        answer1.add(standardTextAnswer1);
        answer2.add(standardTextAnswer2);
        answer3.add(standardTextAnswer3);
        standardDao.addAnswer(answer1, standardTextQuestion1.getId());
        standardDao.addAnswer(answer2, standardTextQuestion2.getId());
        standardDao.addAnswer(answer3, standardTextQuestion3.getId());
        List<Question> questions = new ArrayList<>();
        questions.add(standardTextQuestion1);
        questions.add(standardTextQuestion2);
        questions.add(standardTextQuestion3);
        quiz1.setQuestions(questions);
    }

    public void testQuiz() throws SQLException {
        Quiz temp1 = quizDao.getQuiz(quiz1.getId());
        assertEquals(quiz1.getId(), temp1.getId());
        assertEquals(quiz1.getCreatorUserId(), temp1.getCreatorUserId());
        assertEquals(quiz1.getTitle(), temp1.getTitle());
        assertEquals(quiz1.isRandomQuestions(), temp1.isRandomQuestions());
        assertEquals(quiz1.isOnePage(), temp1.isOnePage());
        assertEquals(quiz1.isPracticeMode(), temp1.isPracticeMode());
        assertEquals(quiz1.isImmediateFeedback(), temp1.isImmediateFeedback());
        Quiz temp2 = quizDao.getQuiz(quiz2.getId());
        assertEquals(quiz2.getId(), temp2.getId());
        assertEquals(quiz2.getCreatorUserId(), temp2.getCreatorUserId());
        assertEquals(quiz2.getTitle(), temp2.getTitle());
        assertEquals(quiz2.isRandomQuestions(), temp2.isRandomQuestions());
        assertEquals(quiz2.isOnePage(), temp2.isOnePage());
        assertEquals(quiz2.isPracticeMode(), temp2.isPracticeMode());
        assertEquals(quiz2.isImmediateFeedback(), temp2.isImmediateFeedback());
        Quiz temp3 = quizDao.getQuiz(quiz3.getId());
        assertEquals(quiz3.getId(), temp3.getId());
        assertEquals(quiz3.getCreatorUserId(), temp3.getCreatorUserId());
        assertEquals(quiz3.getTitle(), temp3.getTitle());
        assertEquals(quiz3.isRandomQuestions(), temp3.isRandomQuestions());
        assertEquals(quiz3.isOnePage(), temp3.isOnePage());
        assertEquals(quiz3.isPracticeMode(), temp3.isPracticeMode());
        assertEquals(quiz3.isImmediateFeedback(), temp3.isImmediateFeedback());
        Quiz temp4 = quizDao.getQuiz(quiz4.getId());
        assertEquals(quiz4.getId(), temp4.getId());
        assertEquals(quiz4.getCreatorUserId(), temp4.getCreatorUserId());
        assertEquals(quiz4.getTitle(), temp4.getTitle());
        assertEquals(quiz4.isRandomQuestions(), temp4.isRandomQuestions());
        assertEquals(quiz4.isOnePage(), temp4.isOnePage());
        assertEquals(quiz4.isPracticeMode(), temp4.isPracticeMode());
        assertEquals(quiz4.isImmediateFeedback(), temp4.isImmediateFeedback());
        List<Question> questions = quizDao.getQuizQuestions(quiz1.getId());
        assertEquals(standardTextQuestion1.getId(), questions.get(0).getId());
        assertEquals(standardTextQuestion1.getText(), questions.get(0).getText());
        assertEquals(standardTextQuestion2.getId(), questions.get(1).getId());
        assertEquals(standardTextQuestion2.getText(), questions.get(1).getText());
        assertEquals(standardTextQuestion3.getId(), questions.get(2).getId());
        assertEquals(standardTextQuestion3.getText(), questions.get(2).getText());
        List<Quiz> quizzes = quizDao.getCreatedQuizzes(1);
        assertEquals(3, quizzes.size());
        assertEquals(quiz1.getTitle(), quizzes.get(0).getTitle());
        assertEquals(quiz2.getTitle(), quizzes.get(1).getTitle());
        assertEquals(quiz3.getTitle(), quizzes.get(2).getTitle());
        List<Quiz> allQuizzes = quizDao.getAllQuizzes();
        assertEquals(4, allQuizzes.size());
        assertEquals(quiz1.getTitle(), allQuizzes.get(0).getTitle());
        assertEquals(quiz2.getTitle(), allQuizzes.get(1).getTitle());
        assertEquals(quiz3.getTitle(), allQuizzes.get(2).getTitle());
        assertEquals(quiz4.getTitle(), allQuizzes.get(3).getTitle());
    }
}
