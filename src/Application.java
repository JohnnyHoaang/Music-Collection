package src;

import src.entities.*;
import java.io.Console;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {
        Console console = System.console();

        String user = console.readLine("Username: ");
        String password = new String(console.readPassword("Password: "));
        MusicStudio muS = null;
        Scanner scanner = null;
        try {
            Credentials creds = new Credentials(user, password);
            muS = new MusicStudio(creds);
            scanner = new Scanner(System.in);
            //this is the loop for menu
            boolean open = true;
            while (open) {
                //printing all choices
                System.out.println("Welcome to our program");
                System.out.println("Select from the following options:");
                System.out.println("1) VIEW SONG");
                System.out.println("2) ADD DATA");
                System.out.println("3) UPDATE DATA");
                System.out.println("4) DELETE DATA");
                System.out.println("5) EXIT");
                System.out.println("Enter your choice: ");
                var result = scanner.next();

                // View song
                if (result.equals("1")) {
                    // Print all song in the library here
                    muS.printAllAlbums();
                    System.out.println("Which song do you want to view? Pick the song id please...");
                    String songid = scanner.next();
                    // print song info method
                    System.out.println("Here is the information of the song you chose");
                    Album alb = muS.getAlbum(songid);
                    System.out.println("1) VIEW CONTRIBUTORS AND ROLES");
                    System.out.println("2) VIEW RECORDING");
                    System.out.println("3) VIEW THE COLLECTION");

                    String choice = console.readLine("Enter your choice: ");
                    switch (choice) {
                        case "1":
                            //GETS THE CONTRIBUTORS AND ROLES
                            muS.rolesFromContributors(alb.getAlbumid());

                            break;
                        case "2":
                            //GETS THE RECORDINGS
                            System.out.println("RECORDINGS");
                            ArrayList<Recording> recordings = muS.recordingsFromAlbum(alb.getAlbumid());

                            for(int i=0; i<recordings.size(); i++){
                                System.out.println(recordings.get(i));
                            }
                            break;
                        case "3":
                            ///Gets the collections from the ALBUM
                            Collection coll = muS.collectionFromAlbum(alb.getAlbumid());
                            System.out.println("COLLECTION");
                            System.out.println(coll);
                            System.out.println();
                            ArrayList<Album> albums = muS.albumsInCollection(coll.getCollectionId());
                            System.out.println("ALBUMS");
                            for(int i=0; i<albums.size(); i++){
                                System.out.println(albums.get(i));
                            }
                    }

                }
                // option to add data
                else if (result.equals("2")) {
                    System.out.println("1) CREATE CONTRIBUTOR");
                    System.out.println("2) CREATE RECORDING AND ASSIGN TO CONTRIBUTOR");
                    System.out.println("3) CREATE SONG");
                    System.out.println("4) CREATE COLLECTION");
                    String choice = console.readLine("Enter your choice: ");
                    // 1. create contributors
                    switch (choice) {
                        case "1":
                            System.out.println("Creating contributors:");
                            muS.printAllRecContributor();
                            String cid = console.readLine("Pick an UNIQUE contributor id not on the list above | FORMAT (C0xxx): ");
                            String clast = console.readLine("Enter contributor last name: ");
                            String cfirst = console.readLine("Enter contributor firstname: ");
                            Contributor contributor = new Contributor(cid, cfirst, clast);
                            muS.createContributor(contributor);
                            System.out.println("Contributor created: " + contributor);
                            break;
                        case "2":
                        //2. create recordings
                            System.out.println("Creating recording:");
                            muS.printAllRecording();
                            String recid = console.readLine("Enter an UNIQUE RECID not on the list above | FORMAT(RExxx): ");
                            String date = console.readLine("Enter date | FORMAT(yyyy-mm-dd): ");
                            System.out.println("Enter duration(seconds): ");
                            double duration = scanner.nextDouble();
                            System.out.println("Enter offset(seconds): ");
                            double offset = scanner.nextDouble();
                            Recording rec = new Recording(recid, Date.valueOf(date), duration, offset);
                            muS.createRecording(rec);
                            System.out.println("Recording created: " + rec);
                            // creates an object contributor
                            System.out.println("Link this recording to an existing contributor");
                            System.out.println("Here are your contributor choices!");
                            System.out
                                    .println("-----------------------------------------------------------------------");
                            muS.printAllRecContributor();
                            String contributorid = console.readLine("Give the contributor id: ");
                            System.out.println("Here are your role choices!");
                            muS.printAllRoles();
                            String roleid = console.readLine("Give the role id: ");
                            Role role = muS.getRole(roleid);
                            Contributor con = muS.getContributor(contributorid);
                            ContributorRec contributorRec = new ContributorRec(con, rec, role);
                            muS.createContributorRec(contributorRec);
                            System.out.println(
                                    "Successfully linked recording and role to contributor!" + '\n' + contributorRec);
                            break;
                        case "3":
                        //3. create album
                            System.out.println("Creating album");
                            muS.printAllAlbums();
                            String albumid = console.readLine("Enter an unique album id | FORMAT (ALxxx)): ");
                            String title = console.readLine("Enter the title: ");
                            String category = console.readLine("Enter the category: ");
                            String pubdate = console.readLine("Enter the pubdate | FORMAT(yyyy-mm-dd): ");
                            String market = console.readLine("Enter the market: ");
                            String label = console.readLine("Enter the label: ");
                            Album album = new Album(albumid, title, category, Date.valueOf(pubdate), "", market, label);
                            muS.createAlbum(album);
                            // while loop to add recordings to song
                            boolean createCompilationsLoop = true;
                            while (createCompilationsLoop) {
                                muS.printAllRecording();
                                String recordingid = console.readLine("Give recording ids from list above to make song: ");
                                String vdate = console.readLine("Give a date to the compilation | FORMAT(yyyy-mm-dd): ");
                                muS.createCompilation(muS.getRecording(recordingid), album, Date.valueOf(vdate));
                                String answer = console.readLine("Do you want to stop adding compilations to this specific song? (yes/no)");
                                if (answer.equals("yes") || answer.equals("Yes")) {
                                    createCompilationsLoop = false;
                                } else {
                                    System.out.println("Keep mixing your song!");
                                }
                            }
                            System.out.println(muS.getAlbum(album.getAlbumid()));
                            break;
                        case "4":
                        //create collection
                            System.out.println("Creating collection: ");
                            muS.printAllCollection();
                            //ask for user data to create object
                            String collectionId = console.readLine("Enter an unique collection id: ");
                            String name = console.readLine("Enter a name for the collection: ");
                            Collection collection = new Collection(collectionId, name);
                            muS.createCollection(collection);
                            String userResponse = console.readLine("Do you want to add songs to the collection?");
                            boolean collectionLoop = true;
                            while (collectionLoop) {
                                if (userResponse.equals("yes") || userResponse.equals("Yes")) {
                                    // print all songs
                                    System.out.println("Choose what songs you want to add to the collection!");
                                    muS.printAllAlbums();
                                    String givenAlbumId = console.readLine("Give its id: ");
                                    muS.updateTable("album", "collectionid", givenAlbumId,
                                            collection.getCollectionId());
                                    System.out.println("Here are all the collections");
                                    muS.printAllCollection();
                                    String answer = console.readLine("Do you want to stop? (yes/no) ");
                                    if (answer.equals("yes") || answer.equals("Yes")) {
                                        collectionLoop = false;
                                    }
                                    else {
                                        System.out.println("Keep addings songs");
                                    }
                                }
                                else {
                                    collectionLoop= false;
                                }
                            }
                            break;

                    }
                }

                // update song
                else if (result.equals("3")) {
                    // print all tables
                    muS.printAllTables();
                    String table = console.readLine("Which table do you want to update? ");
                    // print all ids of that table
                    muS.printAllIDRowsFromTable(table);
                    String givenId = console.readLine("Enter id of the row that you want to change: ");
                    switch (table) {
                        case "role":
                            System.out.println("Updating: " + muS.getRole(givenId));
                            break;
                        case "recording":
                            System.out.println("Updating: " + muS.getRecording(givenId));
                            break;
                        case "contributor":
                            System.out.println("Updating: " + muS.getContributor(givenId));
                            break;
                    }
                    System.out.println("here are the fields available:");
                    muS.printAllFieldsFromTable(table);
                    String column = console.readLine("Which field do you want to update? ");
                    String newData = console.readLine("Enter new field value: ");
                    muS.updateTable(table, column, givenId, newData);
                    switch (table) {
                        case "role":
                            System.out.println("Updated to : " + muS.getRole(givenId));
                            break;
                        case "recording":
                            System.out.println("Updated to: " + muS.getRecording(givenId));
                            break;
                        case "contributor":
                            System.out.println("Updated to: " + muS.getContributor(givenId));
                            break;
                    }
                    // missing the parse string to double
                    // Log the change here

                }
                // delete data
                else if (result.equals("4")) {
                    System.out.println("What data do you wish to delete? Here are the choices");
                    System.out.println("1) DELETE THE WHOLE SONG");
                    System.out.println("2) DELETE CONTRIBUTOR");
                    System.out.println("3) DELETE COLLECTION");
                    System.out.println("4) DELETE RECORDING");
                    String answer = console.readLine("Pick your choice: ");
                    switch (answer) {
                        case "1":
                            System.out.println("Which song do you want to delete : ");
                            String albumid = console.readLine("Enter the albumd id: ");
                            muS.deleteSong(albumid);
                            break;

                        case "2":
                            // case 2: delete contributor
                            System.out.println("Which contributor do you want to delete : ");
                            String contributorid = console.readLine("Enter the contributor id: ");
                            muS.deleteContributor(contributorid);
                            break;

                        case "3":
                            // case 3: delete collection
                            System.out.println("Which collection do you want to delete?");
                            String collectionid = console.readLine("Enter the collection id: ");
                            // which collection to delete
                            muS.deleteCollection(collectionid);
                            break;

                        case "4":
                            // case 4: delete recording
                            System.out.println("Which recording do you want to delete?");
                            String recid = console.readLine("Enter the recid: ");
                            muS.deleteRecording(recid);
                            break;
                    }
                } else if (result.equals("5")) {
                    open = false;
                    //end loop
                }
            }
            
            Logs logs = muS.getUserLogs();
            System.out.println(logs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            muS.closeConnection();
        }
    }
}