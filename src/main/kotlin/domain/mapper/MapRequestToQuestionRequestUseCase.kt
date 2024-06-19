package domain.mapper

import data.entity.QuestionRequest
import data.entity.QuestionRequestAction
import data.entity.Request

class MapRequestToQuestionRequestUseCase {
    operator fun invoke(request: Request) =
        QuestionRequest(
            action = QuestionRequestAction.valueOf(request.action),
            data = request.data
        )
}