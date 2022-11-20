package dev.burnoo.demo.listapp.domain.users

import dev.burnoo.demo.listapp.data.users.core.UsersRepository
import dev.burnoo.demo.listapp.data.users.model.User
import dev.burnoo.demo.listapp.data.users.model.UserId

class GetUserUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(userId: UserId): User {
        return usersRepository.getUser(userId)
    }
}
