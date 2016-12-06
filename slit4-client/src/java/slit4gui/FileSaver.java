/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4gui;

import DataModel.FileDataModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Adne
 */
public class FileSaver 
{
    public void saveFile(FileDataModel fileDataModel)
    {
        try
        {
            OutputStream out = new FileOutputStream(new File(System.getProperty("user.home"), "Desktop") + "/" + fileDataModel.getFilename());
            out.write(fileDataModel.getContent());
            out.close();
        }
        catch(FileNotFoundException e)
        {
            
        }
        catch(IOException es)
        {
            
        }
    }
}
