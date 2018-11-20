package com.mrtan.app.ui

import androidx.lifecycle.MutableLiveData
import com.mrtan.common.base.BaseViewModel
import com.mrtan.data.Repository
import com.mrtan.data.bindWait
import com.mrtan.data.domain.Category
import com.mrtan.data.remote.Result
import com.mrtan.data.remote.exec
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class CategoryViewModel @Inject constructor(
  dispatcher: CoroutineDispatcher,
  private val repository: Repository
) : BaseViewModel(dispatcher) {

  val categories = MutableLiveData<Result<List<Category>>>()

  fun category(author: String) {
    exec({ categories.value = it }) {
      repository.category(author)
          .bindWait(job)
    }
  }
}