import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static ArrayList<Job> jobs;
	public static void main(String[] args) {
		System.out.println("\nMILEAGE TRACKER \n");
		jobs = read();
		System.out.print("1. EDIT JOB \n2. NEW JOB \n3. DELETE JOB \n4. QUIT PROGRAM \n> ");
		Scanner select = new Scanner(System.in);
		int selection = select.nextInt();
		if (selection == 1) {
			System.out.print("Enter ID: ");
			Scanner id = new Scanner(System.in);
			int getId = id.nextInt();
			System.out.print("1. Add Miles \n2. Rename Job \n> ");
			Scanner c = new Scanner(System.in);
			int i = c.nextInt();
			if (i == 1) {
				double miles = mileageCalc();
				jobs.get(getId).addMiles(miles);
			} else if (i == 2) {
				System.out.print("New Name: ");
				Scanner p = new Scanner(System.in);
				String name = p.nextLine();
				jobs.get(getId).setJobName(name);
			} else {
				System.out.println("Invalid selection!");
			}
			save(jobs);
			main(null);
		} else if (selection == 2) {
			System.out.print("Job name: ");
			Scanner in = new Scanner(System.in);
			String name = in.nextLine();
			Job tmp = new Job(name);
			jobs.add(tmp);
			save(jobs);
			main(null);
		} else if (selection == 3){
			System.out.print("Enter ID: ");
			Scanner n = new Scanner(System.in);
			int id = n.nextInt();
			jobs.remove(id);
			save(jobs);
			main(null);
		} else if (selection == 4) {
			return;
		} else {
			System.out.println("Invalid Selection. Closing.");
		}
	}
	public static void save(Object object) {
		try {
			FileOutputStream f = new FileOutputStream(new File("save.ser"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(object);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		
	}
	public static ArrayList read() {
		try {
			ArrayList<Job> jobFromFile = new ArrayList<Job>();
			FileInputStream fi = new FileInputStream(new File("save.ser"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			jobFromFile = (ArrayList) oi.readObject();
			for (int i=0; i<jobFromFile.size(); i++) {
				System.out.println("ID: "+i+"\n"+jobFromFile.get(i).toString()+"\n");
			}
			oi.close();
			fi.close();
			return jobFromFile;
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			return null;
		} catch(IOException e) {
			System.out.println("Error initializing stream");
			return null;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static double mileageCalc() {
		System.out.print("1. Enter Raw Mileage \n2. Enter Start and End Mileage \n> ");
		Scanner sel = new Scanner(System.in);
		int select = sel.nextInt();
		if (select == 1) {
			System.out.print("Miles to Add: ");
			Scanner m = new Scanner(System.in);
			double miles = m.nextDouble();
			return miles;
		} else if (select == 2) {
			System.out.print("Starting Mileage: ");
			Scanner s = new Scanner(System.in);
			double start = s.nextDouble();
			System.out.print("Ending Mileage: ");
			Scanner e = new Scanner(System.in);
			double end = e.nextDouble();
			double miles = end - start;
			System.out.println("Adding "+miles+" miles.");
			return miles;
		} else {
			System.out.println("Error, try again");
			return 0;
		}
	}

}
