package khanh.tu.imports.domain.interactor

import khanh.tu.imports.data.remote.JumpRepo
import khanh.tu.imports.data.repository.JumpRepoImp

class RegisterApp(
    private val repo: JumpRepoImp = JumpRepoImp()
) {
    suspend operator fun invoke(id: String) = repo.registerApp(id)
}