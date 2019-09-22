package com.prasad.cvreader.ui

import android.arch.lifecycle.Observer
import com.prasad.cvreader.model.Address
import com.prasad.cvreader.model.PhoneNumber
import com.prasad.cvreader.model.Post
import com.prasad.cvreader.model.Project
import com.prasad.cvreader.network.PostApi
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MyCVViewModelTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock lateinit var postApi: PostApi
    @Mock lateinit var responseObserver: Observer<Post>

    @Test
    fun myCVViewModel_whenInitCalled_loadsDataOnSuccess() {
        val post = getMockPostData()

        Mockito.`when`(postApi.getPosts()).thenReturn(Observable.just(post))

        val viewModelTest = MyCVViewModel()
        viewModelTest.response.observeForever(responseObserver)

        assertEquals(viewModelTest.response.value, post)
    }


    private fun getMockPostData(): Post {
        val address = Address("Street",
                "City",
                "State",
                "Post Code")
        val phoneNumbers = listOf<PhoneNumber>()
        val skills = listOf<String>()
        val projects = listOf<Project>()
        val post = Post("First Name",
                "Last Name",
                "Male",
                "01-01-1990",
                "name@email.com",
                address,
                phoneNumbers,
                "summary",
                skills,
                projects
        )
        return post
    }
}