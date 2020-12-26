package com.xperiall.queue

import cats.effect._
import com.xperiall.pool.ClientPool

class Consumer[F[_]](implicit F: Sync[F]) {

  def invoke(acc: List[Boolean])(q: Queue[F],
                                 p: ClientPool[F]): F[List[Boolean]] = {
    F.flatMap(q.take) {
      case Some(trx) =>
        F.flatMap(p.exec(trx)) { res =>
          invoke(acc appended res)(q, p)
        }
      case None => F.pure(acc)
    }
  }
}
