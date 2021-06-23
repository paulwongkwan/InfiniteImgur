package prism6.com.infiniteimgur.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import prism6.com.infiniteimgur.model.GalleryModel
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class mRemoteMediator(val page: Int, val galleryLocalRepository: GalleryLocalRepository, val galleryRemoteRepository: GalleryRemoteRepository) : RemoteMediator<Int, GalleryModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GalleryModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    lastItem.id
                }
            }

            val response = galleryRemoteRepository.getGallerys(Integer.parseInt(loadKey))

            if (loadType == LoadType.REFRESH) {
                galleryLocalRepository.removeAll()
            }

            galleryLocalRepository.insertAll(response.data!!.data)

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}