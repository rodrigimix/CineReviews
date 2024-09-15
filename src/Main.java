import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;

import artist.Artist;
import artist.AuxiliaryClass.ArtistComparatorClass;
import artist.AuxiliaryClass.FriendComparatorClass;
import cinesystem.*;
import exceptions.exceptionsthrows.*;
import review.Review;
import show.Movie;
import show.Series;
import show.Show;
import user.*;

/**
 * @author David Pinto (64609) & Rodrigo Marques (64411)
 * It enables the relation between the user and the program, through the inputs received and the outputs which will
 * be displayed. This program consists in an online review  application that collects reviews from movie critics and
 * audiences to determine a movie or serie's overaal score.
 */
public class Main {
    /**
     * Command allows navigating to the different commands allowed by the program, and have also their functionalities.
     */
    private enum Command {

        REGISTER("register - registers a user in the system"),
        USERS("users - lists all registered users"),
        MOVIE("movie - uploads a new movie"),
        SERIES("series - uploads a new series"),
        SHOWS("shows - lists all shows"),
        ARTIST("artist - adds bio information about an artist"),
        CREDITS("credits - lists the bio and credits of an artist"),
        REVIEW("review - adds a review to a show"),
        REVIEWS("reviews - lists the reviews of a show"),
        GENRE("genre - lists shows of given genres"),
        RELEASED("released - lists shows released in a given year"),
        AVOIDERS("avoiders - lists artists that have no common projects"),
        FRIENDS("friends - lists artists that have more projects together"),
        HELP("help - shows the available commands"),
        EXIT("exit - terminates the execution of the program");

        // The output message
        private final String inputCommand;

        /**
         * Create an enumerated and send its respective message
         * @param inputCommand input of the command selected by the user
         */
        Command(String inputCommand){this.inputCommand = inputCommand;}

        /**
         * Checks if the command selected by the user exists in the Commnad enumerated
         * @param command Existing command in enumerated
         * @param cmd User selected command
         * @return true if the command selected by the user is equal and false the opposite
         * @throws IllegalArgumentException  if doesn't found the valueOf(cmd) and returns false.
         */
        public static boolean equals(Command command, String cmd){
            try {
                return Command.valueOf(cmd).equals(command);
            }catch (IllegalArgumentException e){
                return false;
            }
        }
    }

    /**
     * Messages which will be printed as result of command that was inserted.
     */
    private enum OutputMessages {

        REGISTER_USER("User %s was registered as %s.\n"),
        ALL_REGISTERED_USER("All registered users:"),
        MOVIE_UPLOADED("Movie %s (%d) was uploaded [%d new artists were created].\n"),
        SERIES_UPLOADED("Series %s (%d) was uploaded [%d new artists were created].\n"),
        ALL_SHOWS("All shows:"),
        BIO_ARTIST_UPDATE("%s bio was updated.\n"),
        BIO_ARTIST_CREATE("%s bio was created.\n"),
        ADD_REVIEW("Review for %s was registered [%d reviews].\n"),
        REVIEW_SHOW("Reviews of %s [%.1f]:\n"),
        REVIEW_USER("Review of %s (%s): %s [%s]\n"),
        TITLE_WITHOUT_REVIEWS("Show %s has no reviews.\n"),
        GENRE_LIST("Search by genre:"),
        MOVIE_LIST("Movie %s by %s released on %d [%.1f]\n"),
        SERIES_LIST("Series %s by %s released on %d [%.1f]\n"),
        SHOW_RELEASED("Shows released on %d:\n"),
        AVOIDERS_ARTISTS("These %d artists never worked together:\n"),
        FRIENDS_WORK_TOGETHER("These artists have worked on %d projects together:\n"),
        UNKNOWN("Unknown command. Type help to see available commands."),
        ADMIN_ITERATOR("Admin %s has uploaded %d shows\n"),
        USER_ITERATOR("User %s has posted %d reviews\n"),
        FRIENDS_FORMAT("%s and %s\n"),
        CREDITS_FORMAT("%s; %d; %s [%s]\n"),
        EXITING("Bye!");
        // The output message
        private final String message;

