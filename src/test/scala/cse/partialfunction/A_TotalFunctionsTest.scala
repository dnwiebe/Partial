package cse.partialfunction

import org.scalatest.path

class A_TotalFunctions {
  val addition: (Int, Int) => Int = {(a, b) => a + b}
  val multiplication: (Int, Int) => Int = {(a, b) => a * b}

  def methodForContrast (a: Int, b: Int): Int = a*a - 2*a*b + b*b
}




class A_TotalFunctionsTest extends path.FunSpec {

  val totalFunctions = new A_TotalFunctions ()

  describe ("A total function") {
    val subject = totalFunctions.addition

    describe ("evaluated at one point") {
      val result = subject (3, 2)

      it ("produces the correct result") {
        assert (result === 5)
      }
    }

    describe ("evaluated at another point") {
      val result = subject (54, -32)

      it ("produces the correct result") {
        assert (result === 22)
      }
    }
  }

  describe ("Another total function") {
    val subject = totalFunctions.multiplication

    describe ("evaluated at one point") {
      val result = subject (3, 2)

      it ("produces the correct result") {
        assert (result === 6)
      }
    }

    describe ("evaluated at another point") {
      val result = subject (54, -32)

      it ("produces the correct result") {
        assert (result === -1728)
      }
    }
  }
}
