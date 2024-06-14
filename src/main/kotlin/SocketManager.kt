import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class SocketManager {
    private val serverSocket = ServerSocket(1234)
    fun startServer() {
        val clientSocket = serverSocket.accept()
        println("Client connected")
        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val output = PrintWriter(clientSocket.getOutputStream(), true)
        var line: String?
        do {
            line = input.readLine()
            if (line != null) {
                output.println("Send data..")
            }
        } while (line != null)

        input.close()
        output.close()
        clientSocket.close()
        serverSocket.close()
    }
}