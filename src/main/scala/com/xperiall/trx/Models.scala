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
package com.xperiall.trx

import io.estatico.newtype.macros.newtype
import com.xperiall.mock._

object Models {

  case class Username(value: String)

  object Username {
    implicit def from(x: Username): String = x.value

    implicit def to(x: String): Username = Username(x)
  }

  case class Currency(value: String)

  object Currency {
    implicit def from(x: Currency): String = x.value

    implicit def to(x: String): Currency = Currency(x)
  }

  @newtype case class Amount(value: Double)

  object Amount {
    implicit def from(x: Amount): Double = x.value

    implicit def to(x: Double): Amount = Amount(x)
  }

  case class Transaction(
      username: Username = UserNameMock._uname,
      userGetter: Username = UserNameMock._uname,
      senderBalance: Amount = AmountMock._amount,
      getterBalance: Amount = AmountMock._amount,
      currency: Currency = CurrencyMock._currency,
      newSenderBalance: Amount = AmountMock._amount,
      newGetterBalance: Amount = AmountMock._amount
  )

}
