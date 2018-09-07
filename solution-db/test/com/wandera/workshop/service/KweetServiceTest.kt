package com.wandera.workshop.service

import com.wandera.workshop.model.DuplicateIdException
import com.wandera.workshop.model.Kweet
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class KweetServiceTest {

    @Rule
    @JvmField
    val thrown: ExpectedException = ExpectedException.none()

    private lateinit var service: KweetService

    @Before
    fun setUp() {
        DatabaseFactory.init()
        service = KweetService()
    }

    @After
    fun tearDown() {
        DatabaseFactory.clean()
    }

    @Test
    fun testPostKweet() = runBlocking {
        assertEquals(service.postKweet(Kweet(id = "some", userId = "user")), "some")
    }

    @Test
    fun testPostDuplicateKweet() = runBlocking {
        thrown.expect(DuplicateIdException::class.java)
        service.postKweet(Kweet(id = "some", userId = "user"))
        service.postKweet(Kweet(id = "some", userId = "user"))

        Unit
    }

    @Test
    fun testGetKweet() = runBlocking {
        val kweet = Kweet(id = "some", userId = "user")
        assertEquals(service.postKweet(kweet), "some")
        assertEquals(service.getKweet("some"), kweet)
    }

    @Test
    fun testDeleteKweet() = runBlocking {
        val kweet = Kweet(id = "some", userId = "user")
        assertEquals(service.postKweet(kweet), "some")
        service.deleteKweet("some")
        assertEquals(service.getKweet("some"), null)
    }

    @Test
    fun testListKweets() = runBlocking {
        assertEquals(service.postKweet(Kweet(id = "some", userId = "user")), "some")
        assertEquals(service.postKweet(Kweet(id = "some2", userId = "user")), "some2")
        assertEquals(service.getKweetsForUser("user").size, 2)
    }
}