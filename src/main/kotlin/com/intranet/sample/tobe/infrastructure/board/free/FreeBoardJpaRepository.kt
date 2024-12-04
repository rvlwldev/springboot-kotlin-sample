package com.intranet.sample.tobe.infrastructure.board.free

import com.intranet.sample.tobe.domain.board.free.FreeBoard
import com.intranet.sample.tobe.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FreeBoardJpaRepository : DefaultBoardJpaRepository<FreeBoard>