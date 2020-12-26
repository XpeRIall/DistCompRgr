package com.xperiall.db

import cats.effect._
import cats.effect.concurrent._

import scala.collection._
import scala.collection.mutable.ArrayBuffer

class DB[F[_], TT](implicit F: Sync[F]) {

  private val _context: F[Ref[F, ArrayBuffer[Table[F, TT]]]] =
    Ref.of[F, mutable.ArrayBuffer[Table[F, TT]]](
      mutable.ArrayBuffer[Table[F, TT]]())

  def addTable(table: Table[F, TT]): F[Unit] = {
    val tableName = table.getName
    F.flatMap(getTable(tableName)) {
      case Some(_) => F.pure((): Unit)
      case None =>
        F.flatMap(_context)(_.update(_.addOne(table)))
    }
  }

  def getTable(name: String): F[Option[Table[F, TT]]] =
    F.flatMap(_context) { c =>
      F.map(c.get) {
        _.find(_.getName == name)
      }
    }
}
