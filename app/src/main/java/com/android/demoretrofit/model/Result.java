package com.android.demoretrofit.model;

import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;

import com.android.demoretrofit.R;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.JvmStatic;

public class Result extends BaseObservable implements Parcelable {

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<Integer> ( );
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @BindingAdapter({"posterPath"})
    @JvmStatic
    public static void loadImage(ImageView imageView, String Imageurl) {
        //создаем метод для загрузки изображений
        String imagePath = "https://image.tmdb.org/t/p/w500/" + Imageurl;
        Glide.with (imageView.getContext ( )).load (imagePath).placeholder (R.drawable.progress_circle).into (imageView);


    }


    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result> ( ) {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(android.os.Parcel in) {
            return new Result (in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    };

    protected Result(android.os.Parcel in) {
        this.adult = ((Boolean) in.readValue ((Boolean.class.getClassLoader ( ))));
        this.backdropPath = ((String) in.readValue ((String.class.getClassLoader ( ))));
        in.readList (this.genreIds, (java.lang.Integer.class.getClassLoader ( )));
        this.id = ((Integer) in.readValue ((Integer.class.getClassLoader ( ))));
        this.originalLanguage = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.originalTitle = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.overview = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.popularity = ((Double) in.readValue ((Double.class.getClassLoader ( ))));
        this.posterPath = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.releaseDate = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.title = ((String) in.readValue ((String.class.getClassLoader ( ))));
        this.video = ((Boolean) in.readValue ((Boolean.class.getClassLoader ( ))));
        this.voteAverage = ((Double) in.readValue ((Double.class.getClassLoader ( ))));
        this.voteCount = ((Integer) in.readValue ((Integer.class.getClassLoader ( ))));
    }

    public Result() {
    }

    @Bindable
    public Boolean getAdult() {
        return adult;
    }
// создаем на сеттерах бр для фиксирования данных для биндинга
    public void setAdult(Boolean adult) {
        this.adult = adult;
        notifyPropertyChanged (BR.adult);
    }

    @Bindable
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        notifyPropertyChanged (BR.backdropPath);
    }

    @Bindable
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
        notifyPropertyChanged (BR.genreIds);
    }

    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        notifyPropertyChanged (BR.id);
    }

    @Bindable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        notifyPropertyChanged (BR.originalLanguage);
    }

    @Bindable
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        notifyPropertyChanged (BR.originalTitle);
    }

    @Bindable
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
        notifyPropertyChanged (BR.overview);
    }

    @Bindable
    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
        notifyPropertyChanged (BR.popularity);
    }

    @Bindable
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        notifyPropertyChanged (BR.posterPath);
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        notifyPropertyChanged (BR.releaseDate);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged (BR.title);
    }

    @Bindable
    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
        notifyPropertyChanged (BR.video);
    }

    @Bindable
    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        notifyPropertyChanged (BR.voteAverage);
    }

    @Bindable
    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        notifyPropertyChanged (BR.voteCount);
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue (adult);
        dest.writeValue (backdropPath);
        dest.writeList (genreIds);
        dest.writeValue (id);
        dest.writeValue (originalLanguage);
        dest.writeValue (originalTitle);
        dest.writeValue (overview);
        dest.writeValue (popularity);
        dest.writeValue (posterPath);
        dest.writeValue (releaseDate);
        dest.writeValue (title);
        dest.writeValue (video);
        dest.writeValue (voteAverage);
        dest.writeValue (voteCount);
    }

    public int describeContents() {
        return 0;

    }

    public static final DiffUtil.ItemCallback<Result> callbac = new DiffUtil.ItemCallback<Result> ( ) {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.id == newItem.id;// создаем метод для нашего адаптера возвращаем калбак туда(если старый айтем равен новому)
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {

            return true;
        }
    };

}