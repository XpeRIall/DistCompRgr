package com.xperiall.activity

import cats.effect.Sync
import com.xperiall.activity.Models.Activity
import com.xperiall.db.DB
import com.xperiall.db.Models.LongIndex

class ActivityManager[F[_]](db: DB[F, Activity])(implicit F: Sync[F]) {
  def getActivites(newActivity: Activity): F[List[Activity]] = {
    F.flatMap(db.getTable(newActivity.username.value)) {
      case Some(act) => F.map(act.get)(_.values.toList)
      case None      => F.pure(List())
    }
  }

  def addActivities(activity: Activity): F[Unit] =
    F.flatMap(db.getTable(activity.username.value)) {
      case Some(t) => t.addTo(LongIndex(1L), activity)
      case None    => F.pure((): Unit)
    }
}
