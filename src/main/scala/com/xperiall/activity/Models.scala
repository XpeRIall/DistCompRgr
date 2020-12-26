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
package com.xperiall.activity

import com.xperiall.mock._
import io.estatico.newtype.macros.newtype

object Models {

  case class ClientName(value: String)

  case class IP(value: String)

  case class OS(value: String)

  case class Lang(value: String)

  //Amount in seconds
  case class AveSessionTime(value: Long)

  case class Geo(value: String)

  case class Activity(username: ClientName = ClientNameMock._cname,
                      ip: IP = IPMock._ip,
                      os: OS = OSMock._os,
                      lang: Lang = LangMock._lang,
                      sessionTime: AveSessionTime = TransactionLifeMock._life,
                      geo: Geo = GeoMock._geo)

}
