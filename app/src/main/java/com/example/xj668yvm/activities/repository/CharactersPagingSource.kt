package com.example.xj668yvm.activities.repository

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.xj668yvm.activities.models.CharacterResponse
import com.example.xj668yvm.activities.models.Characters
import com.google.gson.Gson
import java.io.IOException



class CharactersPagingSource(
    private val service: CharacterServiceRepository,
) : PagingSource<Int, Characters>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters> {
        val pageNumber = params.key ?: 1

        return try {
            val response = service.getCharacter(page = pageNumber)
            var convertedData = Gson().fromJson<CharacterResponse>(response.body(),CharacterResponse::class.java)
            val repos = convertedData.results
            var nextPageNumber: Int? = null


            if (convertedData?.info?.next != null) {
                val uri = Uri.parse(convertedData.info!!.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = repos,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Characters>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}