package com.savit.record.domain.usecase

import com.savit.record.domain.model.Record
import com.savit.record.domain.repsotory.RecordRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetRecordUseCase @Inject constructor(private val repository: RecordRepository) {

    fun getRecords(id: Long): Observable<List<Record>> {
        return repository.getRecords(accountId = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}