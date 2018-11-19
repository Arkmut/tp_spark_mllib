import org.apache.spark.SparkContext
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.tree.configuration.Algo._
import org.apache.spark.mllib.tree.impurity.Gini
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
    val session = SparkSession.builder
      .appName("SparkSample")
      .master("spark://Spark-1.s0wsc52zzg5utp0cwcnetspzja.ax.internal.cloudapp.net:7077")
      .getOrCreate

    // READ FILES
    val sc = session.sparkContext;
    // Load and parse the data file
    val train = sc.textFile("data/dota2Test.csv")
    val parsedTrain = train.map { line =>
      val parts = line.split(',').map(_.toDouble)
      LabeledPoint(parts(0)*0.5+0.5, Vectors.dense(parts.tail).toSparse) // *0.5+0.5 to avoid negative values
    }
    val test = sc.textFile("data/dota2Test.csv")
    val parsedTest = test.map { line =>
      val parts = line.split(',').map(_.toDouble)
      LabeledPoint(parts(0)*0.5+0.5, Vectors.dense(parts.tail).toSparse)
    }

    println("Here I am! " + parsedTrain.count())


    // BUILD MODEL
    val t0 = System.nanoTime()
    var model = DecisionTree.train(parsedTrain, Classification, Gini, 20)
    val nbIte = args.apply(0).toInt
    for(i <- 2 to nbIte) {
      model = DecisionTree.train(parsedTrain, Classification, Gini, 20)
    }
    val t1 = System.nanoTime()
    println("Time for model : " + (((t1 - t0)/1000000)/nbIte) + "ms")

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

    val t2 = System.nanoTime()
    var valuesAndPreds = parsedTest.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    for(i <- 2 to nbIte) {
      valuesAndPreds = parsedTest.map { point =>
        val prediction = model.predict(point.features)
        (point.label, prediction)
      }
    }
    val t3 = System.nanoTime()
    println("Time for tests : " + (((t3 - t2)/1000000)/nbIte) + "ms")

    val MSE = valuesAndPreds.map{ case(v, p) => math.pow((v - p), 2)}.mean()
    println("training Mean Squared Error = " + MSE)

  }
}
