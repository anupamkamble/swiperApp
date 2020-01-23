package com.coal.profileapp.ModelTests

import android.content.Context
import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.coal.profileapp.dataModel.local.entity.UserEntity
import com.coal.profileapp.dataModel.model.User
import com.coal.profileapp.utlities.globals.CardClass
import com.mindorks.placeholderview.SwipePlaceHolderView
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@RunWith(JUnit4::class)
class UserModelTest {


    val gender = "Male"
    val title = "Mr."
    var first = "First"
    var last = "Last"

    val street = "32"
    val city = "Pune"
    val state ="Maha"
    val zip = "412307"

    val email =  "chad.barnes30@example.com"
    val dob = 411246404L
    val registered = "1331848358"
    val phone = "(551)-320-4019"
    val cell =  "(738)-463-8054"

    @Rule
    @JvmField
    var initRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mUser: User


    @Before
    fun  setUp(){
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(mUser.gender).thenReturn(gender)
        Mockito.`when`(mUser.name).thenReturn(User.Name(title ,first, last))
        Mockito.`when`(mUser.location).thenReturn(User.Location(street, city, state, zip))
        Mockito.`when`(mUser.email).thenReturn(email)
        Mockito.`when`(mUser.registered).thenReturn(registered)
        Mockito.`when`(mUser.dob).thenReturn(dob)
        Mockito.`when`(mUser.cell).thenReturn(cell)
        Mockito.`when`(mUser.phone).thenReturn(phone)

    }

    @Test
    fun testGender(){
        Mockito.`when`(mUser.gender).thenReturn(gender)
        Assert.assertEquals("Male",mUser.gender)
    }

    @Test
    fun testTitle(){
        Mockito.`when`(mUser.name.title).thenReturn(title)
        Assert.assertEquals("Mr.",mUser.name.title)
    }

    @Test
    fun testFirst(){
        Mockito.`when`(mUser.name.first).thenReturn(first)
        Assert.assertEquals("First",mUser.name.first)
    }

    @Test
    fun testLast(){
        Mockito.`when`(mUser.name.last).thenReturn(last)
        Assert.assertEquals("Last",mUser.name.last)
    }

    @Test
    fun testLocation(){
        Mockito.`when`(mUser.location).thenReturn(User.Location(street, city, state, zip))
        Assert.assertNull(mUser.location)
    }

    @Test
    fun testEmail(){
        Mockito.`when`(mUser.email).thenReturn(email)
        Assert.assertEquals("chad.barnes30@example.com",mUser.email)
    }
    @Test
    fun testPhone(){
        Mockito.`when`(mUser.phone).thenReturn(phone)
        Assert.assertEquals("(551)-320-4019",mUser.phone)
    }
    @Test
    fun testCell(){
        Mockito.`when`(mUser.cell).thenReturn(cell)
        Assert.assertEquals("(738)-463-8054",mUser.cell)
    }


    /**
     * assign null
     */
    @After
    fun tearDown(){
    }

}