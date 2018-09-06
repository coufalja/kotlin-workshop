package com.wandera.workshop.model

import com.wandera.workshop.randomId

data class User(val id: String = randomId(), val name: String)