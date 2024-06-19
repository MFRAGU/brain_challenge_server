import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin

fun main() = runBlocking {
    startKoin {
        modules(appModule)
    }
    SocketManager().startServer()
}