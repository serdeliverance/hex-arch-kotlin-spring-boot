package com.hexarchbootdemo.adapter.input.rest.reactor

import com.hexarchbootdemo.adapter.input.rest.data.VoterJson
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just

@RestController
class FindVoterController(
    private val findVotersUseCase: FindVoterUseCase
) {
    @GetMapping("/voters")
    fun findVoters(@RequestParam lastName: String): Mono<List<VoterJson>> {
        return just(toJsonList(findVotersUseCase.findByLastName(FindByLastNameQuery(lastName))))
    }

    private fun toJsonList(people: List<Voter>): List<VoterJson> {
        return people.map {
            VoterJson(firstInitial = it.firstName[0],
                lastName = it.lastName,
                socialSecurityNumber = it.socialSecurityNumber.toString())
        }
    }
}
