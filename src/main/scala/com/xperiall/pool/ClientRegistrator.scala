package com.xperiall.pool

import cats.effect.Sync

class ClientRegistrator[F[_]](pool: ClientPool[F])(implicit F: Sync[F]) {
  def register(client: Client[F]): F[Unit] = pool.addClient(client)
}
