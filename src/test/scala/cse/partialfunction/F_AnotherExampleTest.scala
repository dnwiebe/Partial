package cse.partialfunction

import java.text.SimpleDateFormat
import java.util.{Calendar, GregorianCalendar, Date}
import java.util.Calendar._

import org.scalatest.path

class F_AnotherExample {

  private val sdf = new SimpleDateFormat ("MM-dd-yyyy")

  def age: PartialFunction[String, Int] = {
    case "Brenda" => currentAge (sdf.parse ("04-19-1975"))
    case "Srini" => currentAge (sdf.parse ("11-04-2001"))
    case "Bodgan" => currentAge (sdf.parse ("03-21-1961"))
    case "Meiling" => currentAge (sdf.parse ("12-15-2013"))
  }

  private def currentAge (birthDate: Date): Int = {
    val birthCal = new GregorianCalendar ()
    birthCal.setTime (birthDate)
    val nowCal = new GregorianCalendar ()
    val tentativeAge = nowCal.get (YEAR) - birthCal.get (YEAR)
    if (nowCal.get (DAY_OF_YEAR) < birthCal.get (DAY_OF_YEAR)) {tentativeAge - 1} else {tentativeAge}
  }
}

class F_AnotherExampleTest extends path.FunSpec {

  describe ("An age calculator") {
    val subject = new F_AnotherExample ()

    describe ("asked for the ages of people who have had a birthday this year") {
      val brenda = subject.age ("Brenda")
      val bodgan = subject.age.lift ("Bodgan")

      it ("calculates the correct ages") {
        assert (brenda === 40)
        assert (bodgan === Some (54))
      }
    }

    describe ("asked for the ages of people who have not had a birthday this year") {
      val srini = subject.age ("Srini")
      val meiling = subject.age.lift ("Meiling")

      it ("calculates the correct ages") {
        assert (srini === 13)
        assert (meiling === Some (1))
      }
    }

    describe ("asked for the age of unknown people") {
      val jeff = {
        try {
          subject.age ("Jeff")
          None
        }
        catch {
          case e: MatchError => Some (e)
        }
      }
      val dmitri = subject.age.lift ("Dmitri")

      it ("complains appropriately") {
        assert (jeff.get.getMessage () === "Jeff (of class java.lang.String)")
        assert (dmitri === None)
      }
    }
  }
}
