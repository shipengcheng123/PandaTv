package jiyun.com.ipandatv.utils;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by lx on 2017/7/22.
 */

public class CustomDaoManager {
    public static void main(String[] args) throws Exception {
        // 第一个参数为数据库版本
        //第二个参数为数据库的包名
        Schema schema = new Schema(1, "news.db");
        // 创建表,参数为表名
        Entity entity = schema.addEntity("Info");
        // 为表添加字段
        entity.addIdProperty();// 该字段为id
        entity.addStringProperty("image");
        entity.addStringProperty("title");// String类型字段
        entity.addStringProperty("time");
        // 生成数据库相关类
        //第二个参数指定生成文件的本次存储路径,AndroidStudio工程指定到当前工程的java路径
        new DaoGenerator().generateAll(schema, "C:\\Users\\lx\\Desktop\\workspace3\\PandaTv\\app\\src\\main\\java");
    }
}
