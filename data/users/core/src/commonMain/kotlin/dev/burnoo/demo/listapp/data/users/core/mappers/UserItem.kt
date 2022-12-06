package dev.burnoo.demo.listapp.data.users.core.mappers

import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem
import dev.burnoo.demo.listapp.data.users.network.model.NetworkUserItem

fun NetworkUserItem.asExternalModel() = UserItem(
    id = UserId(id),
    title = title,
    firstName = firstName,
    lastName = lastName,
    photoUrl = picture,
)
