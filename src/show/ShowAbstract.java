package show;

import artist.Artist;
import review.Review;

import java.util.*;

public abstract class ShowAbstract implements Show{
    //Private Variables
    private String title;
    String ageCertification;
    int releaseDate;
    float avgReview;
    private final List<String> genres;
    private final List<String> topCast;
    private final List<Review> reviews;
    private final Set<String> usersReviewed;
    //Constants
    private static final float DEFAULT_VALUE = 0;

    protected ShowAbstract(String title, String ageCertification, int releaseDate){
        this.title = title;
        this.ageCertification = ageCertification;
        this.releaseDate = releaseDate;
        this.avgReview = DEFAULT_VALUE;
        this.genres = new ArrayList<>();
        this.topCast = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.usersReviewed = new HashSet<>();
    }

    @Override
    public String getTitle(){ return title; }
    @Override
    public String getAgeCertification() { return  ageCertification; }
    @Override
    public int getReleaseDate() { return  releaseDate; }
    @Override
    public Iterator<String> getGenresIt() { return genres.iterator(); }
    @Override
    public Iterator<String> getActors() { return topCast.iterator(); }
    @Override
    public void addGenres(String genre){genres.add(genre); }
    @Override
    public void addActor(Artist artist){
        topCast.add(artist.getName());
    }
    @Override
    public String getMainGenre(){ return genres.get(0); }
    @Override
    public boolean actorExist(String name){ return topCast.contains(name); }
    @Override
    public boolean userAlreadyReviewed(String name) { return usersReviewed.contains(name); }
    @Override
    public void addUserReview(String name) { usersReviewed.add(name); }
    @Override
    public void addReview(Review review) { reviews.add(review); }
    @Override
    public int getNumberReviews() { return reviews.size(); }
    @Override
    public Iterator<Review> getReviewsIt() { return  reviews.iterator(); }
    @Override
    public void setAvgReview(float value) { this.avgReview = value;}
    @Override
    public float getAvgReview() { return avgReview; }
    public abstract String toString();
    public abstract String getArtistType(String name);
}
