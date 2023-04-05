package sh4k4w4t.github.io.seo_expate_bangladesh_task.Services.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostStructureModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}