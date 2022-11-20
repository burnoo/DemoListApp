package dev.burnoo.demo.listapp.data.users.core

import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId
import dev.burnoo.demo.listapp.data.users.model.UserItem

val testUsers = List(5) {
    UserItem(
        id = UserId(it.toString()),
        title = "mrs",
        firstName = "John $it",
        lastName = "Doe $it",
        photoUrl = "https://example.org/image$it.jpg",
    )
}
val testUser = User(
    title = "mrs",
    firstName = "John 0",
    lastName = "Doe",
    photoUrl = "https://example.org/image0.jpg",
    gender = "male",
    email = "test@example.org",
    phone = "123456789",
)
