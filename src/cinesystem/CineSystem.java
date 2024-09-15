package cinesystem;

import artist.Artist;
import artist.AuxiliaryClass.ArtistComparatorClass;
import artist.AuxiliaryClass.FriendComparatorClass;
import exceptions.exceptionsthrows.*;
import review.Review;
import show.Show;
import user.User;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public interface CineSystem {
    /**
     * Create a new user in the system
     * @param type Type of the User
     * @param name Name of the User
     * @param pass Password if the User is an Admin Type
     * @throws UserAlreadyExistException User Exists in The System
     * @throws UserUnknownException Type selected doest exist in the System
     */
    void register(String type, String name, String pass) throws UserAlreadyExistException, UserUnknownException;

    /**
     * Iterator of the Users exists in the System
     * @return Iterator of the Users
     * @throws NoUsersException Users doesn't exist in the System
     */
    Iterator<User> usersIterator() throws NoUsersException;

    /**
     * Create a Movie and saves the Movie in the System
     * @param username Name of the User
     * @param pass Password of the User
     * @param title Title of the Movie
     * @param director Name of the Director
     * @param duration Time of the Movie
     * @param ageCertification The Age Certification of the Movie
     * @param releaseYear Year that release the Movie
     * @param genres Genre of the Movie
     * @param castMembers List of the Cast Members that participates of the Movie
     * @return Number of Artists are created in the System.
     * @throws AdminDoesntExistException The User isn't the Admin
     * @throws PasswordIncorrectException  The Password given by the User is Incorrect.
     * @throws TitleAlreadyExistException The title of the Movie Already Exists.
     */
    int uploadMovie(String username, String pass, String title, String director, int duration,
                     String ageCertification, int releaseYear, String[] genres, String[] castMembers) throws
                        AdminDoesntExistException, PasswordIncorrectException, TitleAlreadyExistException;

    /**
     * Create a Series and saves the Series in the System
     * @param username Name of the User
     * @param pass Password of the User
     * @param title Title of the Series
     * @param creator Name of the Creator
     * @param nrSeasons Number of the seasons exists in the series
     * @param ageCertification The Age Certification of the Series
     * @param releaseYear Year that release the Series
     * @param genres Genre of the Series
     * @param castMembers List of the Cast Members that participates of the Series
     * @return Number of Artists are created in the System.
     * @throws AdminDoesntExistException The User isn't the Admin
     * @throws PasswordIncorrectException The Password given by the User is Incorrect.
     * @throws TitleAlreadyExistException The title of the Movie Already Exists.
     */
    int uploadSeries(String username, String pass, String title, String creator, int nrSeasons,
                     String ageCertification, int releaseYear, String[] genres, String[] castMembers) throws
                             AdminDoesntExistException, PasswordIncorrectException, TitleAlreadyExistException;

    /**
     * Iterator of Shows that it is sorted by name
     * @return Iterator of Shows
     * @throws NoShowsUploadedException Doesn't exist any shows in the system
     */
    Iterator<Show> showsByNameIt() throws NoShowsUploadedException;

    /**
     * Update the biography of the Artist
     * @param name Name of the Artist
     * @param date Date of his anniversary
     * @param place Place of Birth
     * @return boolean - if the Artist Exists.
     * @throws BioAlreadyAvailableException Biography already exists in the Artist.
     */
    boolean updateBio(String name, String date, String place) throws BioAlreadyAvailableException;

    /**
     * Shows the profile of the artist and the movies/series that participates.
     * @param name Name of the Artist
     * @return Iterator of Credits
     * @throws ArtistDoesntExistException The artist doesn't exist in the system
     */
    Iterator<Show> showCredits(String name) throws ArtistDoesntExistException;

    /**
     * Return the Artist indicated by the User
     * @param name Name of the Artist
     * @return the Artist indicated by the User
     * @throws ArtistDoesntExistException
     */
    Artist getArtist(String name) throws ArtistDoesntExistException;

    /**
     * Return the Type of Artist
     * @param name Name of the Artist
     * @param show Show that Artist Participates
     * @return Type of the Artist
     */
    String getArtistType(String name, Show show);

    /**
     * Create a Review and Saves in the System
     * @param username Name of the User
     * @param title Name of the Title of the Show
     * @param comment Comment given by the user about a show
     * @param classification The rating of the show given by the user
     * @return Number of total reviews made it about the show
     * @throws UserDoesntExistException The User Doest Exists In the System
     * @throws AdminNotAllowedException The Admin isn't allow to review a show
     * @throws TitleDoesntExistException The Show doest exist in the System
     * @throws TitleAlreadyReviewedByUserException The Show is already reviewed by the User
     */
    int createReview(String username, String title, String comment, String classification) throws
            UserDoesntExistException, AdminNotAllowedException,
            TitleDoesntExistException, TitleAlreadyReviewedByUserException;

    /**
     * Iterator of Reviews, sorted by classification
     * @param title Name of the Show
     * @return Iterator of Reviews
     * @throws TitleDoesntExistException The Show doesn't exist in the System.
     */
    Iterator<Review> showReviews(String title) throws TitleDoesntExistException;

    /**
     * Returns the average score about the Show
     * @param title Name of the Show
     * @return the average score about the Show
     */
    float getAvgClassification(String title);

    /**
     * Iterator of Shows by Genre, Sorted By Average Score
     * @param genres Lists of genres selected by the User
     * @return Iterator of Shows
     * @throws FailCriteriaException Genre select by the user doest exist.
     */
    Iterator<Show> getShowsByGenre(String[] genres) throws FailCriteriaException;

    /**
     * Indicates if the show is a Movie Type or Series Type.
     * @param show Show's Object
     * @return True - if the shoe is an Movie / false - if is a Series
     */
    boolean isShowMovieType(Show show);

    /**
     * Iterator of Shows that is releaced by that Year, sorted by Average Score
     * @param year Year indicated by the User
     * @return Iterator of Shows
     * @throws FailCriteriaException The Year indicated by the user doesn't exist in any shows.
     */
    Iterator<Show> getShowsByYear(int year) throws FailCriteriaException;

    /**
     * Iterator of Artists that have never participated in a common movie or series, sorted alphabetically.
     * @return Iterator of Artists
     * @throws WithoutArtistsException No artist exists in the System
     * @throws ProjectsInCommonException  All artists have common projects we will write
     */
    Iterator<SortedSet<ArtistComparatorClass>> getAvoiderArtist() throws WithoutArtistsException, ProjectsInCommonException;

    /**
     * Iterator of the pairs of artists that have more projects (movies or series) together.
     * @return Iterator of Artists
     * @throws WithoutArtistsException  No Artist exists in the System
     * @throws NoCollaborationBetweenArtistsException No collaborations exist between any Artists
     */
    Iterator<FriendComparatorClass> getFriendsList() throws WithoutArtistsException, NoCollaborationBetweenArtistsException;

}
