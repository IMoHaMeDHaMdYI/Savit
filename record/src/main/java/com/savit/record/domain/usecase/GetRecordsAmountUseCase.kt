package com.savit.record.domain.usecase

import com.savit.record.domain.repsotory.RecordRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetRecordsAmountUseCase @Inject constructor(private val repository: RecordRepository) {

    fun execute(accountId: Long): Observable<Int> {
        println("account id : $accountId")
        return repository.getRecordAmount(accountId = accountId)
            .startWith(Observable.just(0))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}