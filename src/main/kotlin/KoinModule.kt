import data.database.MySQLConnection
import data.database.SQLDatabaseConnection
import data.question.QuestionExecutor
import data.question.QuestionRepository
import data.question.QuestionRepositoryImpl
import domain.FetchRequestUseCase
import domain.question.FetchQuestionUseCase
import domain.question.GetRandomQuestionsByDifficultyUseCase
import domain.question.GetRandomQuestionsUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::MySQLConnection) { bind<SQLDatabaseConnection>() }
    singleOf(::QuestionExecutor)
    singleOf(::QuestionRepositoryImpl) { bind<QuestionRepository>() }
    singleOf(::GetRandomQuestionsUseCase)
    singleOf(::GetRandomQuestionsByDifficultyUseCase)
    singleOf(::SocketManager)
    singleOf(::FetchRequestUseCase)
    singleOf(::FetchQuestionUseCase)
}