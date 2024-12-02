package com.intranet.sample.infrastructure.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussBoard
import com.intranet.sample.domain.board.discuss.DiscussBoardRepository
import com.intranet.sample.infrastructure.board.AbstractBoardRepositoryImpl
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DiscussBoardRepositoryImpl(override val jpa: DefaultBoardJpaRepository<DiscussBoard>) :
    AbstractBoardRepositoryImpl<DiscussBoard>(),
    DiscussBoardRepository