package com.gini.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.gini.entity.v2.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {

//        CreateTableRequest request = new CreateTableRequest()
//                .withAttributeDefinitions(new AttributeDefinition(
//                        "employeeId", ScalarAttributeType.S))
//
//                .withKeySchema(new KeySchemaElement("employeeId", KeyType.HASH))
//                .withProvisionedThroughput(new ProvisionedThroughput(
//                        10L, 10L))
//                .withTableName("employee");
//
//        CreateTableResult result = buildAmazonDynamoDb().createTable(request);
//        System.out.println(result.getTableDescription().getTableName());


        return new DynamoDBMapper(buildAmazonDynamoDb());
    }


    private AmazonDynamoDB buildAmazonDynamoDb() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("gigelllllllll", "xxxxxxxxx")))
                .build();

    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(){
//        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(
//                        // Configure an instance of the standard client.
//                        DynamoDbClient.builder()
//                                .region(Region.US_EAST_1)
//                                .credentialsProvider(ProfileCredentialsProvider.create())
//                                .build())
//                .build();



 //       return enhancedClient;

        return DynamoDbEnhancedClient.builder().dynamoDbClient(getDynamoDbClient()).build();
    }

    private DynamoDbClient getDynamoDbClient() {
        ClientOverrideConfiguration.Builder overrideConfig =
                ClientOverrideConfiguration.builder();

        return DynamoDbClient.builder()
                //.overrideConfiguration(overrideConfig.build())
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(Region.US_EAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("xxx", "yyy")))
                .build();
    }


    @Bean
    public DynamoDbTable<Employee> employeeTable  (DynamoDbEnhancedClient dynamoDbEnhancedClient){
       return dynamoDbEnhancedClient.table("employee", TableSchema.fromBean(Employee.class));
    }



}
