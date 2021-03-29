package com.savit.account.domain.usecase

import com.savit.account.domain.model.Account
import com.savit.account.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val repository: AccountRepository) {

    suspend operator fun invoke(): Flow<List<Account>> {
        return repository.getAccounts()
    }

}
