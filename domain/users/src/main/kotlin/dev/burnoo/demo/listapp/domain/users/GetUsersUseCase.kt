package dev.burnoo.demo.listapp.domain.users

import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import dev.burnoo.demo.listapp.data.users.model.UserItem

class GetUsersUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(): List<UserItem> {
        return usersRepository.getUsers()
    }
}
