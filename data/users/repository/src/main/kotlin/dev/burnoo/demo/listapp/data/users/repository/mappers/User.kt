package dev.burnoo.demo.listapp.data.users.repository.mappers

import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUser

fun NetworkUser.asExternalModel() = User(
    title = title,
    firstName = firstName,
    lastName = lastName,
    photoUrl = picture,
    gender = gender,
    email = email,
    phone = phone,
)
