package com.xperiall.queue

import cats.effect._
import cats.effect.concurrent.MVar
import com.xperiall.trx.Models.Transaction

class Queue[F[_]](implicit F: Concurrent[F]) {

  private val _context = MVar[F].empty[Option[Transaction]]

  def add(trx: Option[Transaction]): F[Unit] = F.map(_context)(_.put(trx))
  def take: F[Option[Transaction]] = F.flatMap(_context)(_.take)
}
