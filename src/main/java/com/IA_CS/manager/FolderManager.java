/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IA_CS.manager;


import com.IA_CS.config.Settings;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class FolderManager {

    String userDirectory = Settings.USER_PATH;
    
    Path mainDirectory = Paths.get ( userDirectory, ".IA_CS" );
    Path configDirectory = mainDirectory.resolve ( "config" );

    public FolderManager () {
    }

    public void createCoreFilesAndDirectories () {
        try {
            //Create core directories
            if ( !Files.exists ( mainDirectory ) ) {
                
                Files.createDirectories ( configDirectory );

                System.out.println ( "Main directories created successfully" );
            } else {
                System.out.println ( "Main directory already exists" );
            }

            Path dbFile = configDirectory.resolve ( "config.db" );
            if ( !Files.exists ( dbFile ) ) {
                Files.createFile ( dbFile );
               
                System.out.println ( "config.db file created in config directory" );
            } else {
                System.out.println ( "config.db file already exists" );
            }
        } catch (Exception e) {
            System.out.println ( "An error occurred: " + e.getMessage () );
            e.printStackTrace ();
        }
    }
}
