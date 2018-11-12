import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.io.Codec;
import scala.io.Source;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {
        SparkSession session = SparkSession.builder()
                .appName("SparkSample")
                .master("spark://DESKTOP-3EM4HH9:7077")
                .getOrCreate();

        session.log().warn("DEBUG: Loading DATA");
        Dataset<Row> rowDataset = loadData(session, args[0]);

        session.log().warn("DEBUG: Data loaded");
        KMean job = new KMean(session);

        rowDataset.show();
        
        job.run(rowDataset
                .toJavaRDD()
                .map((Function<Row, Vector>) row -> (Vector) row.get(0))
        );
        session.log().warn("DEBUG: Job finished");
    }

    public static Dataset<Row> loadData(SparkSession session, String path) {
        session.log().warn(MessageFormat.format("DEBUG: {0}, {1}", session.logName(), path));

        String[] s = Source
                .fromURL(path, Codec.UTF8())
                .mkString()
                .split("\n");
        try {
            PrintWriter out = new PrintWriter("./csvData.csv");

        out.print(s);
        out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       return session.sqlContext().read().format("com.databricks.spark.csv").option("header",true).load("./csvData.csv");
    }
}
