package cse.partialfunction

import org.scalatest.path

class D_PartialFunctionsUsingClass {
  val division = new PartialFunction[(Int, Int), Int]() {
    override def isDefinedAt (pair: (Int, Int)) = pair._2 > 0
    override def apply (pair: (Int, Int)) = pair._1 / pair._2
  }

  val squareRoot = new PartialFunction[Double, Double]() {
    override def isDefinedAt (a: Double) = a >= 0
    override def apply (a: Double) = Math.sqrt (a)
  }
}




class D_PartialFunctionsUsingClassTest extends path.FunSpec {

  val partialFunctions = new D_PartialFunctionsUsingClass ()

  describe ("A partial function") {
    val subject = partialFunctions.division

    describe ("evaluated where it is defined") {
      val result = subject (12, 3)

      it ("produces a Some containing the correct result") {
        assert (result === 4)
      }
    }

    describe ("evaluated where it is not defined") {
      val result = {
        try {
          subject (12, 0)
          None
        }
        catch {
          case e: ArithmeticException => Some (e)
        }
      }

      it ("throws an exception") {
        assert (result.get.getMessage === "/ by zero")
      }
    }
  }

  describe ("Another partial function") {
    val subject = partialFunctions.squareRoot

    describe ("evaluated where it is defined") {
      val result = subject (25)

      it ("produces a Some containing the correct result") {
        assert (result === 5)
      }
    }

    describe ("evaluated where it is not defined") {
      val result = subject (-25)

      it ("produces NaN") {
        assert (s"${result}" === s"${Double.NaN}")
      }
    }
  }
}

class D_PartialFunctionsUsingClassLiftedTest extends path.FunSpec {

  val partialFunctions = new D_PartialFunctionsUsingClass ()

  describe ("A partial function, lifted") {
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

  describe ("Another partial function, lifted") {
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
