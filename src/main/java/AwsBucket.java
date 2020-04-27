import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AwsBucket {


    public static void main(String[] args) throws IOException {
        Regions clientRegion = Regions.US_EAST_2;

        String Bucket = "javacall";
        String file = "Hello World.txt";

        BasicAWSCredentials aws = new BasicAWSCredentials("", "");
        AmazonS3 s3C = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(aws))
                .withRegion(clientRegion)
                .build();
        S3Object s3client = s3C.getObject(new GetObjectRequest(Bucket, file));
        System.out.println("Content-Type: " + s3client.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        displayTextInputStream(s3client.getObjectContent());

    }
    public static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = br.readLine()) !=null){
            System.out.println(line);
        }
        System.out.println();
    }

}
