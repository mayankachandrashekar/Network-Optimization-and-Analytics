import java.text.SimpleDateFormat

import scala.io.Source

/**
  * Created by Mayanka on 01-Sep-16.
  */
object SimpleMovingAvg {
  def main(args: Array[String]): Unit = {

    val data = Source.fromFile("dataset/data.csv")
    val lines = data.getLines().toList
    val date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    val formatedData = lines.map(f => {

      val ff = f.replace("\"", "")
      val s = ff.split(",").map(_.trim)
      val d = s(1)
      //println(s.size)
      (s(0), s(1), s(5), s(6))
    })

    val entites = List("KC-ISNA-Prime31", "KC-DC02", "Kc-csrv-irapora", "KC-ISNT-NetFlow", "kc-csrv-solweb")
    // formatedData.foreach(println(_))
    val filteredData = formatedData.filter(f => {
      if (f._3 == entites(0))
        true
      else
        false
    })
    val sortedData = filteredData.sortBy(_._2)


    var sum = 0.0
    //filteredData.foreach(println(_))
    sortedData.take(10).foreach(f => {
      val ff = f._1.replace("\"", "").replace("\"", "")
      sum += ff.toDouble
      // println(ff)
    })


    val forecastValue = sum / 10
    println("Simple Moving Average:" + forecastValue)

  //  sortedData.take(11).foreach(println(_))

    val sd = sortedData.take(11)
    println(sd(10))

    val actualValue=sd(10)._1
    println("Actual Value :"+actualValue)

    val error = actualValue.toDouble - forecastValue
    println("Error : "+error)
  }

}
