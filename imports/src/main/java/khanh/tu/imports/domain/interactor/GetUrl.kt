package khanh.tu.imports.domain.interactor

import khanh.tu.imports.data.remote.JumpRepo
import khanh.tu.imports.data.repository.JumpRepoImp

class GetUrl(
    private val repo: JumpRepoImp = JumpRepoImp()
) {
    suspend operator fun invoke(id: String) = repo.getJumpUrl(id)
}