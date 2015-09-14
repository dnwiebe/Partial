package cse.partialfunction

import org.scalatest.path

class B_PartialFunctionsNaive {
  val division: (Int, Int) => Int = {(a, b) => a / b}
  val squareRoot: (Double) => Double = {(a) => Math.sqrt (a)}
}




class B_PartialFunctionsNaiveTest extends path.FunSpec {

  val partialFunctions = new B_PartialFunctionsNaive ()

  describe ("A partial function") {
    val subject = partialFunctions.division

    describe ("evaluated where it is defined") {
      val result = subject (12, 3)

      it ("produces the correct result") {
        assert (result === 4)
      }
    }

    describe ("evaluated where it is not defined") {
      val result: Exception = {
        try {
          subject (12, 0)
          null
        }
        catch {
          case e: ArithmeticException => e
        }
      }

      it ("throws an exception") {
        assert (result.getMessage === "/ by zero")
      }
    }
  }

  describe ("Another partial function") {
    val subject = partialFunctions.squareRoot

    describe ("evaluated where it is defined") {
      val result = subject (25)

      it ("produces the correct result") {
        assert (result === 5)
      }
    }

    describe ("evaluated where it is not defined") {
      val result = subject (-25.0)

      it ("produces NaN") {
        assert (s"${result}" === s"${Double.NaN}")
      }
    }
  }
}
