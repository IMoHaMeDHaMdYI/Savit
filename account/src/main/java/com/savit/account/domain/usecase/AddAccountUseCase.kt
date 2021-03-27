package com.savit.account.domain.usecase

import com.savit.account.domain.model.Account
import com.savit.account.domain.repository.AccountRepository
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(private val repository: AccountRepository) {

    suspend operator fun invoke(account: Account) {
        repository.addAccount(account)
    }
}