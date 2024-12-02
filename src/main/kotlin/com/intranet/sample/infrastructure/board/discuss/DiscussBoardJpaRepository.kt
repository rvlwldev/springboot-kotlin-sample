package com.intranet.sample.infrastructure.board.discuss

import com.intranet.sample.domain.board.discuss.DiscussBoard
import com.intranet.sample.infrastructure.board.DefaultBoardJpaRepository

interface DiscussBoardJpaRepository : DefaultBoardJpaRepository<DiscussBoard>