package data.question

import data.database.SQLDatabaseConnection
import data.entity.Difficulty
import data.entity.Question
import java.sql.SQLException

class QuestionExecutor(sqlDatabaseConnection: SQLDatabaseConnection) {
    private val connection = sqlDatabaseConnection.getConnection()
    companion object {
        private const val QUESTION_TABLE_NAME = "quizz_questions"
        private const val CATEGORY_COLUMN = "category"
        private const val ID_COLUMN = "id"
        private const val CORRECT_ANSWER_COLUMN = "correct_answer"
        private const val INCORRECT_ANSWERS_COLUMN = "incorrect_answer"
        private const val QUESTION_COLUMN = "question"
        private const val DIFFICULTY_COLUMN = "difficulty"
        private const val LIMIT_QUESTIONS = 10
    }

    fun getRandomQuestions(): List<Question> {
        return try {
            val query = """
                SELECT * FROM $QUESTION_TABLE_NAME 
                ORDER BY RAND()
                LIMIT $LIMIT_QUESTIONS
                """
            executeQuery(query)
        }
        catch (sqlException: SQLException) {
            System.err.println("Error: ${sqlException.message}")
            emptyList()
        }
    }

    fun getRandomQuestionsByDifficulty(difficulty: Difficulty): List<Question> {
        return try {
            val query = """
                SELECT * FROM $QUESTION_TABLE_NAME  
                WHERE $DIFFICULTY_COLUMN = '${difficulty.value}' 
                ORDER BY RAND()
                LIMIT $LIMIT_QUESTIONS
            """
            executeQuery(query)
        }
        catch (sqlException: SQLException) {
            System.err.println("Error: ${sqlException.message}")
            emptyList()
        }
    }

    private fun executeQuery(query: String): List<Question> {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query)
        val questions = mutableListOf<Question>()
        while (resultSet.next()) {
            questions.add(
                Question(
                    resultSet.getString(CATEGORY_COLUMN),
                    resultSet.getString(ID_COLUMN),
                    resultSet.getString(CORRECT_ANSWER_COLUMN),
                    resultSet.getString(INCORRECT_ANSWERS_COLUMN),
                    resultSet.getString(QUESTION_COLUMN),
                    resultSet.getString(DIFFICULTY_COLUMN)
                )
            )
        }
        println("Request successfully")
        statement.close()
        return questions
    }
}