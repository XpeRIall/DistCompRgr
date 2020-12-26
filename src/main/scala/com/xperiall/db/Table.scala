package com.xperiall.db

import cats.effect.Sync
import com.xperiall.db.Models._

import scala.collection._

class Table[F[_], TT](name: String)(implicit F: Sync[F]) {
	private val _context =
		(name, mutable.Map[IndexType, TT]())

	def addTo(idx: IndexType, obj: TT): F[Unit] = F.pure {
		val table = _context._2
		val tableSize = table.size
		val index: IndexType = idx match {
			case _: LongIndex => LongIndex(tableSize + 1L)
			case _: IntIndex => IntIndex(tableSize + 1)
		}
		table.addOne((index, obj))
		(): Unit
	}

	def getName: String = _context._1

	def get: F[mutable.Map[IndexType, TT]] = F.pure(_context._2)
}
