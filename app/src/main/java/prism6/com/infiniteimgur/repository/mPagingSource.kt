package prism6.com.infiniteimgur.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import prism6.com.infiniteimgur.model.GalleryModel

class mPagingSource(val galleryLocalRepository: GalleryLocalRepository) : PagingSource<Int, GalleryModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryModel> {
//        try {
//            // Start refresh at page 1 if undefined.
//            val nextPageNumber = params.key ?: 1
//            val response = galleryLocalRepository.getGallerys()
//            return LoadResult.Page(
//                data = response.,
//                prevKey = null, // Only paging forward.
//                nextKey = nextPageNumber + 1
//            )
//        } catch (e: Exception) {
//            // Handle errors in this block and return LoadResult.Error if it is an
//            // expected error (such as a network failure).
//        }
        TODO("Not yet implemented")
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryModel>): Int? {
        TODO("Not yet implemented")
    }
}