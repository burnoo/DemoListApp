package dev.burnoo.demo.listapp.data.users.model

data class Users(
    val list: List<UserItem>,
    val isLastPage: Boolean,
)
