package cinesystem;
import artist.*;
import artist.AuxiliaryClass.ArtistComparatorClass;
import artist.AuxiliaryClass.FriendComparatorClass;
import artist.artistComparator.SetComparator;
import artist.artistComparator.SetFriendComparator;
import review.Review;
import review.ReviewClass;
import review.reviewComparators.ReviewComparatorByTypeAndScore;
import show.*;
import show.showComparators.ShowComparatorByName;
import show.showComparators.ShowGenreComparatorByReview;
import show.showComparators.ShowYearComparator;
import user.*;
import exceptions.exceptionsthrows.*;

import java.util.*;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 */
public class CineSystemClass implements CineSystem {
    //Private Variables
    private final SortedMap<String,User> users;
    private final SortedMap<String,Show> shows;
    private final Map<String, Artist> artists;
    //Constants
    private static final int CRITIC = 5;
    private static final int AUDIENCE = 1;

    /**
     * CineSystemClass Constructor
     */
    public CineSystemClass() {
        this.users = new TreeMap<>();
        this.shows = new TreeMap<>();
        this.artists = new HashMap<>();
    }

    @Override
    public void register(String type, String name, String pass) throws UserAlreadyExistException, UserUnknownException{
        if(!Critic.TYPE.equals(type) && !Admin.TYPE.equals(type) && !Audience.TYPE.equals(type)){
            throw new UserUnknownException();
        }
        if(users.containsKey(name)){
            throw new UserAlreadyExistException(name);
        }
        User user = null;
        switch (type){
            case Admin.TYPE -> user = new AdminClass(name, pass);
            case Audience.TYPE -> user = new AudienceClass(name);
            case Critic.TYPE -> user = new CriticClass(name);
        }
        users.put(name,user);
    }

    @Override
   public Iterator<User> usersIterator() throws NoUsersException{
        if(users.isEmpty()){
            throw new NoUsersException();
        }
        return users.values().iterator();
   }

   @Override
   public int uploadMovie(String username, String pass, String title, String director, int duration,
                           String ageCertification, int releaseYear, String[] genres, String[] castMembers) throws
                            AdminDoesntExistException, PasswordIncorrectException, TitleAlreadyExistException {

        User user = users.get(username);
        int counter = 0;

        if (!(user instanceof Admin)){
            throw new AdminDoesntExistException(username);
        }

        if (!((Admin) user).getPassword().equals(pass)){
            throw new PasswordIncorrectException();
        }

        if (shows.containsKey(title)){
            throw new TitleAlreadyExistException(title);
        }

        Admin admin = (Admin) user;

       if (!artists.containsKey(director)){
           counter++;
       }


       Artist directorMovie = searchOrCreateArtist(admin,director);
       directorMovie.addShowParticipatedIn(title);


       Show show = new MovieClass(title,directorMovie,duration,ageCertification,releaseYear);
       return addShow(title, genres, castMembers, counter, admin, show);

   }

   @Override
   public int uploadSeries(String username, String pass, String title, String creator, int nrSeasons,
                           String ageCertification, int releaseYear, String[] genres, String[] castMembers) throws
                            AdminDoesntExistException, PasswordIncorrectException, TitleAlreadyExistException {

       User user = users.get(username);
       int counter = 0;

       if (!(user instanceof Admin)){
           throw new AdminDoesntExistException(username);
       }

       if (!((Admin) user).getPassword().equals(pass)){
           throw new PasswordIncorrectException();
       }

       if (shows.containsKey(title)){
           throw new TitleAlreadyExistException(title);
       }

       Admin admin = (Admin) user;

       if (!artists.containsKey(creator)){
           counter++;
       }

       Artist creatorSeries = searchOrCreateArtist(admin,creator);
       creatorSeries.addShowParticipatedIn(title);


       Show show = new SeriesClass(title,creatorSeries,nrSeasons,ageCertification,releaseYear);
       return addShow(title, genres, castMembers, counter, admin, show);


   }

