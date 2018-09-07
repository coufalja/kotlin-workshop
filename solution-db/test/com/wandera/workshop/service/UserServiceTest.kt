package com.wandera.workshop.service

import com.wandera.workshop.model.User
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserServiceTest {

    private lateinit var service: UserService

    @Before
    fun setUp() {
        DatabaseFactory.init()
        service = UserService()
    }

    @After
    fun tearDown() {
        DatabaseFactory.clean()
    }

    @Test
    fun testCreateUser() = runBlocking {
        assertEquals(service.createOrUpdateUser(User(id = "some", name = "user")), "some")
    }

    @Test
    fun testUpdateUser() = runBlocking {
        assertEquals(service.createOrUpdateUser(User(id = "some", name = "user")), "some")
        assertEquals(service.getUser("some")!!.name, "user")
        assertEquals(service.createOrUpdateUser(User(id = "some", name = "user2")), "some")
        assertEquals(service.getUser("some")!!.name, "user2")
    }

    @Test
    fun testGetUser() = runBlocking {
        val user = User(id = "some", name = "user")
        assertEquals(service.createOrUpdateUser(user), "some")
        assertEquals(service.getUser("some"), user)
    }

    @Test
    fun testListUsers() = runBlocking {
        assertEquals(service.createOrUpdateUser(User(id = "some", name = "user")), "some")
        assertEquals(service.createOrUpdateUser(User(id = "some2", name = "user")), "some2")
        assertEquals(service.listUsers().size, 2)
    }
}