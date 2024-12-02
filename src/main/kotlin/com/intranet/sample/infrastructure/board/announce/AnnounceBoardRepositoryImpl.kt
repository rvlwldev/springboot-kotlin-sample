package com.intranet.sample.infrastructure.board.announce

import com.intranet.sample.domain.board.announce.AnnounceBoard
import com.intranet.sample.domain.board.announce.AnnounceBoardRepository
import com.intranet.sample.infrastructure.board.AbstractBoardRepositoryImpl
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
class AnnounceBoardRepositoryImpl(override val jpa: DefaultBoardJpaRepository<AnnounceBoard>) :
    AbstractBoardRepositoryImpl<AnnounceBoard>(),
    AnnounceBoardRepository