package com.coal.profileapp.ViewModelTests

import androidx.lifecycle.Observer
import com.coal.profileapp.dataModel.local.DatabaseService
import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.dataModel.remote.NetworkService
import com.coal.profileapp.dataModel.repo.UserRepo
import com.coal.profileapp.ui.home.HomeViewModel
import com.coal.profileapp.utlities.globals.NetworkConnectivity
import com.coal.profileapp.utlities.globals.ViewStates
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coal.profileapp.dataModel.local.entity.UserEntity
import org.junit.*


@RunWith(JUnit4::class)
class HomeViewModelTest {


    @Rule  @JvmField
    var initRule = MockitoJUnit.rule()

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var  viewModel: HomeViewModel

    @Mock
    lateinit var observer: Observer<ViewStates<Int>>

    @Mock
    lateinit var userRepo: UserRepo

    @Mock
    lateinit var connectivity: NetworkConnectivity

    @Mock
    lateinit var networkService: NetworkService

    @Mock
    lateinit var databaseService: DatabaseService

    var compositeDisposable: CompositeDisposable? = null


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        compositeDisposable = CompositeDisposable()
        viewModel = HomeViewModel(compositeDisposable,connectivity, networkService, databaseService,  userRepo)
        viewModel.stateWithMessage.observeForever(observer)
    }

    @Test
    fun testNull(){
        Mockito.`when`(userRepo.getProfile()).thenReturn(null)
        Assert.assertNotNull(viewModel.profileMutableLiveData)
        Assert.assertTrue(viewModel.stateWithMessage.hasObservers())
    }

    @Test
    fun testAPISuccess(){
        Mockito.`when`(userRepo.getProfile()).thenReturn(Single.just(User()))
        viewModel.getProfiles(false)
        Mockito.verify(observer).onChanged(ViewStates.success())
    }

    @Test
    fun testAPIFailure(){
        Mockito.`when`(userRepo.getProfile()).thenReturn(Single.error(Throwable("error")))
        viewModel.getProfiles(false)
        Mockito.verify(observer).onChanged(ViewStates.error())
    }

    @Test
    fun testGetOfflineData(){
        Mockito.`when`(databaseService.userDao().getAllUsers()).thenReturn(Single.just(ArrayList<UserEntity>()))
        viewModel.getAllOfflineUsers(false)
        Mockito.verify(observer).onChanged(ViewStates.success())
    }


    @After
    fun tearDown(){
        compositeDisposable = null
    }
}