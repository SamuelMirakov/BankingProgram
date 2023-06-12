import java.util.Scanner;
import java.io.*;

public class Main {

  

	public static void main(String[] args) throws FileNotFoundException {
 //Customer[] accDB = new Customer[10000];
		//myBST treeDB = LoadCustomers();
    //System.out.println(treeDB.getRoot());
    Menu();
	}
	
	public static myBST LoadCustomers() throws FileNotFoundException {
		myBST treeDB = new myBST();
    Customer[] acctDB = new Customer[10000];
		File file1 = new File("customers.txt");
		Scanner scan1 = new Scanner(file1);
    
    
while (scan1.hasNext()) {

			String[] customer = scan1.next().split(",");
			btNode BT = new btNode();
			BT.data.setLast(customer[0]); 
			BT.data.setFirst(customer[1]); 
			BT.data.setAccount(customer[2]);
			BT.data.setBalance(Double.parseDouble(customer[3]));

			
      /////PROJECT 2 ADDITIONS/////
		   acctDB[Integer.parseInt(BT.data.getAccount())] = BT.data;
       //The AccDB array accessess the customer quicker therefore its more efficient but we also need the BST just incase the account number is unknown but both are stored in the tree.
			/////PROJECT 2 ADDITIONS/////
      
      System.out.println(BT.data.toString());
			treeDB.insert(treeDB, BT);
		}
    Customer anotherCustomer = new Customer();
       anotherCustomer = acctDB[0001];
      System.out.println("");
			System.out.println(anotherCustomer.getFirst());
      System.out.println("");
		scan1.close();
		
		return treeDB;
	}

  public static void saveCustomer(btNode node) throws FileNotFoundException
  {
    PrintWriter outFile = new PrintWriter("output.txt");
    SaveCustomerRecursive(node, outFile);
    outFile.close();

  }

  private static void SaveCustomerRecursive(btNode t, PrintWriter outFile) throws FileNotFoundException {
    
    if(t != null){
        
	  outFile.print(t.data);	
    SaveCustomerRecursive(t.left, outFile);
    SaveCustomerRecursive(t.right, outFile);
    }
		
  }

public static void Menu() throws FileNotFoundException {
  PrintWriter outFile = new PrintWriter("output.txt");
  btNode BT = new btNode();
  myBST treeDB = LoadCustomers();
  btNode newNode = new btNode();
  int choice=0;
  double amount;

  String acctNo = "";
  double balance = 0;
  Scanner in = new Scanner(System.in);

  System.out.println("Enter First Name: ");
  String first = in.next();
  System.out.println("Enter Last Name: ");
  String last = in.next();
  
  
  System.out.print("\nCommand Menu\n" + "-----------------------------------\n"
				+ "1: Make A Deposit\n"
				+ "2: Make A Withdrawal\n"
				+ "3: Check Your Current Balance\n" + "4: Open A New Account\n"
				+ "5: Close An Existing Account\n" + "6: Quit The Program\n\n" + "0: Display The Menu Again\n");

   while (choice != 6) {
    System.out.println("\nPlease Enter A Command Or Type 0 To See The Menu:\n");
      BT.data.setFirst(first);
      BT.data.setLast(last);
      BT.data.setAccount(acctNo);
      BT.data.setBalance(balance);
      choice = in.nextInt();
      newNode = treeDB.search(treeDB, BT.data);
      switch(choice) {
        case 1: // Deposit to account
            if(newNode != null) {
              System.out.print("\nYour Current Balance: $" + newNode.data.getBalance());
				System.out.print("\nPlease Enter An Amount You Would Like To Deposit: $");
				amount = in.nextDouble();
				newNode.data.deposit(amount);
				System.out.println("\nYou deposited $" + amount + "\nNew Balance: $"  + newNode.data.getBalance());
            } else {
				System.out.println("\nInvalid Account, You Should Make One First!\n");
            }
              break;
        
        
        case 2: // withdraw from account
             if(newNode != null) {
              System.out.println("\nYour Current Balance: $" + newNode.data.getBalance());
				System.out.print("\nPlease Enter An Amount You Would Like To Withdraw: $");
				amount = in.nextDouble();
				newNode.data.withdraw(amount);
             } else {
             System.out.println("\nInvalid Account, You Should Make One First!\n");
             }
        break;
        case 3: // check balance
              if(newNode != null) {
              System.out.println("\nYour Current Balance Is: $" + newNode.data.getBalance());
              } else {
              System.out.println("\nInvalid Account, You Should Make One First!\n");
              }
        break;
        case 4: // insert new customer

            if(newNode!=null)
            {
              System.out.println("\nOops!! Account Already Exists.\n");
              break;
            }
            else{
              System.out.println("\nWelcome To BST Bank. Let's Get You Started Right Away!\n");
              btNode newC = new btNode();
              newC.data.setFirst(first);
              newC.data.setLast(last);
              System.out.println("\nEnter Your New Account Number, In Binary: ");
              acctNo = in.next();
              newC.data.setAccount(acctNo);
              System.out.print("\nHow Much Would You Like To Deposit As Your Starting Balance? $" );
              balance = in.nextDouble();
              newC.data.setBalance(balance);
              System.out.println("\nNew Account Added:\n" + newC.data);
				      treeDB.insert(treeDB, newC);

            }
        break;
        case 5: // delete existing customer
        System.out.println("\nAccount Deleted:\n" + newNode.data);
        System.out.println("\nWe Hope You Bank With Us Again!\n");
				treeDB.delete(treeDB, newNode.data);
        break;
        case 6: // Quit program
        saveCustomer(treeDB.root);
        System.out.println("\nGoodbye!\n");
        in.close();
        break;
        case 0: // reprint Menu
        Menu();
        break;
        default:
        System.out.println("\nInvalid Input!\n");
        
      }  
    } 
  }
}
