import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
	
	private Card card = new Card();
	ArrayList<Cards> listCards = new ArrayList<>();
	ArrayList<Hands> listHand = new ArrayList<Hands>();
		/* input string*/
	public static String inputString() { 
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input String:");
		String input = scanner.nextLine();
		return input.toUpperCase();		
	}
	
	/* 52 cards in the deck */
	public int check(String input) {
		int flag = 1;
		for(int i = 0; i< input.length(); i++) {
			if(i%2 == 0) {
				int j = 0;
				for(; j < card.suits.length; j++) {
					if(input.charAt(i) ==  card.suits[j]) {
						break;
					}
					
				}
				if(j == card.suits.length) {
					flag = 2;
					return flag;
				}
			}else {
				int l = 0;
				for(; l< card.ranks.length; l++) {
					if(Character.toString(input.charAt(i)).equals(card.ranks[l])) {
						break;
					}	
				}
				if(l == card.ranks.length) {
					flag = 3;
					return flag;
				}
			}
		}
		return flag;
	}
	/* check Dublicate*/
	public boolean checkDublicate(String input) {
		boolean flag = false;
		for(int i = 0; i< input.length(); i++) {
			Cards cards = new Cards();
			cards.suit = input.charAt(i);
			cards.rank = input.charAt(++i);
			listCards.add(cards);
		}
		for(int k=0; k< listCards.size();k++) {
			for(int m = k+1; m< listCards.size();m++) {
				if((listCards.get(k).rank) == (listCards.get(m).rank)
					&& (listCards.get(k).suit) == (listCards.get(m).suit)	) {
					flag = true;
				}
			}
		}
		return flag;
	}
	

	public ArrayList<Hands> getHand(){
		for(int i = 0 ; i < listCards.size()-1;i++) {
			for(int j = i+1; j < listCards.size();j++) {
				if(listCards.get(j).status == true &&
						listCards.get(i).rank == listCards.get(j).rank) {
					listCards.get(i).count ++;
					listCards.get(j).status = false;
				}
			}

			if(listCards.get(i).status) {
				Hands hands = new Hands();
				hands.setRank(listCards.get(i).rank);
				hands.setCount(listCards.get(i).count);
				listHand.add(hands);
			}
					
		}	
		return listHand;
	}
	/*display in other*/	
	public void decriptionPoker(int count) {
		if(count == 5) {
			System.out.println("5 Different");
		}else if(count == 4) {
			System.out.println("1P");
		}else if( count == 3) {
			System.out.println("2P");
		}else if(count == 2) {
			int max = 1;
			for(int i = 0; i< 2; i++) {
				if(listHand.get(i).getCount() > max) {
					max = listHand.get(i).getCount();
				}
			}
			if(max == 4) {
				System.out.println("4C");
			}else if(max == 3) {
				System.out.println("FH");
			}
		}
	}
	
	public static void main(String[] args) {
		String input;
		String ans;
		do {
			input = inputString();
			Player player = new Player();
			System.out.print("Out string: ");
			if(input.length() == 10) {
				if(player.check(input) == 2) {
					System.out.println("No exists suits");
				}else if(player.check(input) == 3) {
					System.out.println("No exists ranks");
				}else {
					if(player.checkDublicate(input)) {
						System.out.println("Duplicate");
					}else {
						player.decriptionPoker(player.getHand().size());
					}
				}
			}else {
				System.out.println("--");
			}
			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you continue(Y/N)");
			ans = scanner.nextLine().toUpperCase();
		}while(ans.equals("Y"));
		System.out.println("*********** SEE YOU AGAIN ************");
	}

}
