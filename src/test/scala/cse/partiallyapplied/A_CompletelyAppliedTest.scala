package cse.partiallyapplied

import org.scalatest.path

class A_CompletelyAppliedTest extends path.FunSpec
{

  val completelyApplied = new CompletelyApplied ()
  val date = completelyApplied.sdf.parse ("04-19-2015")

  describe ("A completely-applied function") {
    val subject = completelyApplied.logFunction

    describe ("applied to a date and a message") {
      val result = subject (date, "The time to repair the roof is when the sun is shining.")

      it ("produces the expected log") {
        assert (result === "04-19-2015: The time to repair the roof is when the sun is shining.\n")
      }
    }
  }

  describe ("A completely-applied method applied to a date and a message") {
    val result = completelyApplied.logMethod (date, "The time to repair the roof is when the sun is shining.")

    it ("produces the expected log") {
      assert (result === "04-19-2015: The time to repair the roof is when the sun is shining.\n")
    }
  }
}