        /**
         * Create an enumerated and send its respective message
         * @param message input of the command selected by the user
         */
        OutputMessages(String message){ this.message = message; }
    }

    /**
     * Method main creates the Scanner that allows the user to interact with
     * the program and creates the CineSystem that allows manipulating the data of that object.
     * @param args Streaming I/O Standard of Java.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CineSystem cineSystem = new CineSystemClass();
        getCommand(in, cineSystem);
        in.close();
    }

    /**
     * Using the scanner, it reads the information given
     * by the terminal and manipulates the given data to select the command.
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    public static void getCommand(Scanner in, CineSystem cineSystem){
        String command = null;
        do{
            try {
                command = in.next().toUpperCase().trim();
                commandCenter(in, cineSystem, Command.valueOf(command));
            }catch (IllegalArgumentException e){
                System.out.println(OutputMessages.UNKNOWN.message);
            }

        }while (!Command.equals(Command.EXIT, command));
    }

    /**
     * Selects the command given by the user and executes their functionality.
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     * @param command command given by the user.
     */
    private static void commandCenter(Scanner in,CineSystem cineSystem, Command command){
        switch (command) {
            case HELP -> listHelpCommands();
            case REGISTER -> register(in,cineSystem);
            case USERS -> usersList(cineSystem);
            case MOVIE -> uploadNewMovie(in,cineSystem);
            case SERIES -> uploadNewSeries(in,cineSystem);
            case SHOWS -> showsList(cineSystem);
            case ARTIST -> addBio(in,cineSystem);
            case CREDITS -> creditsList(in,cineSystem);
            case REVIEW -> addReview(in,cineSystem);
            case REVIEWS -> reviewsList(in,cineSystem);
            case GENRE -> showsByGenresList(in,cineSystem);
            case RELEASED -> showsByReleaseYearList(in,cineSystem);
            case AVOIDERS -> avoiderArtists(cineSystem);
            case FRIENDS -> getFriends(cineSystem);
            case EXIT -> System.out.println(OutputMessages.EXITING.message);
        }
    }

    /**
     * Shows the functionality of all the functions of the program.
     */
    private static void listHelpCommands(){
        for (Command temp:Command.values()) {
            System.out.println(temp.inputCommand);
        }
    }

