package com.itbank.amazonS3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class S3Utill {
	
	
	private final BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
	
	//bucketName
    private String bucketName = "yeol-gong-study-picture";
    //bucketName getter
    public String getBucketName() {
        return bucketName;
    }
    
    private AmazonS3 conn;

    public S3Utill() {

        this.conn = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)) 
    			.withRegion(Regions.AP_NORTHEAST_2).build();
//        this.conn = AmazonS3ClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()) 
//    			.withRegion(Regions.AP_NORTHEAST_2).build();
    }

    // 버킷 리스트를 가져오는 메서드이다.
    public List<Bucket> getBucketList() {
        return conn.listBuckets();
    }
    // 버킷을 생성하는 메서드이다.
    public Bucket createBucket(String bucketName) {
        return conn.createBucket(bucketName);
    }

    // 폴더 생성 (폴더는 파일명 뒤에 "/"를 붙여야한다.)
    public void createFolder(String bucketName, String folderName) {
        conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
    }
    
    // 파일 업로드
    public void fileUpload(String bucketName, String fileName, File file) throws FileNotFoundException {

        conn.putObject(bucketName, fileName, file);
        System.out.println("업로드 성공");

    }

    // 파일 삭제
    public void fileDelete(String folderName ,String fileName) {

        System.out.println("fileName : " + fileName);
        conn.deleteObject(this.getBucketName() + "/" + folderName, fileName);
        System.out.println("삭제성공");
    }

    // 파일 URL
    public String getFileURL(String bucketName, String fileName) {
        System.out.println("넘어오는 파일명 : "+fileName);
        return conn.getUrl(bucketName, fileName).toString();
    }

}
