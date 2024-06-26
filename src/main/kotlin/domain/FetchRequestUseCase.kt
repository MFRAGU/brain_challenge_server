package domain

import AsteriskManager
import com.google.gson.Gson
import data.entity.Request
import data.entity.RequestType
import data.entity.Result
import domain.mapper.MapRequestToQuestionRequestUseCase
import domain.question.FetchQuestionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class FetchRequestUseCase(
    private val fetchQuestionUseCase: FetchQuestionUseCase
) {
    private val gson = Gson()
    private val _result = MutableSharedFlow<Result>(replay = 1)
    private val asteriskManager = AsteriskManager()
    val result = _result.asSharedFlow()

    suspend operator fun invoke(jsonRequest: String) {
        try {
            val request = gson.fromJson(jsonRequest, Request::class.java)
            when (request.type) {
                RequestType.QUESTION.name -> {
                    val questionRequest = MapRequestToQuestionRequestUseCase().invoke(request)
                    _result.tryEmit(fetchQuestionUseCase(questionRequest))
                }
                RequestType.ASTERISK.name -> {
                    asteriskManager.call()
                    _result.emit(Result.AsteriskResult("Call in progress..."))
                    delay(20000)
                    asteriskManager.hangup()
                }

                else -> _result.tryEmit(Result.BadResult("Unrecognized request type"))
            }
        } catch (e: Exception) {
            _result.tryEmit(Result.BadResult("Bad request format"))
        }
    }
}