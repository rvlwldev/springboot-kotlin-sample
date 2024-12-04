package com.intranet.sample.tobe.domain.board.discuss

import com.intranet.sample.tobe.domain.board.AbstractBoard
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "board_discuss")
class DiscussBoard(
    @OneToMany(mappedBy = "board", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val comments: List<DiscussComment> = mutableListOf()
) : AbstractBoard()