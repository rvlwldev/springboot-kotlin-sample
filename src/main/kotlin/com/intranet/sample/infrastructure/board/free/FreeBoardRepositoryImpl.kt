package com.intranet.sample.infrastructure.board.free

import com.intranet.sample.domain.board.free.FreeBoard
import com.intranet.sample.domain.board.free.FreeBoardRepository
import com.intranet.sample.infrastructure.board.AbstractBoardRepositoryImpl
import org.springframework.stereotype.Repository

@Repository
class FreeBoardRepositoryImpl(override val jpa: FreeBoardJpaRepository) :
    AbstractBoardRepositoryImpl<FreeBoard>(),
    FreeBoardRepository