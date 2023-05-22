package com.example.testmarvel.model

data class ComicResponse(val data: ComicData)

data class ComicData(val results: List<Comic>)