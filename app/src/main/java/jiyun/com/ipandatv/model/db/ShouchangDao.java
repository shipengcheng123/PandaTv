package jiyun.com.ipandatv.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lenovo on 2017/7/23.
 */
@DatabaseTable(tableName = "InfoTwo")
public class ShouchangDao {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "imageurl")
    private String imageurl;
    @DatabaseField(columnName = "pid")
    private String pid;


    public ShouchangDao() {
    }

    public ShouchangDao(int id, String title, String imageurl, String pid) {
        this.id = id;
        this.title = title;
        this.imageurl = imageurl;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
