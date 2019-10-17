package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 *
 * @author runb-cs112
 *
 */
public class Polynomial { //version 3

	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3
	 * </pre>
	 *
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc)
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		if ( poly1 == null ) { // checks for 0
			return poly2;
		}
		if (poly2 == null) { // checks for 0
			return poly1;
		}
		Node ptr1 = poly1;
		Node ptr2 = poly2;
		Node ptr3 = null;
		//Node front4 = new Node(5,5,null);
		Node front3= null;


		while (ptr1 != null && ptr2 != null) {
			if (ptr1.term.degree == ptr2.term.degree) {
				if ((ptr1.term.coeff + ptr2.term.coeff == 0)) {
					ptr1 = ptr1.next;
					ptr2 = ptr2.next;
				} else {
					if (front3 == null) {
						front3 = new Node(ptr1.term.coeff + ptr2.term.coeff,ptr1.term.degree,null);
						ptr3 = front3;
					} else {
						Node temp = new Node(ptr1.term.coeff + ptr2.term.coeff,ptr1.term.degree,null);
						ptr3.next = temp;
						ptr3 = temp;
					}
					ptr1 = ptr1.next;
					ptr2 = ptr2.next;
				}
			}
			else if ( ptr1.term.degree < ptr2.term.degree) {
				if (front3 == null) {
					front3 = new Node(ptr1.term.coeff,ptr1.term.degree,null);
					ptr3 = front3;

				} else {
					Node temp = new Node(ptr1.term.coeff, ptr1.term.degree , null);
					ptr3.next = temp;
					ptr3 = temp;
				}
				ptr1 = ptr1.next;
			}
			else if ( ptr1.term.degree > ptr2.term.degree ) {
				if ( front3 == null) {
					front3 = new Node(ptr2.term.coeff, ptr2.term.degree,null);
					ptr3 = front3;
				} else {
					Node temp = new Node(ptr2.term.coeff,ptr2.term.degree,null);
					ptr3.next = temp;
					ptr3 = temp;
				}
				ptr2 = ptr2.next;
			}
		}

		while ( ptr1 != null) {
			Node temp = new Node(ptr1.term.coeff, ptr1.term.degree, null );
			ptr3.next = temp;
			ptr3 = temp;
			ptr1 = ptr1.next;
		}

		while ( ptr2 != null) {
			Node temp = new Node(ptr2.term.coeff, ptr2.term.degree, null );
			ptr3.next = temp;
			ptr3 = temp;
			ptr2 = ptr2.next;
		}

		if (ptr3 == null) {
			return null;
		}

		Node ptr10 = front3.next;
		Node prev1 = front3;
		while ( ptr10 != null ) { // gets rid of any potential 0's
			if ( ptr10.term.coeff == 0 ) {
				prev1.next= ptr10.next;
				ptr10 = prev1.next;
			} else {
				prev1 = prev1.next;
				ptr10 = ptr10.next;
			}
		}
		if (front3.term.coeff == 0 ) {
			front3 = front3.next;
		}

		return front3;

	}

	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 *
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		if ( poly1 == null || poly2 == null ) { // checks if any of them == 0, anything times 0 is 0, so just return null
			return null;
		}

		Node ptr1 = poly1;
		Node ptr2 = poly2;
		Node ptr3 = null;
		Node front3 = null;

		while ( ptr1 != null ) { // multiplies
			while ( ptr2 != null ) {
				if ( front3 == null ) {
					front3 = new Node(ptr1.term.coeff * ptr2.term.coeff, ptr1.term.degree + ptr2.term.degree, null);
					ptr3 = front3;
					//System.out.println("front3 is getting a first");
				} else {
					Node temp = new Node(ptr1.term.coeff * ptr2.term.coeff, ptr1.term.degree + ptr2.term.degree, null);
					ptr3.next = temp;
					ptr3 = temp;
					//System.out.println("front3 is getting a new addition");
				}
				ptr2 = ptr2.next;
			}
			ptr1 = ptr1.next;
			ptr2 = poly2;
		}

		//Node rawMath = front3;

		Node p1 = front3;
		Node p2 = null;
		Node p2prev = front3;
		float a;
		int degreeCheck;
		Node front4 = null;
		Node p4 = null;

		//p1 = front3;

		while ( p1 != null ) { // combines like terms
			p2 = p1.next;
			a = p1.term.coeff;
			//System.out.println("The coeff is " + a );
			degreeCheck = p1.term.degree;
			//System.out.println("The degree is " + degreeCheck);
			while ( p2 != null ) {
				if ( p2.term.degree == degreeCheck ) {
					a = a + p2.term.coeff;
					//System.out.println("I'm being added");
					p2prev.next = p2.next;
					p2 = p2prev.next;
				} else {
					p2 = p2.next;
					p2prev = p2prev.next;
				}
				//p2 = p2.next;
				//p2prev = p2prev.next;
			}
			Node temp = new Node(a,degreeCheck,null);
			if ( front4 == null ) {
				front4 = temp;
				/*
				System.out.println("Front4 was just created");
				System.out.print(a + ",");
				System.out.println(degreeCheck);
				*/
				p4 = temp;
			} else {
				p4.next = temp;
				p4 = temp;
				/*
				System.out.println("Front4 has a new addtion ");
				System.out.print(a + ",");
				System.out.println(degreeCheck);
//				*/
			}
			//front3 = front3.next;
			p1 = p1.next;
			p2prev = p1;

		}

		Node smallest = null;
		Node curr = null;
		Node front5 = null;
		Node p5 = null;
		boolean smallestRemoved = false;

		while ( front4.next != null ) { // puts them in ascending order
			smallest = front4;
			curr = front4.next;
			while ( curr != null ) {
				if ( smallest.term.degree > curr.term.degree ) {
					smallest = curr;
				}
				curr = curr.next;
			}
			Node temp = new Node (smallest.term.coeff, smallest.term.degree,null);
			if ( front5 == null ) {
				front5 = temp;
				p5 = temp;
			} else {
				p5.next = temp;
				p5 = temp;
			}

			Node prev2= front4;
			Node curr2 = front4.next;
			smallestRemoved = false;

			if ( smallest == front4 ) { // removing the smallest from front4
				front4 = front4.next;
				smallestRemoved = true;
			}



			while ( smallestRemoved == false ) { //traverses linked list until the smallest is removed

				if ( curr2 == smallest ) {
					prev2.next = curr2.next;
					smallestRemoved = true;
				} else {
					prev2 = prev2.next;
					curr2 = curr2.next;
				}
			}

		}
		Node temp = new Node (front4.term.coeff, front4.term.degree, null);
		p5.next = temp;
		p5 = temp;

		Node ptr10 = front5.next;
		Node prev1 = front5;;
		while ( ptr10 != null ) { // gets rid of 0's
			if ( ptr10.term.coeff == 0 ) {
				prev1.next= ptr10.next;
				ptr10 = prev1.next;
			} else {
				prev1 = prev1.next;
				ptr10 = ptr10.next;
			}
		}
		if (front5.term.coeff == 0 ) {
			front5 = front5.next;
		}

		//Node temp2 = null;
		return front5;
		//return rawMath;
	}


	/**
	 * Evaluates a polynomial at a given value.
	 *
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		float total = 0;
		Node curr = poly;

		while ( curr != null ) {
			float coeff = curr.term.coeff;
			total = total + (coeff * (float) Math.pow(x, curr.term.degree));
			curr = curr.next;
		}

		return total;
	}

	/**
	 * Returns string representation of a polynomial
	 *
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		}

		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
}
