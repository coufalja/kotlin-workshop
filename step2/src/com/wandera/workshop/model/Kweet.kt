package com.wandera.workshop.model

import com.wandera.workshop.randomId
import java.time.Instant

data class Kweet(val id: String = randomId(), val userId: String, val text: String = "", val date: Instant = Instant.now())