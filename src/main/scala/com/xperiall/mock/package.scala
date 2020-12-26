// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.xperiall

import com.xperiall.activity.Models._
import com.xperiall.trx.Models._

package object mock {
  object ClientNameMock {
    private val cnames = Array(ClientName("a"),
                               ClientName("b"),
                               ClientName("c"),
                               ClientName("d"),
                               ClientName("e"),
                               ClientName("f"),
                               ClientName("g"),
                               ClientName("h"))
    def _cname: ClientName = Randomizer.select(cnames)
  }
  object UserNameMock {
    private val unames = Array(Username("a"),
                               Username("b"),
                               Username("c"),
                               Username("d"),
                               Username("e"),
                               Username("f"),
                               Username("g"),
                               Username("h"))
    def _uname: Username = Randomizer.select(unames)
  }
  object CurrencyMock {
    private val currs = Array(Currency("BTC"),
                              Currency("ETH"),
                              Currency("LTC"),
                              Currency("GCC"),
                              Currency("GHC"),
                              Currency("ERC"),
                              Currency("ETC"),
                              Currency("BPC"))
    def _currency: Currency = Randomizer.select(currs)
  }
  object AmountMock {
    def _amount: Amount = Amount(Randomizer.get())
  }
  object IPMock {
    private val ips = Array(IP("0.0.0.0"),
                            IP("0.0.0.1"),
                            IP("0.0.0.2"),
                            IP("0.0.0.3"),
                            IP("0.0.0.4"),
                            IP("0.0.0.5"),
                            IP("0.0.0.6"),
                            IP("0.0.0.7"))
    def _ip: IP = Randomizer.select(ips)
  }
  object OSMock {
    private val oss = Array(OS("Windows"),
                            OS("MacOS"),
                            OS("Ios"),
                            OS("Android"),
                            OS("Ubuntu"),
                            OS("FreeBSD"),
                            OS("Linux"),
                            OS("Unknown"))
    def _os: OS = Randomizer.select(oss)
  }
  object LangMock {
    private val langs = Array(Lang("en"),
                              Lang("uk"),
                              Lang("it"),
                              Lang("bl"),
                              Lang("ch"),
                              Lang("pl"),
                              Lang("deu"),
                              Lang("es"))
    def _lang: Lang = Randomizer.select(langs)
  }

  object TransactionLifeMock {
    def _life: AveSessionTime = AveSessionTime(Randomizer.getLong())
  }

  object GeoMock {
    private val geos = Array(Geo("EN"),
                             Geo("UKR"),
                             Geo("ITA"),
                             Geo("BEL"),
                             Geo("CHECH"),
                             Geo("POL"),
                             Geo("DEUCH"),
                             Geo("ESP"))
    def _geo: Geo = Randomizer.select(geos)

  }
  object Randomizer {
    private val random = scala.util.Random
    def select[T](arr: Array[T]): T = {
      val len = arr.length
      arr(random.between(0, len))
    }
    def get(): Double = random.between(0, Double.MaxValue / 100)

    def getLong(): Long = random.between(0, Long.MaxValue / 100)
  }
}
