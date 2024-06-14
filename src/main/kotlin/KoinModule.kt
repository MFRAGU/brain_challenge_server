import data.database.MySQLConnection
import data.database.SQLDatabaseConnection
import data.question.QuestionExecutor
import data.question.QuestionRepositoryImpl
import domain.GetRandomQuestionsByDifficultyUseCase
import domain.GetRandomQuestionsUseCase
import domain.repository.QuestionRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MySQLConnection) { bind<SQLDatabaseConnection>() }
    singleOf(::QuestionExecutor)
    singleOf(::QuestionRepositoryImpl) { bind<QuestionRepository>() }
    singleOf(::GetRandomQuestionsUseCase)
    singleOf(::GetRandomQuestionsByDifficultyUseCase)
}