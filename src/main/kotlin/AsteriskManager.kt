import org.asteriskjava.manager.ManagerConnectionFactory
import org.asteriskjava.manager.ManagerEventListener
import org.asteriskjava.manager.action.HangupAction
import org.asteriskjava.manager.action.OriginateAction
import org.asteriskjava.manager.event.ManagerEvent
import org.asteriskjava.manager.event.NewChannelEvent
import org.asteriskjava.manager.response.ManagerResponse

class AsteriskManager: ManagerEventListener {
    private val managerConnection = ManagerConnectionFactory(
        HOSTNAME, USERNAME, PASSWORD
    ).createManagerConnection()

    private var activeChannel: String? = null

    companion object {
        private const val HOSTNAME = "192.168.52.207"
        private const val USERNAME = "mourchidi"
        private const val PASSWORD = "john"
    }

    override fun onManagerEvent(event: ManagerEvent?) {
        if (event is NewChannelEvent) {
            activeChannel = event.channel
            println("New Channel Event: ${event.channel}")
        }
    }

    init {
        managerConnection.addEventListener(this)
    }

    fun call() {
        managerConnection.login()

        val originate = OriginateAction()
        originate.channel = "SIP/myclient"
        originate.context = "bchallenge"
        originate.exten = "100"
        originate.priority = 1
        originate.timeout = 30000L
        originate.callerId = "myclient <1000>"

        // Ajout de variables SIP
        originate.setVariable("SIPADDHEADER", "Alert-Info: Ring Answer")
        originate.setVariable("CALLERID(num)", "1000")
        originate.setVariable("CALLERID(name)", "myclient")
        originate.setVariable("ENCRYPTION", "yes")

        val originateResponse = managerConnection.sendAction(originate, 30000)
        println("Response : " + originateResponse.response)
    }

    fun hangup() {
        val hangupAction = HangupAction()
        hangupAction.channel = activeChannel
        try {
            val response: ManagerResponse = managerConnection.sendAction(hangupAction)
            println("Hangup Response: " + response.response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}