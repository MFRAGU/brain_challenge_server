import com.google.gson.Gson
import domain.FetchRequestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class SocketManager : KoinComponent {
    private lateinit var clientSocket: Socket
    private lateinit var input: BufferedReader
    private lateinit var output: PrintWriter
    private val gson = Gson()
    private val serverSocket = ServerSocket(1234)
    private val fetchRequestUseCase: FetchRequestUseCase by inject(FetchRequestUseCase::class.java)

    suspend fun startServer() = withContext(Dispatchers.IO) {
        try {
            println("Server launched")
            do {
                println("Wainting client...")
                clientSocket = serverSocket.accept()
                println("Client connected !")
                input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                output = PrintWriter(clientSocket.getOutputStream(), true)
                launch {
                    collectResults()
                }
                var line: String?
                do {
                    line = input.readLine()?.let {
                        println(it)
                        fetchRequestUseCase(it)
                        it
                    }
                } while (line != null)
                input.close()
                output.close()
                clientSocket.close()

            } while (true)
        }
        catch(e: Exception){
            println(e)
            serverSocket.close()
            println("Server stopped")
        }
    }

    private suspend fun collectResults() {
        fetchRequestUseCase.result.collectLatest {
            val result = gson.toJson(it)
            println(result)
            output.println(result)
        }
    }
}