package com.example.presentation

import androidx.lifecycle.*
import com.example.core.event.Event
import com.example.domain.usecase.GetUser
import com.example.presentation.mapper.UserDomainToUiModelMapper
import com.example.presentation.model.UserUiModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class MainViewModel : ViewModel() {

    abstract fun getUser()
    abstract val launches: LiveData<List<UserUiModel>>
    abstract val loadingHeader: LiveData<Boolean>
    abstract val loadingBody: LiveData<Boolean>
    abstract val headerError: LiveData<Event<Unit>>
    abstract val bodyError: LiveData<Boolean>
    abstract val openLink: LiveData<Event<String>>
    abstract val showDialog: LiveData<Event<Unit>>
}

@ExperimentalCoroutinesApi
@FlowPreview
class MainViewModelImpl @Inject constructor(
    private val getUser: GetUser,
    private val userDomainToUiModelMapper: UserDomainToUiModelMapper
) : MainViewModel() {

    private val openLinkChannel = ConflatedBroadcastChannel<Event<String>>()
    private val showDialogChannel = ConflatedBroadcastChannel<Event<Unit>>()

    override val openLink: LiveData<Event<String>> = openLinkChannel.asFlow().asLiveData()
    override val showDialog: LiveData<Event<Unit>> = showDialogChannel.asFlow().asLiveData()

    private val _loadingHeader = MediatorLiveData<Boolean>()
    override val loadingHeader: LiveData<Boolean>
        get() = _loadingHeader

    private val _loadingBody = MediatorLiveData<Boolean>()
    override val loadingBody: LiveData<Boolean>
        get() = _loadingBody

    private val _launches = MediatorLiveData<List<UserUiModel>>()
    override val launches: LiveData<List<UserUiModel>>
        get() = _launches

    private val _bodyError = MediatorLiveData<Boolean>()
    override val bodyError: LiveData<Boolean>
        get() = _bodyError

    private val _headerError = MediatorLiveData<Event<Unit>>()
    override val headerError: LiveData<Event<Unit>>
        get() = _headerError

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        _loadingBody.value = false
        _bodyError.value = true
    }

    override fun getUser() {
        viewModelScope.launch(errorHandler) {
            _loadingBody.value = true
            getUser.execute()
                .catch {
                    _loadingBody.value = false
                    _bodyError.value = true
                }
                .collect { launchesDomainModel ->
                    val uiModel =
                        userDomainToUiModelMapper.toUiModel(launchesDomainModel)
                    _launches.postValue(uiModel)
                    _loadingBody.value = false
                }
        }
    }
}