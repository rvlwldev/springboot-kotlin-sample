package com.intranet.sample.tobe.domain.board.free

import com.intranet.sample.tobe.domain.board.AbstractBoard
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "board_free")
class FreeBoard : AbstractBoard()