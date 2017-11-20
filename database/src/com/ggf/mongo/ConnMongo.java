package com.ggf.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PanYuanJin on 2017/11/16.
 */
public class ConnMongo {
    public static void main(String[] args) {

    }

    /**
     * 连接MongoDB
     */
    public static List<String> mongoConn() {
        MongoClient client=new MongoClient("192.168.0.20", 27017);
        MongoDatabase data=client.getDatabase("N_JCJBaseData");
        MongoCollection<Document> collection=data.getCollection("COMPANY_BASEINFO");
        FindIterable<Document> cursor = collection.find(Filters.eq("清洗人员",null)).noCursorTimeout(true).batchSize(30);
        int num = 0;
        List<String> dataList = new ArrayList<String>();
        for (Document doc : cursor){
            num++;
            String company = doc.getString("企业名称");
            dataList.add(company);
        }
        System.out.println(num);
        client.close();
        return dataList;
    }

}
