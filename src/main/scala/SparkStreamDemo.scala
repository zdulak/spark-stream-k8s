object SparkStreamDemo extends App {
  import org.apache.spark.sql.SparkSession
  val spark = SparkSession
    .builder()
//    .master("local[*]")
    .appName("SparkStreamDemo")
    .getOrCreate()

  import scala.concurrent.duration.DurationInt
  import org.apache.spark.sql.streaming.Trigger
  val query = spark
    .readStream
    .format("rate")
    .load
    .writeStream
    .format("console")
    .trigger(Trigger.ProcessingTime(500.millisecond))
    .option("truncate", false)
    .start

  query.awaitTermination()
}
