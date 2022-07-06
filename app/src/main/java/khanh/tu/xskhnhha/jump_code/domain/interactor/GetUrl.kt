package khanh.tu.xskhnhha.jump_code.domain.interactor

import khanh.tu.xskhnhha.jump_code.data.repository.JumpRepoImp

class GetUrl(
    private val repo: JumpRepoImp = JumpRepoImp()
) {
    suspend operator fun invoke(id: String) = repo.getJumpUrl(id)
}