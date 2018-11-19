import org.apache.spark.sql.SparkSession

object App {
  def main(): Unit = {
    exo()
  }

  def test(): Unit = {
    println("Hello, world!")
  }

  def exo(): Unit = {
    val nbIte = 10
  }

  def main(args: Array[String]) {

    //Create a SparkContext to initialize Spark
    val conf = SparkSession.builder
      .appName("SparkSample")
      .master("spark://Spark-1.s0wsc52zzg5utp0cwcnetspzja.ax.internal.cloudapp.net:7077")
      .getOrCreate

    // READ FILES

    val dataFrame = conf.read
      .option("delimiter", ';')
      .csv(args.apply(0))

    println("Here I am! " + dataFrame.count())


    //    // BUILD MODEL
    //    val t0 = System.nanoTime()
    //    var model = DecisionTree.train(dataFrame.toJavaRDD(), Classification, Gini, 20)
    //    for (i <- 2 to nbIte) {
    //      model = DecisionTree.train(parsedTrain, Classification, Gini, 20)
    //    }
    //    val t1 = System.nanoTime()
    //    println("Time for model : " + (((t1 - t0) / 1000000) / nbIte) + "ms")

    /*
        // CHECK PREDICTION
        val labelAndPreds = parsedTrain.map { point =>
          val prediction = model.predict(point.features)
          (point.label, prediction)
        }

        val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / parsedTrain.count
        println("Training Error = " + trainErr)
    */

    // Evaluate model on training examples and compute training error
    /*
        val t2 = System.nanoTime()
        var valuesAndPreds = parsedTest.map { point =>
          val prediction = model.predict(point.features)
          (point.label, prediction)
        }
        for (i <- 2 to nbIte) {
          valuesAndPreds = parsedTest.map { point =>
            val prediction = model.predict(point.features)
            (point.label, prediction)
          }
        }
        val t3 = System.nanoTime()
        println("Time for tests : " + (((t3 - t2) / 1000000) / nbIte) + "ms")

        val MSE = valuesAndPreds.map { case (v, p) => math.pow((v - p), 2) }.mean()
        println("training Mean Squared Error = " + MSE)*/
  }
}
