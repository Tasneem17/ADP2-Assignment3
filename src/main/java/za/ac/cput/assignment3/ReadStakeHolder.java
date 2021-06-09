/**
 *Tasneem Jacobs 215030389
 * ADP2 Assignment 3
 *
 */
package za.ac.cput.assignment3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadStakeHolder {

    ArrayList<Customer> customerarray = new ArrayList();
    ArrayList<Supplier> supplierarray = new ArrayList();

    Customer customer = new Customer();
    Supplier supplier = new Supplier();

    public void writeToFile() {
        int count = 0;
        String path = "stakeholder.ser";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(path));
            Object array = new Array[11];

            while (reader.readLine() != null) {
                Stakeholder stakeholder = (Stakeholder) objectReader.readObject();

                if (stakeholder.getClass() == customer.getClass()) {

                    customerarray.add((Customer) stakeholder);
                    System.out.println("Added customer");

                } else if (stakeholder.getClass() == supplier.getClass()) {

                    supplierarray.add((Supplier) stakeholder);
                    System.out.println("Added Supplier");
                }

                count++;
            }

            System.out.println("Number of stakeholders = " + count + "\n");
            objectReader.close();
        } catch (ClassNotFoundException ioe) {
            System.out.println("class error reading ser file:" + ioe);

        } catch (IOException ioe) {
            System.out.println("error reading from ser file:" + ioe);
        }

    }

    public void displaycustomerarray() {

        for (int i = 0; i < customerarray.size(); i++) {
            
            System.out.print(
               customerarray.get(i).getStHolderId() + 
                " " +
               customerarray.get(i).getFirstName() +
                "\t" +
                customerarray.get(i).getSurName() +
                "\t\t" 
                
            );               
        }
    }

    public void displaySupplierList() {

        for (int i = 0; i < supplierarray.size(); i++) {
            System.out.println(
                    supplierarray.get(i).getStHolderId()
                    + " "
                    + supplierarray.get(i).getName()
                    + "\t\t"
                    + supplierarray.get(i).getProductType()
                    + "\t\t\t"
                    + supplierarray.get(i).getProductDescription()
            );
            System.out.println("\n");

        }
    }

    public static void main(String[] args) {
        ReadStakeHolder b = new ReadStakeHolder();

        b.writeToFile();

        System.out.println("==================================================== Customers ===================================================");
        System.out.println("\n");
        System.out.println(" %s\t%-10s\t%-10s\t%-10s\t%-10s%n","ID" ,"Name","Surname","Date of Birth","Age");
        System.out.println("==================================================================================================================");
        b.displaycustomerarray();
        System.out.println("\n");
        System.out.println("==================================================== Supplier ====================================================");
        System.out.println("");
        System.out.println("==================================================================================================================");
        b.displaySupplierList();

    }

}
