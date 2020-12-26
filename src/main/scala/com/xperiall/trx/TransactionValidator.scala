package com.xperiall.trx

import cats.Monad
import com.xperiall.queue.Models.Hash

class TransactionValidator[F[_]](implicit F: Monad[F]) {
  def validateTrx(hashes: List[Hash]): F[Boolean] = F.pure {
    hashes.forall(_ == hashes.head)
  }
}
