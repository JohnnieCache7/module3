
import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ListItem;

public class Starter {

	static Scanner in = new Scanner(System.in);
	static ListItemHelper lih = new ListItemHelper();

	private static void addASnack() {
		// TODO Auto-generated method stub
		System.out.print("Enter a new snack: ");
		String snack = in.nextLine();
		System.out.print("Enter snack price: $");
		int price = in.nextInt();

		ListItem toAdd = new ListItem(snack, price);
		lih.insertSnack(toAdd);
	}

	private static void deleteASnack() {
		// TODO Auto-generated method stub
		System.out.print("Enter the snack to delete: ");
		String snack = in.nextLine();
		System.out.print("Enter the price to delete: $");
		int price = in.nextInt();

		ListItem toDelete = new ListItem(snack, price);
		lih.deleteASnack(toDelete);

	}

	private static void editASnack() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search snack");
		System.out.println("2 : Search price");
		int searchBy = in.nextInt();
		in.nextLine();

		List<ListItem> foundSnack;
		if (searchBy == 1) {
			System.out.print("Enter the snack: ");
			String snack = in.nextLine();
			foundSnack = lih.searchSnackBySnack(snack);

		} else {
			System.out.print("Enter the price: $");
			int price = in.nextInt();
			foundSnack = lih.searchSnackByPrice(price);
		}

		if (!foundSnack.isEmpty()) {
			System.out.println("Found Snacks...");
			for (ListItem l : foundSnack) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			ListItem toEdit = lih.searchSnackById(idToEdit);
			System.out.println("Retrieved " + toEdit.getPrice() + " from " + toEdit.getSnack());
			System.out.println("1 : Update Snack");
			System.out.println("2 : Update Price");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Snack: ");
				String newSnack = in.nextLine();
				toEdit.setSnack(newSnack);
			} else if (update == 2) {
				System.out.print("New Price: $");
				int newPrice = in.nextInt();
				toEdit.setPrice(newPrice);
			}

			lih.updateSnack(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("****** Vending Machine ******");
		while (goAgain) {
			System.out.println("*  Select an option below for the vending machine:");
			System.out.println("*  1 -- Add a new snack");
			System.out.println("*  2 -- Edit an existing snack");
			System.out.println("*  3 -- Delete snack");
			System.out.println("*  4 -- View the snacks");
			System.out.println("*  5 -- Exit the vending machine");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addASnack();
			} else if (selection == 2) {
				editASnack();
			} else if (selection == 3) {
				deleteASnack();
			} else if (selection == 4) {
				viewVendingMachine();
			} else {
				lih.cleanUp();
				System.out.println("Come again! ");
				goAgain = false;
			}

		}

	}

	private static void viewVendingMachine() {
		List<ListItem> allSnacks = lih.showAllSnacks();
		for (ListItem singleSnack : allSnacks) {
			System.out.println(singleSnack.returnSnacks());
		}
	}

}
