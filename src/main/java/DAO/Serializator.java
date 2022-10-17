package DAO;

import BLL.DeliveryService;

import java.io.*;

public class Serializator {
    public static void serialize(DeliveryService deliveryService) {
        try {
            FileOutputStream fileOut = new FileOutputStream("serializat.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(deliveryService);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        DeliveryService deliveryService;
        try {
            FileInputStream fileIn = new FileInputStream("serializat.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deliveryService = (DeliveryService)in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            return null;
        }
        return deliveryService;
    }
}
