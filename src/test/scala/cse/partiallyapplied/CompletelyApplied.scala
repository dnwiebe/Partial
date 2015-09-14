package cse.partiallyapplied

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by dnwiebe on 9/11/15.
 */
class CompletelyApplied {
  val sdf = new SimpleDateFormat ("MM-dd-yyyy")

  val logFunction = {(date: Date, msg: String) =>
    s"${sdf.format (date)}: ${msg}\n"
  }

  def logMethod (date: Date, msg: String): String = {
    s"${sdf.format (date)}: ${msg}\n"
  }
}
