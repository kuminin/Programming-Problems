import java.util.ArrayList;
import java.util.Collections;

/**
 * Facilitates dispensing stamps for a postage stamp machine.
 */
public class StampDispenser
{
    /**
     * Constructs a new StampDispenser that will be able to dispense the given 
     * types of stamps.
     *
     * @param stampDenominations The values of the types of stamps that the 
     *     machine should have.  Should be sorted in descending order and 
     *     contain at least a 1.
     */
    public StampDispenser(int[] stampDenominations)
    {	
    	this.stampDenominations = new ArrayList<Integer>();
    	for (int i = 0; i < stampDenominations.length; i++) {
    		if (!this.stampDenominations.contains(stampDenominations[i])) {
        		this.stampDenominations.add(stampDenominations[i]);
    		}

    	}
    	Collections.sort(this.stampDenominations, Collections.reverseOrder());

    }
 
    /**
     * Returns the minimum number of stamps that the machine can dispense to
     * fill the given request.
     *
     * @param request The total value of the stamps to be dispensed.
     */
    public int calcMinNumStampsToFillRequest(int request)
    {
    	
    	/*
    	 * trackNum is set to be stamps.size()-1 because it is a 
    	 * necessary variable for us to be able to backtrack in our loop
    	 */
    	int trackNum = stampDenominations.size() - 1; 
    	int index = 0;
    	int sum = 0; // initial sum of the subset of 0 elements
    	int sizeOfSubset = 0; // initial size of the subset
    	int count = request; // We can have a maximum of (request/1) stamps
    	while(true) {
    		/*
    		 * We add sum and the element from the stamps list at index i to 
    		 * check if it is less than or equal to the request.
    		 */
    		if (sum + stampDenominations.get(index) <= request) {
    			/*
    			 * This loop adds the sum and the element from the stamps list
    			 * and increments the count until the check for loop fails.
    			 */
    			while (sum + stampDenominations.get(index) <= request) {
        			sum += stampDenominations.get(index);
        			sizeOfSubset++;
    			}
    			
    			/*
    			 * If trackNum is less than index, we set the trackNum to be
    			 * the index for us to be able to backtrack the 
    			 * stampDenominations list and try to find the minimum subset
    			 * list that will add up to the request. 
    			 */
    			if (trackNum > index) {
    				trackNum = index;
    			}
    			
    			/*
    			 * If the sum is equal to the request; we compare the count to
    			 * the sizeOfSubset and set count to sizeOfSubset if count is
    			 * greater than sizeOfSubset and sizeOfSubset does not equal
    			 * zero.
    			 */
    			if (sum == request) {
        			if (count > sizeOfSubset && sizeOfSubset != 0) {
        				count = sizeOfSubset;
        			}
        			/*
        			 * we set the sum and the sizOfSubset back to zero because
        			 * we are going to backtrack back to the trackNum. Thus
        			 * we have to set our index to trackNum.
        			 */
        			sum = 0;
        			sizeOfSubset = 0;
        			index=trackNum;
        			/*
        			 * We set the trackNum back to the size of the 
        			 * stampDenominations - 1 in order for us to be able to 
        			 * back track again
        			 */
        			trackNum = stampDenominations.size() - 1;
        			/*
        			 * this if statement checks if the index is equal to the 
        			 * stampDenominations.size()-1 (trackNum) to see if have 
        			 * completely backtracked every single possible subset.
        			 */
            		if (index == trackNum) {
            			break;
            		}
        		}
    		}
    		index++;
    	}
    	
        return count;
    }
    
    private ArrayList<Integer> stampDenominations;
    
    public static void main(String[] args)
    {
        int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
        String pass = "pass, no assertion error thrown";
        StampDispenser stampDispenser = new StampDispenser(denominations);
        assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;
        System.out.println(pass);
        assert stampDispenser.calcMinNumStampsToFillRequest(34) == 2;
        System.out.println(pass);
        assert stampDispenser.calcMinNumStampsToFillRequest(120) == 2;
        System.out.println(pass);
        assert stampDispenser.calcMinNumStampsToFillRequest(13) == 3;
        System.out.println(pass);
        assert stampDispenser.calcMinNumStampsToFillRequest(109) == 5;
        System.out.println(pass);
    }
}
