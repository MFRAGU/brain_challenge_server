
import org.koin.core.context.startKoin

fun main(){
    startKoin {
        modules(appModule)
    }
    println("Hello Brain challenge")
}