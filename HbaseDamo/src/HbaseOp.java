import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

public class HbaseOp{
	public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    
	 //��������
    public static void init(){
        configuration  = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum",  "10.141.211.131:2181,10.141.211.132:2181,10.141.211.133:2181,10.141.211.134:2181,10.141.211.135:2181");
        try{
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    //�ر�����
    public static void close(){
        try{
            if(admin != null){
                admin.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * ����HBase�ı��л���һ��ϵͳĬ�ϵ�������Ϊ�����������������д�����Ĭ��Ϊput��������б������һ�����ݣ���˴˴����贴��id��
     * @param myTableName ����
     * @param colFamily ������
     * @throws IOException
     */
    public static void createTable(String myTableName,String[] colFamily) throws IOException {
    	 
        init();
        TableName tableName = TableName.valueOf(myTableName);
 
        if(admin.tableExists(tableName)){
            System.out.println("talbe is exists!");
            for(String str:colFamily){
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(str);
                admin.addColumn(tableName, hColumnDescriptor);
            }
            
        }else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for(String str:colFamily){
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(str);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
            System.out.println("create table success");
        }
        close();
    }
    
    /**
     * ��ĳһ�е�ĳһ�в�������
     * @param tableName ����
     * @param rowKey �м�
     * @param colFamily ������
     * @param col �����������������û�����У��˲�����Ϊ�գ�
     * @param val ֵ
     * @throws IOException
     */
    public static void insertRow(String tableName,String rowKey,String colFamily,String col,String val) throws IOException {
        init();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes());
        put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
        table.put(put);
        table.close();
        close();
    }
}