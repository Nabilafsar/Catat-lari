package com.upn.catatlari.Repository

import com.upn.catatlari.model.User
import com.upn.catatlari.data.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun register(user: User) {
        userDao.insert(user)
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }
}