   @Override
   public Iterator<Show> showsByNameIt() throws NoShowsUploadedException{
        if (shows.isEmpty()){
            throw new NoShowsUploadedException();
        }
       return shows.values().iterator();
   }

   @Override
   public boolean updateBio(String name, String date, String place) throws BioAlreadyAvailableException{
        boolean alreadyExist = false;

        Artist artist;

        if (artists.containsKey(name)){
            artist = artists.get(name);
            alreadyExist = true;
            if (artist.getDate()!=null && artist.getBirthPlace()!=null){
                throw new BioAlreadyAvailableException(name);
            }
        }

        artists.put(name,new ArtistClass(name,date,place));

        return alreadyExist;

   }

   @Override
   public Iterator<Show> showCredits(String name) throws ArtistDoesntExistException{
       if (!artists.containsKey(name)) {
           throw new ArtistDoesntExistException(name);
       }

       SortedSet<Show> filteredArtistShows = new TreeSet<>(new ShowComparatorByName());
       for (Show show : shows.values()) {
           if (show.actorExist(name)) {
               filteredArtistShows.add(show);
           } else {
               if (show instanceof Movie movie) {
                   Artist artist = movie.getDirector();
                   if (artist.getName().equals(name))
                       filteredArtistShows.add(show);
               }
               if (show instanceof Series series) {
                   Artist artist = series.getCreator();
                   if (artist.getName().equals(name))
                       filteredArtistShows.add(show);
               }
           }
       }

       return filteredArtistShows.iterator();

   }

   @Override
   public Artist getArtist(String name) throws ArtistDoesntExistException{
        if (!artists.containsKey(name)){
            throw new ArtistDoesntExistException(name);
        }

       return artists.get(name);
   }

   @Override
   public String getArtistType(String name, Show show){
        String artist = "";
        if (show instanceof Movie){
            artist = show.getArtistType(name);

        } else if (show instanceof Series) {
            artist = show.getArtistType(name);
        }

        return artist;
   }

   @Override
   public int createReview(String username, String title, String comment, String classification) throws
           UserDoesntExistException, AdminNotAllowedException,
           TitleDoesntExistException, TitleAlreadyReviewedByUserException{
        if (!users.containsKey(username)){
            throw new UserDoesntExistException(username);
        }

        User user = users.get(username);

        if (user instanceof Admin){
            throw new AdminNotAllowedException(username);
        }

        if (!shows.containsKey(title)){
            throw new TitleDoesntExistException(title);
        }

        Show show = shows.get(title);

        if (show.userAlreadyReviewed(username)){
            throw new TitleAlreadyReviewedByUserException(username,title);
        }

        show.addReview(new ReviewClass(title,user,comment,classification));
        show.addUserReview(username);
        user.addReview(title);

        return show.getNumberReviews();


   }

   @Override
   public Iterator<Review> showReviews(String title) throws TitleDoesntExistException{
        if (!shows.containsKey(title)){
            throw new TitleDoesntExistException(title);
        }

        SortedSet<Review> showsFileteredIt = new TreeSet<>(new ReviewComparatorByTypeAndScore());

        Iterator<Review> tempIt = shows.get(title).getReviewsIt();
        while (tempIt.hasNext()){
            showsFileteredIt.add(tempIt.next());
        }


        return showsFileteredIt.iterator();
   }

   @Override
   public float getAvgClassification(String title) {
        Show show = shows.get(title);
        float classification;
        float result = 0;
        float numerator = 0;
        float denominator = 0;


        Iterator<Review> reviewIt = show.getReviewsIt();
        if (reviewIt.hasNext()){
            while (reviewIt.hasNext()){
                Review review = reviewIt.next();
                classification = review.getNumericOverall();

                User user = users.get(review.getUser().getName());

                if (user instanceof Critic){
                    denominator += CRITIC;
                    numerator += CRITIC * classification;

                }

                if (user instanceof Audience){
                    denominator += AUDIENCE;
                    numerator += AUDIENCE * classification;

                }
            }

            result = numerator/denominator;
        }


        return result;
   }

