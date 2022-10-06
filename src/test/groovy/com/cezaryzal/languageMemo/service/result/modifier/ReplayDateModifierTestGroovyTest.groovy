package com.cezaryzal.languageMemo.service.result.modifier

import spock.lang.Specification

import java.time.LocalDate

class ReplayDateModifierTestGroovyTest extends Specification{
    def replayDateModifier

    def setup(){
        replayDateModifier = new ReplayDateModifier()

    }
    def "change replay date by replay level with 0 level"(){
        when:
        def replayedDate = replayDateModifier.changeReplayDateByReplayLevel(0)
        then:
        LocalDate.now().plusDays(1) == replayedDate
        LocalDate.now() != replayedDate
    }
    def "change replay date by replay level with 1, 2, 3 level"(int level, LocalDate date){
        expect:
        date.plusDays(level) == replayDateModifier.changeReplayDateByReplayLevel(level)
        where:
        level | date
        1     | LocalDate.now()
        2     | LocalDate.now()
        3     | LocalDate.now()
    }
    def "change replay date by replay level with 4 level"(){
        when:
        def replayedDate = replayDateModifier.changeReplayDateByReplayLevel(4)
        then:
        LocalDate.now().plusDays(5) == replayedDate
        LocalDate.now() != replayedDate
        LocalDate.now().plusDays(4) != replayedDate
        LocalDate.now().plusDays(6) != replayedDate
    }
    def "change replay date by replay level with 5 level"(){
        when:
        def replayedDate = replayDateModifier.changeReplayDateByReplayLevel(5)
        then:
        LocalDate.now().plusDays(10) == replayedDate
        LocalDate.now().plusDays(9) != replayedDate
        LocalDate.now().plusDays(11) != replayedDate
        LocalDate.now() != replayedDate
    }

}
