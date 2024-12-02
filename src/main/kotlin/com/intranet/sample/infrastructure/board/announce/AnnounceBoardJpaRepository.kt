package com.intranet.sample.infrastructure.board.announce

import com.intranet.sample.domain.board.announce.AnnounceBoard
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository

interface AnnounceBoardJpaRepository : DefaultBoardJpaRepository<AnnounceBoard>