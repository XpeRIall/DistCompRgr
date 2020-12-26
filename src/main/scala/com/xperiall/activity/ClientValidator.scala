package com.xperiall.activity

import cats.effect.Sync
import com.xperiall.activity.Models.Activity

class ClientValidator[F[_]](activityManager: ActivityManager[F])(
    implicit F: Sync[F]) {

  def validate(newActivity: Activity): F[Boolean] = {
    F.flatMap(activityManager.getActivites(newActivity)) { activities =>
      if (activities.isEmpty) {
        F.map(activityManager.addActivities(newActivity)) { _ =>
          true
        }
      } else F.pure(activities.exists(_.ip == newActivity.ip))
    }
  }
}