    /**
     * The register function allows registering
     * a user in CineReview using the different types available (critic, audience, admin)
     * It receives as input the user type, the username, and in case is an admin receives the password too.
     *
     * This function give the output of failure in case:
     * - The user is unknown (UserUnknownException)
     * - The user already exist (UserAlreadyExistException)
     *
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     * @pre type != null && user != null && pass != null
     */
    private static void register(Scanner in, CineSystem cineSystem){
        String type = in.next().toUpperCase();
        String user = in.next();
        String pass = null;

        if(type.equals(Admin.TYPE)){
            pass = in.next();
        }

        try {
            cineSystem.register(type,user,pass);
            System.out.printf(OutputMessages.REGISTER_USER.message, user, type.toLowerCase());
        }catch (UserUnknownException | UserAlreadyExistException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * The usersList function lists all registered users.
     * This command always succeeds and print all users alphabetically ordered by identifier.
     * If there are no registered users it will print an appropriate message (NoUsersException).
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */

    private static void usersList(CineSystem cineSystem){
        try {
            Iterator<User> it = cineSystem.usersIterator();
            System.out.println(OutputMessages.ALL_REGISTERED_USER.message);
            while (it.hasNext()){
                User user = it.next();
                if(user instanceof Admin){
                    System.out.printf(OutputMessages.ADMIN_ITERATOR.message,user.getName(),((Admin) user).getNumberShows());
                }
                else {
                    System.out.printf(OutputMessages.USER_ITERATOR.message,user.getName(),user.numberReviewsCreated());
                }
            }
        }catch (NoUsersException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * The uploadNewMovie function allows to upload a new movie
     * It receives as input the username, the password, the movie title, the director's name, the duration,
     * the age certification, the year of release, the number of genres, the movie genres, the number of cast members,
     * and the name of each of the cast members.
     *
     * This function give the output of failure in case:
     * - The admin does not exist or is not an admin user (AdminDoesntExistException)
     * - The password is wrong (PasswordIncorrectException)
     * - The movie title already exists in the application (TitleAlreadyExistException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     * @pre admin != null && pass != null && title != null && director != null && duration >= 0 &&
     *      ageCertification != null && releaseYear >= 0 && nrGenres >= 1 && genres != null && nrCastMembers >= 1 &&
     *      castMembers != null
     */

    private static void uploadNewMovie(Scanner in, CineSystem cineSystem){
        String admin = in.next().trim();
        String pass = in.nextLine().trim();
        String title = in.nextLine().trim();
        String director = in.nextLine().trim();
        int duration = Integer.parseInt(in.nextLine());
        String ageCertification = in.nextLine().trim();
        int releaseYear = Integer.parseInt(in.nextLine());
        int nrGenres = Integer.parseInt(in.nextLine());
        int counter = 0;
        String [] genres = new String[nrGenres];

        while (counter < nrGenres){
            genres[counter++] = in.nextLine().trim();
        }

        int nrCastMembers = Integer.parseInt(in.nextLine());

        String[] castMembers = new String[nrCastMembers];
        counter = 0;

        while (counter< nrCastMembers){
            castMembers[counter++] = in.nextLine().trim();
        }

        try {
            counter = cineSystem.uploadMovie(admin,pass,title,director,duration,ageCertification,releaseYear,genres,castMembers);
            System.out.printf(OutputMessages.MOVIE_UPLOADED.message,title,releaseYear,counter);

        } catch (AdminDoesntExistException | PasswordIncorrectException | TitleAlreadyExistException e){
            System.out.println(e.getMessage());

        }
    }

    /**
     * The uploadNewSeries function allows to upload a new series
     * It receives as input the username, the password, the movie title, the creator's name, the number of seasons,
     * the age certification, the year of release, the number of genres, the series genres, the number of cast members,
     * and the name of each of the cast members.
     *
     * This function give the output of failure in case:
     * - The admin does not exist or is not an admin user (AdminDoesntExistException)
     * - The password is wrong (PasswordIncorrectException)
     * - The movie title already exists in the application (TitleAlreadyExistException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     * @pre admin != null && pass != null && title != null && creator != null && nrSeasons >= 1 &&
     *      ageCertification != null && releaseYear >= 0 && nrGenres >= 1 && genres != null && nrCastMembers >= 1 &&
     *      castMembers != null
     */
    private static void uploadNewSeries(Scanner in, CineSystem cineSystem){
        String admin = in.next().trim();
        String pass = in.nextLine().trim();
        String title = in.nextLine().trim();
        String creator = in.nextLine().trim();
        int nrSeasons = Integer.parseInt(in.nextLine());
        String ageCertification = in.nextLine().trim();
        int releaseYear = Integer.parseInt(in.nextLine());
        int nrGenres = Integer.parseInt(in.nextLine());
        int counter = 0;
        String [] genres = new String[nrGenres];

        while (counter < nrGenres){
            genres[counter++] = in.nextLine().trim();
        }

        int nrCastMembers = Integer.parseInt(in.nextLine());

        String[] castMembers = new String[nrCastMembers];
        counter = 0;

        while (counter < nrCastMembers){
            castMembers[counter++] = in.nextLine().trim();
        }

        try{
            counter = cineSystem.uploadSeries(admin,pass,title,creator,nrSeasons,ageCertification,releaseYear,genres,castMembers);
            System.out.printf(OutputMessages.SERIES_UPLOADED.message,title,releaseYear,counter);
        } catch (AdminDoesntExistException | PasswordIncorrectException | TitleAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The shows function lists all shows exists in the System.
     * This command always succeeds and print all users alphabetically ordered by identifier.
     * If there are no registered shows it will print an appropriate message (NoShowsUploadedException).
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void showsList(CineSystem cineSystem){
        try {
            Iterator<Show> showIt = cineSystem.showsByNameIt();
            System.out.println(OutputMessages.ALL_SHOWS.message);
            while (showIt.hasNext()){
                Show show = showIt.next();
                System.out.println(show);
            }
        } catch (NoShowsUploadedException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This function create artist or updates information for a certain artist.
     * It receives as input the name of the artist, the date of birth and the place where he was born.
     *
     * This function give the output of failure in case:
     *      - Information about the artist already exists (BioAlreadyAvailableException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void addBio (Scanner in, CineSystem cineSystem){
        String name = in.nextLine().trim();
        String date = in.nextLine().trim();
        String place = in.nextLine().trim();

        try {
            boolean cond=cineSystem.updateBio(name,date,place);

            if (cond){
                System.out.printf(OutputMessages.BIO_ARTIST_UPDATE.message,name);
            } else {
                System.out.printf(OutputMessages.BIO_ARTIST_CREATE.message,name);
            }

        } catch (BioAlreadyAvailableException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The shows function lists all the shows that specific artist participated in the System.
     * It receives as input the artist name.
     * This function give the output of failure in case:
     *  - The artist does not exist (ArtistDoesntExistException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void creditsList (Scanner in, CineSystem cineSystem){
        String name = in.nextLine().trim();

        try {
            Artist artist = cineSystem.getArtist(name);
            if (artist.getDate()!=null)
                System.out.println(artist.getDate());

            if (artist.getBirthPlace()!=null)
                System.out.println(artist.getBirthPlace());

            Iterator<Show> showIt = cineSystem.showCredits(name);
            while (showIt.hasNext()){
                Show show = showIt.next();
                String creditType = cineSystem.getArtistType(name,show);
                String type = "";
                if (show instanceof Movie movie){
                    type = movie.TYPE.toLowerCase();
                } else if (show instanceof Series series){
                    type = series.TYPE.toLowerCase();
                }
                System.out.printf(OutputMessages.CREDITS_FORMAT.message,show.getTitle(),show.getReleaseDate(),
                        creditType.toLowerCase(), type);
            }

        } catch (ArtistDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function adds a review about a show.
     * It has as arguments, username, title of the show, comment about the show and its classification.
     *
     * This function give the output of failure in case:
     * - The User does not exist (UserDoesntExistException)
     * - The Admin is not allow to review (AdminNotAllowedException)
     * - The show is already reviewed by the user (TitleAlreadyReviewedByUserException)
     * - The show doesn't exist in the system (TitleDoesntExistException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void addReview(Scanner in, CineSystem cineSystem){
        String username = in.next().trim();
        String title = in.nextLine().trim();
        String comment = in.nextLine().trim();
        String classification = in.nextLine().trim();

        try {
            int numberReviews = cineSystem.createReview(username,title,comment,classification);
            System.out.printf(OutputMessages.ADD_REVIEW.message,title,numberReviews);
        }catch (AdminNotAllowedException | UserDoesntExistException
                | TitleAlreadyReviewedByUserException | TitleDoesntExistException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * The reviewsList function lists all the reviews that specific show in the System.
     * Receives as argument the title of the show.
     *
     * This function give the output of failure in case:
     * - The show doesn't exist in the system (TitleDoesntExistException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void reviewsList(Scanner in, CineSystem cineSystem){
        String title = in.nextLine().trim();

        try {
            Iterator<Review> reviewsIt = cineSystem.showReviews(title);
            float avgResult = cineSystem.getAvgClassification(title);

            if (!reviewsIt.hasNext()){
                System.out.printf(OutputMessages.TITLE_WITHOUT_REVIEWS.message,title);
            } else {
                System.out.printf(OutputMessages.REVIEW_SHOW.message,title,avgResult);

                while (reviewsIt.hasNext()){
                    Review review = reviewsIt.next();
                    User user = review.getUser();
                    System.out.printf(OutputMessages.REVIEW_USER.message,user.getName(),user.getType().toLowerCase(),
                            review.getComment(),review.getOverall());
                }
            }

        } catch (TitleDoesntExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The showsByGenresList function lists all the shows that specifics genres in the System.
     * Receive as argument number os genres as size of array and the name os the genres selected by the user.
     *
     * This function give the output of failure in case:
     * - The genre doesn't exist in the system (FailCriteriaException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void showsByGenresList(Scanner in, CineSystem cineSystem){
        int nrGenres = in.nextInt(); in.nextLine();
        int counter = 0;
        String [] genres = new String[nrGenres];

        while (counter < nrGenres){
            genres[counter++] = in.nextLine().trim();
        }

        try {
            Iterator<Show> showsIt = cineSystem.getShowsByGenre(genres);
            System.out.println(OutputMessages.GENRE_LIST.message);
            genderAndReleaseYearAux(cineSystem, showsIt);

        } catch (FailCriteriaException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * The showsByReleaseYearList function lists all the shows that specifics year of release in the System.
     * Receive as argument year of release.
     *
     * This function give the output of failure in case:
     * - The year doesn't exist in the system (FailCriteriaException)
     * @param in scans the information given by the terminal.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void showsByReleaseYearList(Scanner in, CineSystem cineSystem){
        int year = in.nextInt(); in.nextLine();

        try{
            Iterator<Show> showsIt = cineSystem.getShowsByYear(year);
            System.out.printf(OutputMessages.SHOW_RELEASED.message,year);
            genderAndReleaseYearAux(cineSystem, showsIt);

        } catch (FailCriteriaException e){
            System.out.println(e.getMessage());
        }

    }
    /**
     * The avoiderArtists function lists all the
     * Artists that have never participated in a common movie or series, sorted alphabetically.
     *
     * This function give the output of failure in case:
     * - No artist exists in the System (WithoutArtistsException)
     * - All artists have common projects we will write (ProjectsInCommonException)
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void avoiderArtists(CineSystem cineSystem){

        try {
            Iterator<SortedSet<ArtistComparatorClass>> artistSetIt = cineSystem.getAvoiderArtist();
            int enableFirstOutput = 0;

            while (artistSetIt.hasNext()){
                int size = 0;
                String avoiderOutput = "";
                SortedSet<ArtistComparatorClass> artistSet = artistSetIt.next();

                for (ArtistComparatorClass artist: artistSet) {
                    if (size++ > 0 )
                        avoiderOutput += ", ";

                    Artist temp = artist.getArtist();
                    avoiderOutput += temp.getName();
                }
                if (enableFirstOutput++ == 0){
                    System.out.printf(OutputMessages.AVOIDERS_ARTISTS.message,size);
                }
                System.out.println(avoiderOutput);
            }

        } catch (WithoutArtistsException | ProjectsInCommonException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * The getFriends function lists all
     * the pairs of artists that have more projects (movies or series) together.
     *
     * This function give the output of failure in case:
     * - No artist exists in the System (WithoutArtistsException)
     * - No collaborations exist between any Artists (NoCollaborationBetweenArtistsException)
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     */
    private static void getFriends(CineSystem cineSystem){
        try{
            int enableFirstOutput = 0;
            Iterator<FriendComparatorClass> friendsIt = cineSystem.getFriendsList();

            while (friendsIt.hasNext()){
                FriendComparatorClass temp = friendsIt.next();
                if (enableFirstOutput++==0)
                    System.out.printf(OutputMessages.FRIENDS_WORK_TOGETHER.message,temp.getNrColaborations());

                System.out.printf(OutputMessages.FRIENDS_FORMAT.message,temp.getFirstArtist().getName(),
                                    temp.getSecondArtist().getName());
            }

        } catch (WithoutArtistsException | NoCollaborationBetweenArtistsException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Auxiliary Function that make a specific output if the show is a Movie or a Series.
     * @param cineSystem object that permits manipulating the data of CineSystemClass.
     * @param showsIt iterator of shows
     */
    private static void genderAndReleaseYearAux(CineSystem cineSystem, Iterator<Show> showsIt) {
        while (showsIt.hasNext()){
            Show showTemp = showsIt.next();
            if (cineSystem.isShowMovieType(showTemp)){
                Movie movie = (Movie) showTemp;
                System.out.printf(OutputMessages.MOVIE_LIST.message,showTemp.getTitle(),
                        movie.getDirector().getName(), showTemp.getReleaseDate(),showTemp.getAvgReview());
            } else {
                Series series = (Series) showTemp;
                System.out.printf(OutputMessages.SERIES_LIST.message,showTemp.getTitle(),
                        series.getCreator().getName(), showTemp.getReleaseDate(),showTemp.getAvgReview());
            }
        }
    }

}
