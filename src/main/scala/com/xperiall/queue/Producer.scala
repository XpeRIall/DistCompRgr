package com.xperiall.queue

import cats.effect._
import com.xperiall.trx.Models._

class Producer[F[_]]()(implicit F: Sync[F]) {

  def invoke(trxs: List[Transaction], q: Queue[F]): F[Unit] = {
    trxs match {
      case Nil => q.add(None)
      case head :: tail =>
        F.map(q.add(Some(head))) { _ =>
          invoke(tail, q)
        }
    }
  }
}
