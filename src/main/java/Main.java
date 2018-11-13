import org.apache.spark.api.java.JavaRDD;
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
        JavaRDD<String> rowDataset = loadData(session);

        session.log().warn("DEBUG: Data loaded");
        KMean job = new KMean(session);

        //rowDataset.show();
        
        /*job.run(rowDataset
                .map((Function<Row, Vector>) row -> (Vector) row.get(0))
        );*/
        session.log().warn("DEBUG: Job finished");
    }

    public static JavaRDD<String> loadData(SparkSession session) {
        JavaRDD<String> data= session.sparkContext().textFile("file:///home/embraser01/data/data.csv",1).toJavaRDD();
        return data;
    }
}
