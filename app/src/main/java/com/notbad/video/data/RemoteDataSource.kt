package com.notbad.video.data

import com.notbad.video.network.RetrofitService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val dataHandler: IDataHandler, val retrofitService: RetrofitService) {

}