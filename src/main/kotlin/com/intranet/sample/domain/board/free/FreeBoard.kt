package com.intranet.sample.domain.board.free

import com.intranet.sample.domain.board.AbstractBoard
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "free_board")
class FreeBoard : AbstractBoard()