import java.io.IOException;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import java.util.Random;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.*;

public class HBaseUtils {
	public  void put(String string) throws IOException { 
		//设置HBase据库的连接配置参数
		Configuration conf = HBaseConfiguration.create(); 
		conf.set("hbase.zookeeper.quorum",  "10.141.211.131:2181");   //Zookeeper的地址
		//conf.set("hbase.zookeeper.property.clientPort", "2181"); 
		Random random = new Random();
		long a = random.nextInt(1000000000);           
		String mytableName = "emp"; 
		String rowkey = "rowkey"+a ;
		String columnFamily = "basicinfo"; 
		String column = "empname"; 
		//String value = string; 
		Connection connection= ConnectionFactory.createConnection(conf);;
		Table table=connection.getTable(TableName.valueOf(mytableName));
		Put put=new Put(Bytes.toBytes(rowkey)); 
		put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(string)); 
		table.put(put);//放入表
		table.close();//释放资源
		}

}
