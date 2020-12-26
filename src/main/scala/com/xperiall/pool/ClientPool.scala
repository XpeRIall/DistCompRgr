package com.xperiall.pool

import cats.Parallel
import cats.effect._
import cats.effect.concurrent.Ref
import cats.syntax.all._
import com.xperiall.trx.Models.Transaction
import com.xperiall.trx.TransactionValidator

class ClientPool[F[_]](transactionValidator: TransactionValidator[F])(
    implicit F: Sync[F],
    P: Parallel[F]) {
  private val _context = Ref.of[F, List[Client[F]]](List())

  def addClient(client: Client[F]): F[Unit] = F.map(_context) { ctx =>
    ctx.update(clients => clients.appended(client))
  }

  def exec(trx: Transaction): F[Boolean] = {
    F.flatMap(_context) { ctx =>
      F.flatMap(ctx.get) { clients =>
        F.flatMap(clients.map(_.process(trx)).parSequence) {
          transactionValidator.validateTrx
        }
      }
    }
  }

}