   @Override
   public Iterator<Show> getShowsByGenre(String[] genres) throws FailCriteriaException{


        SortedSet<Show> showFilteredByGenre = new TreeSet<>(new ShowGenreComparatorByReview());
        Set<String> genresPoolSet = new HashSet<>();

        int nrGenres = genres.length;


       for (int i = 0; i < genres.length; i++) {
           genresPoolSet.add(genres[i]);
       }

       Iterator<Show> showIt = shows.values().iterator();

       while (showIt.hasNext()){
           int counter = 0;
           Show showTemp = showIt.next();
           Iterator<String> genreIt = showTemp.getGenresIt();

           while (genreIt.hasNext() && counter < nrGenres){
               if (genresPoolSet.contains(genreIt.next()))
                   counter++;
           }

           if (counter == nrGenres){
               showTemp.setAvgReview(getAvgClassification(showTemp.getTitle()));
               showFilteredByGenre.add(showTemp);
           }

       }

       if (!showFilteredByGenre.iterator().hasNext()){
           throw new FailCriteriaException();
       }

       return showFilteredByGenre.iterator();

   }
   @Override
   public boolean isShowMovieType(Show show) {
        if (show instanceof Movie){
            return true;
        } else {
            return false;
        }
   }
   @Override
   public Iterator<Show> getShowsByYear(int year) throws FailCriteriaException{
       SortedSet<Show> showFilteredByYear = new TreeSet<>(new ShowYearComparator());

       Iterator<Show> showIt = shows.values().iterator();

       while (showIt.hasNext()){
           Show showTemp = showIt.next();

           if (showTemp.getReleaseDate()==year){
               showTemp.setAvgReview(getAvgClassification(showTemp.getTitle()));
               showFilteredByYear.add(showTemp);
           }

       }

       if (!showFilteredByYear.iterator().hasNext()){
           throw new FailCriteriaException();
       }
       return showFilteredByYear.iterator();

   }


   @Override
   public Iterator<SortedSet<ArtistComparatorClass>> getAvoiderArtist() throws WithoutArtistsException,
           ProjectsInCommonException{
       SortedSet<SortedSet<ArtistComparatorClass>> filteredAvoidArtist = powerSet();
       List<SortedSet<ArtistComparatorClass>> artistsToRemove = new ArrayList<>();

       if (artists.isEmpty()){
           throw new WithoutArtistsException();
       }

       for (SortedSet<ArtistComparatorClass> artistSortedSet : filteredAvoidArtist) {
           int counter = 0;
           for (ArtistComparatorClass artist: artistSortedSet) {
               for (ArtistComparatorClass otherArtist : artistSortedSet) {
                   if (!artist.getArtist().getName().equals(otherArtist.getArtist().getName())){
                       if (artist.getArtist().checkShowsInCommon(otherArtist.getArtist())>0)
                           counter++;
                   }
               }
           }
           if (artistSortedSet.size()<2) //subset has less than 2 elements
               counter++;

           if (counter>0){
               artistsToRemove.add(artistSortedSet);
           }
       }



       for (SortedSet<ArtistComparatorClass> temp : artistsToRemove) {
           filteredAvoidArtist.remove(temp);
       }


       if (filteredAvoidArtist.isEmpty()){
           throw new ProjectsInCommonException();
       }


       SortedSet<ArtistComparatorClass> firstElement = filteredAvoidArtist.first();

       for (SortedSet<ArtistComparatorClass> avoidArtistSet : filteredAvoidArtist) {
           if (firstElement.size()!=avoidArtistSet.size()){
               if (!artistsToRemove.contains(avoidArtistSet))
                   artistsToRemove.add(avoidArtistSet);
           }

       }

       for (SortedSet<ArtistComparatorClass> temp : artistsToRemove) {
           filteredAvoidArtist.remove(temp);
       }




       return filteredAvoidArtist.iterator();

   }

