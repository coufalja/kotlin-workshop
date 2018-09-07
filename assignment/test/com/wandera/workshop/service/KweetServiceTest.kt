package com.wandera.workshop.service

import com.wandera.workshop.model.DuplicateIdException
import com.wandera.workshop.model.Kweet
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
        service = KweetService()
    }

    @Test
    fun testPostKweet() {
        assertEquals(service.postKweet(Kweet(id = "some", userId = "user")), "some")
    }

    @Test
    fun testPostDuplicateKweet() {
        thrown.expect(DuplicateIdException::class.java)
        service.postKweet(Kweet(id = "some", userId = "user"))
        service.postKweet(Kweet(id = "some", userId = "user"))
    }

    @Test
    fun testGetKweet() {
        val kweet = Kweet(id = "some", userId = "user")
        assertEquals(service.postKweet(kweet), "some")
        assertEquals(service.getKweet("some"), kweet)
    }

    @Test
    fun testDeleteKweet() {
        val kweet = Kweet(id = "some", userId = "user")
        assertEquals(service.postKweet(kweet), "some")
        service.deleteKweet("some")
        assertEquals(service.getKweet("some"), null)
    }

    @Test
    fun testListKweets() {
        assertEquals(service.postKweet(Kweet(id = "some", userId = "user")), "some")
        assertEquals(service.postKweet(Kweet(id = "some2", userId = "user")), "some2")
        assertEquals(service.getKweetsForUser("user").size, 2)
    }
}