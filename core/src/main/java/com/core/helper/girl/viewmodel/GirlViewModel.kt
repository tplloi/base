package com.core.helper.girl.viewmodel

import com.core.base.BaseViewModel
import com.core.helper.girl.model.GirlPage
import com.core.helper.girl.service.GirlApiClient
import com.core.helper.girl.service.GirlRepository
import com.service.livedata.ActionData
import com.service.livedata.ActionLiveData
import kotlinx.coroutines.launch

/**
 * Created by Loitp on 24,December,2019
 * HMS Ltd
 * Ho Chi Minh City, VN
 * www.muathu@gmail.com
 */

class GirlViewModel : BaseViewModel() {
    private val logTag = "loitpp" + javaClass.simpleName
    private val repository: GirlRepository = GirlRepository(GirlApiClient.apiService)

    val userActionLiveData: ActionLiveData<ActionData<ArrayList<GirlPage>>> = ActionLiveData()

    fun getPage(pageIndex: Int, keyWord: String?) {
        userActionLiveData.set(ActionData(isDoing = true))

        ioScope.launch {
//            LLog.d(logTag, ">>>getPage pageIndex $pageIndex, keyWord: $keyWord")
            val response = repository.getPage(
                    pageIndex = pageIndex,
                    keyWord = keyWord
            )
//            LLog.d(logTag, "<<<getPage " + Gson().toJson(response))
            if (response.items == null) {
                userActionLiveData.postAction(
                        getErrorRequestGirl(response)
                )
            } else {
                userActionLiveData.post(
                        ActionData(
                                isDoing = false,
                                isSuccess = true,
                                data = response.items
                        )
                )
            }
        }

    }

}