package dependency.greendao.test.tinder.directional;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by janisharali on 29/08/16.
 */
public class Instance {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("instanceID")
    @Expose
    private String instanceID;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("art")
    @Expose
    private String art;

    @SerializedName("soundEffect")
    @Expose
    private String soundEffect;

    @SerializedName("ambiance")
    @Expose
    private String ambiance;

    @SerializedName("music")
    @Expose
    private String music;

    @SerializedName("chapter")
    @Expose
    private String chapter;

    @SerializedName("storyText")
    @Expose
    private String storyText;

    @SerializedName("positiveText")
    @Expose
    private String positiveText;

    @SerializedName("negativeText")
    @Expose
    private String negativeText;

    @SerializedName("positiveID")
    @Expose
    private String positiveID;

    @SerializedName("negativeID")
    @Expose
    private String negativeID;

    @SerializedName("item")
    @Expose
    private String item;

    @SerializedName("itemKey")
    @Expose
    private String itemKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(String soundEffect) {
        this.soundEffect = soundEffect;
    }

    public String getAmbiance() {
        return ambiance;
    }

    public void setAmbiance(String ambiance) {
        this.ambiance = ambiance;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public String getPositiveID() {
        return positiveID;
    }

    public void setPositiveID(String positiveID) {
        this.positiveID = positiveID;
    }

    public String getNegativeID() {
        return negativeID;
    }

    public void setNegativeID(String negativeID) {
        this.negativeID = negativeID;
    }

    public String getItem() {
        return item;
    }
    public String getItemKey() {
        return itemKey;
    }
}
