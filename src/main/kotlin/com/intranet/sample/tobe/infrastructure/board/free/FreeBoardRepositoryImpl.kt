package com.intranet.sample.tobe.infrastructure.board.free

import com.intranet.sample.tobe.domain.board.free.FreeBoard
import com.intranet.sample.tobe.domain.board.free.FreeBoardRepository
import com.intranet.sample.tobe.infrastructure.board.AbstractBoardRepositoryImpl
import org.springframework.stereotype.Repository

@Repository
class FreeBoardRepositoryImpl(override val jpa: FreeBoardJpaRepository) :
    AbstractBoardRepositoryImpl<FreeBoard>(),
    FreeBoardRepository