   @Override
   public Iterator<FriendComparatorClass> getFriendsList() throws WithoutArtistsException, NoCollaborationBetweenArtistsException {
        if (artists.isEmpty()){
            throw new WithoutArtistsException();
        }
        SortedSet<FriendComparatorClass> filteredArtistsFriends = new TreeSet<>(new SetFriendComparator());
        List<FriendComparatorClass> artistsToRemove = new ArrayList<>();
        Set<String> friendsPoolSet = new HashSet<>();

        int numProjects;
       for (Artist artist: artists.values()) {
           for (Artist otherArtist: artists.values()) {
               if (!artist.getName().equals(otherArtist.getName())){
                   numProjects = artist.checkShowsInCommon(otherArtist);
                   FriendComparatorClass contender = new FriendComparatorClass(artist,otherArtist,numProjects);
                   if (!friendsPoolSet.contains(otherArtist.getName())){
                       filteredArtistsFriends.add(contender);
                       friendsPoolSet.add(artist.getName());
                   }

               }
           }
       }

       if (filteredArtistsFriends.isEmpty()){
           throw new NoCollaborationBetweenArtistsException();
       }

       FriendComparatorClass firstFriends = filteredArtistsFriends.first();

       for (FriendComparatorClass friendsSet : filteredArtistsFriends) {
           if (firstFriends.getNrColaborations()!=friendsSet.getNrColaborations())
               artistsToRemove.add(friendsSet);
       }

       for (FriendComparatorClass temp : artistsToRemove) {
           filteredArtistsFriends.remove(temp);
       }


       return filteredArtistsFriends.iterator();


   }


    /**
     * PowerSet that rearrange the Artists HashMap for Avoiders function.
     * @return iterator of subsets of avoiders
     */
   private SortedSet<SortedSet<ArtistComparatorClass>> powerSet(){
       SortedSet<SortedSet<ArtistComparatorClass>> subsets = new TreeSet<>(new SetComparator());
       subsets.add(new TreeSet<>());
       for (Artist artist : artists.values()){
           SortedSet<SortedSet<ArtistComparatorClass>> current = new TreeSet<>(new SetComparator());
           for(SortedSet<ArtistComparatorClass> set : subsets){
               SortedSet<ArtistComparatorClass> newSet = new TreeSet<>(set);
               newSet.add(new ArtistComparatorClass(artist));
               current.add(newSet);
           }
           subsets.addAll(current);
       }
       return subsets;
   }


    /**
     * Use the previous counter to increment in case there's new artists. If the artist is new, will add the show
     * to its hashset where he store all shows where he participated in.
     * @param title title of the show
     * @param genres genre of the show
     * @param castMembers cast members that participates of the show
     * @param counter counter that indicates hom many artist are created.
     * @param admin Account of the Admin
     * @param show Show's Object
     * @return Number of new actors created
     */
    private int addShow(String title, String[] genres, String[] castMembers, int counter, Admin admin, Show show) {
        admin.addShowCreated(title,show);


        for (String tempName: castMembers) {

            if(!(artists.containsKey(tempName))){
                counter++;
            }


            Artist actor = searchOrCreateArtist(admin,tempName);
            actor.addShowParticipatedIn(title);

            show.addActor(actor);
        }

        for (String tempGenres: genres) {
            show.addGenres(tempGenres);
        }
        shows.put(title,show);
        return counter;
    }


    /**
     * Search if the artist already exist. If exist retrieve the information from hashmap of artists.
     * Otherwise, create a new one and is added to the sorted map of artists  and to hashmap created for admin.
     * @param admin Account of the Admin
     * @param artist Name of the Artist
     * @return the artist using the given name
     */

    private Artist searchOrCreateArtist(Admin admin, String artist){
       Artist finalArtist = null;
       boolean doesntExist = true;

       if (artists.containsKey(artist)){
           finalArtist = artists.get(artist);
           doesntExist = false;
       }

       if (doesntExist) {

           finalArtist = new ArtistClass(artist,null,null);
           admin.addArtistCreated(artist, finalArtist);

           artists.put(artist, finalArtist);

       }

       return finalArtist;
   }

}
