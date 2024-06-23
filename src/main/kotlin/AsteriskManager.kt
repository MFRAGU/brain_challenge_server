import org.asteriskjava.manager.ManagerConnectionFactory
import org.asteriskjava.manager.action.OriginateAction


class AsteriskManager {
    private val managerConnection = ManagerConnectionFactory(
        HOSTNAME, USERNAME, PASSWORD
    ).createManagerConnection()

    companion object {
        private const val HOSTNAME = "192.168.1.104"
        private const val USERNAME = "mourchidi"
        private const val PASSWORD = "john"
    }

    fun run() {
        println("Launch run")
        managerConnection.login()
        println("Login ok")


        val originate = OriginateAction()
        originate.channel = "SIP/100"
        originate.context = "bchallenge"
        originate.exten = "100"
        originate.priority = 1
        originate.timeout = 30000L

        val originateResponse = managerConnection.sendAction(originate, 3000)
        println(originateResponse.response)

        managerConnection.logoff()
    }
}