//import java.util.Scanner;
// Customer class Project 1 - PART 2
class Customer implements Comparable<Customer> {

	private String last, first;
	private String acctNo;
	private double balance;
  
	// create customer
	public Customer() 
  {
		this.last = last;
		this.first = first;
		this.acctNo = acctNo;
		this.balance = balance;

	}
	// MUTATORS AND ACCESSORS******************

	// SET METHODS:

	public void setLast(String last) {
		this.last = last;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setAccount(String acctNo) {
		this.acctNo = acctNo;
	}
  

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// GET METHODS:

	public String getLast() {
		return last;
	}

	public String getFirst() {
		return first;
	}

	public String getAccount() {
		return acctNo;
	}
 
	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		
			balance += amount;
		
	}

	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println("You withdrew $" + amount + "\nNew balance: $" + balance);
		} else if (balance < amount) {
			System.out.println("You have insufficient funds!" + "\nYour Current Balance: $" + balance);
		}
	}

	/*
	 * public boolean equals(Comparable<Character> acctNo) {
	 * if(this.acctNo.equals(other.acctNo)) { return true;
	 * 
	 * } return true; }
	 */
	public String toString() {

		return ("\n|<:Customer- FullName: " + last + ", " + first + "\nAccount Number: " + acctNo + "\nBalance: $"
				+ balance + ":>|");
	}

	public int compareTo(Customer other) {
		//if (other.getFirst().equals(this.getFirst()))
     int value = this.getFirst().compareTo(other.first);
     if(value != 0)
     {
        return value;
     }
     else 
          return this.getLast().compareTo(other.last);
	}

	public boolean equals(Object r) {
		// TODO Auto-generated method stub
		return ((Customer) r).getAccount().equals(this.getAccount());
	}
	
	

}
