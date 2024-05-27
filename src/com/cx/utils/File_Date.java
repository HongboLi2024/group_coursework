package com.cx.utils;

import com.cx.entity.Data;

import java.io.*;

/**
 * 读取数据，存储数据
 */
public class File_Date {

    public static void Writ_File(Data data){
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("src/com/cx/Data/data.txt",false));
            os.writeObject(data);
            os.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static Data Read_File(){
        try {
            ObjectInputStream is = new ObjectInputStream(
                    new FileInputStream("src/com/cx/Data/data.txt"));
          Data data= (Data) is.readObject();
          return data;
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            return new Data();
        }
    }
}
