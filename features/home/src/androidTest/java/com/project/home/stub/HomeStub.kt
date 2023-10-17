package com.project.home.stub

import com.project.home.data.model.CharacterResponse
import com.project.home.data.model.DataResponse
import com.project.home.data.model.ResultsResponse
import com.project.home.data.model.ThumbnailResponse

object HomeStub {

    fun getSizeSuccessStub() = getSuccessStub("").data.results?.size ?: 0
    fun getSuccessStub(name: String): CharacterResponse {
        return CharacterResponse(
            data = DataResponse(
                results = listOf(
                    ResultsResponse(
                        id = "123",
                        name = name,
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),
                    ResultsResponse(
                        id = "123",
                        name = "teste",
                        thumbnail = ThumbnailResponse(
                            path = "teste",
                            extension = ".jpg"
                        )
                    ),

                    )
            )
        )
    }
}