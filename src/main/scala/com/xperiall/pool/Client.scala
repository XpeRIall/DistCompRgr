package com.xperiall.pool

import java.security.MessageDigest

import cats.effect.Sync
import com.xperiall.queue.Models.Hash
import com.xperiall.trx.Models.Transaction

class Client[F[_]](implicit F: Sync[F]) {

	def process(trx: Transaction): F[Hash] = F.pure(getMD5Hash(trx))

	def getMD5Hash(trx: Transaction): String = {
		val msgDigest: MessageDigest = MessageDigest.getInstance("MD5")
		val MD5Hash = msgDigest
			.digest(trx.toString.getBytes())
			.map(0xFF & _)
			.map {
				"%02x".format(_)
			}
			.foldLeft("") {
				_ + _
			}
		MD5Hash
	}
}
