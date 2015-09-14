package cse.partialfunction

import org.scalatest.path

class E_PartialFunctionsUsingSyntax {
  val division: PartialFunction[(Int, Int), Int] = {
    case (a, b) if (b > 0) => a / b
  }

  val squareRoot: PartialFunction[Double, Double] = {
    case a if a >= 0 => Math.sqrt (a)
  }
}




class E_PartialFunctionsUsingSyntaxTest extends path.FunSpec {

  val partialFunctions = new E_PartialFunctionsUsingSyntax ()

  describe ("A partial function") {
    val subject = partialFunctions.division.lift

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
    val subject = partialFunctions.squareRoot.lift

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
