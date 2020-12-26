package com.xperiall.di

import cats.Parallel
import cats.effect.{Concurrent, Sync}
import com.xperiall.activity.Models.Activity
import com.xperiall.activity._
import com.xperiall.db.DB
import com.xperiall.pool._
import com.xperiall.queue.Queue
import com.xperiall.trx.TransactionValidator

trait Container[F[_]] {
  val db: DB[F, Activity]
  val activityManager: ActivityManager[F]
  val clientValidator: ClientValidator[F]
  val clientPool: ClientPool[F]
  val clientRegistrator: ClientRegistrator[F]
  val queue: Queue[F]
  val trxValidator: TransactionValidator[F]
}

class ContainerDef[F[_]](implicit F: Sync[F], P: Parallel[F], C: Concurrent[F])
    extends Container[F] {
  override val db: DB[F, Activity] = new DB()

  override val trxValidator: TransactionValidator[F] =
    new TransactionValidator[F]()

  override val queue: Queue[F] = new Queue[F]()

  override val activityManager: ActivityManager[F] = new ActivityManager[F](db)

  override val clientValidator: ClientValidator[F] =
    new ClientValidator[F](activityManager)

  override val clientPool: ClientPool[F] = new ClientPool[F](trxValidator)

  override val clientRegistrator: ClientRegistrator[F] =
    new ClientRegistrator[F](clientPool)
}
