package com.intranet.sample.tobe.infrastructure.board.announce

import com.intranet.sample.tobe.domain.board.announce.AnnounceBoard
import com.intranet.sample.tobe.domain.board.announce.AnnounceBoardRepository
import com.intranet.sample.tobe.infrastructure.board.AbstractBoardRepositoryImpl
import com.intranet.sample.tobe.infrastructure.board.DefaultBoardJpaRepository
import org.springframework.stereotype.Repository

@Repository
class AnnounceBoardRepositoryImpl(override val jpa: DefaultBoardJpaRepository<AnnounceBoard>) :
    AbstractBoardRepositoryImpl<AnnounceBoard>(),
    AnnounceBoardRepository