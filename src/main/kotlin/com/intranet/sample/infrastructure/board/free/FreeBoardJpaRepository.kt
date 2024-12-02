package com.intranet.sample.infrastructure.board.free

import com.intranet.sample.domain.board.free.FreeBoard
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FreeBoardJpaRepository : DefaultBoardJpaRepository<FreeBoard>