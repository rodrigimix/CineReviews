package show;

import artist.Artist;
import review.Review;

import java.util.Iterator;

public interface Show {
    String getTitle();

    String getAgeCertification();

    int getReleaseDate();

    Iterator<String> getGenresIt();

    Iterator<String> getActors();

    void addGenres(String genre);

    void addActor(Artist artist);

    String getMainGenre();

    boolean actorExist(String name);

    boolean userAlreadyReviewed(String name);

    void addUserReview(String name);

    void addReview(Review review);

    int getNumberReviews();

    Iterator<Review> getReviewsIt();

    void setAvgReview(float value);

    float getAvgReview();

    String getArtistType(String name);
}
