package cse.partialfunction

import org.scalatest.path

class C_PartialFunctionsBetter {
  val division: (Int, Int) => Option[Int] = {(a, b) =>
    if (b > 0) {
      Some (a / b)
    }
    else {
      None
    }
  }

  val squareRoot: (Double) => Option[Double] = {(a) =>
    if (a >= 0) {
      Some (Math.sqrt (a))
    }
    else {
      None
    }
  }
}




class C_PartialFunctionsBetterTest extends path.FunSpec {

  val partialFunctions = new C_PartialFunctionsBetter ()

  describe ("A partial function") {
    val subject = partialFunctions.division

    describe ("evaluated where it is defined") {
      val result = subject (12, 3)

      it ("produces a Some containing the correct result") {
        assert (result === Some (4))
      }
    }

    describe ("evaluated where it is not defined") {
      val result = subject (12, 0)

      it ("produces a None") {
        assert (result === None)
      }
    }
  }

  describe ("Another partial function") {
    val subject = partialFunctions.squareRoot

    describe ("evaluated where it is defined") {
      val result = subject (25)

      it ("produces a Some containing the correct result") {
        assert (result === Some (5))
      }
    }

    describe ("evaluated where it is not defined") {
      val result = subject (-25)

      it ("produces a None") {
        assert (result === None)
      }
    }
  }
}
