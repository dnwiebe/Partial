package cse.partiallyapplied


import org.scalatest.path

class B_PartiallyAppliedTest extends path.FunSpec
{

  val completelyApplied = new CompletelyApplied ()
  val date = completelyApplied.sdf.parse ("04-19-2015")

  describe ("A function partially applied to a date") {
    val subject = completelyApplied.logFunction (date, _: String)

    describe ("yields a function that when given only a message") {
      val result = subject ("The time to repair the roof is when the sun is shining.")

      it ("produces the expected log") {
        assert (result === "04-19-2015: The time to repair the roof is when the sun is shining.\n")
      }
    }
  }

  describe ("A method partially applied to a date and a message") {
    val subject = completelyApplied.logMethod (date, _: String)

    describe ("yields a function that when given only a message") {
      val result = subject ("The time to repair the roof is when the sun is shining.")

      it ("produces the expected log") {
        assert (result === "04-19-2015: The time to repair the roof is when the sun is shining.\n")
      }
    }
  }
}
