package com.tron.kkdayassiment

import com.tron.api.domain.ShrtCodeRepository
import com.tron.shared.UseCase
import com.tron.shared.domain.HistoryRepository
import com.tron.shared.model.History
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShrtCodeUseCase @Inject constructor(
    private val shrtCodeRepository: ShrtCodeRepository,
    private val historyRepository: HistoryRepository
) : UseCase.ReactiveCompletable<String> {
    override fun invoke(input: String): Completable {
        return shrtCodeRepository.getShrtCode(input)
            .flatMapCompletable {
                historyRepository.insertHistory(
                    History(
                        full_short_link = it.full_short_link,
                        original_link = it.original_link
                    )
                )
            }
    }
}
