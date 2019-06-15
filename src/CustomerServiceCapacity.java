import java.util.*;

/*

----------------------------------------------------------- CHALLENGE ------------------------------------------------------------

»»»»»»»»»» CUSTOMER SERVICE CAPACITY

At Booking.com, our customer service team is an important contributor to customer satisfaction. During busy times, however, there
might be more calls to customer service than the number of customer service executives can manage. Fortunately, we record data on
that. We've collected information about all phone calls to our call centres for the past year. Given that our current number of
customer care executives is X, determine how many more people we would need to hire, to make sure that our customers
would not have to wait during peak hours (i.e. that we don't have more phone calls than we have customer service executives).

Input:
The first line contains the current number of customer service executives X.
The second line contains one integer N, N is the number of data points in the data set.
The third line contains one integer M, M is the number of integers in each data point (Note, this will always be equal to 2)
The next N lines are whitespace-separated pairs of timestamps (a timestamp is an integer that represents the number of seconds
since the epoch). On each line, the first time is the time when the call was started, and the second one is when that call ended.

Output:
A single integer, representing the number of additional customer service executives that we would need to employ, to cover the
call volume during peak times. If the current coverage is already sufficient, then print 0.
 */

public class CustomerServiceCapacity {

    private int noOfCurrentAgents;
    private List<List<Integer>> callsTimes;


    public CustomerServiceCapacity(int noOfCurrentAgents, List<List<Integer>> callsTimes) {
        this.noOfCurrentAgents = noOfCurrentAgents;
        this.callsTimes = callsTimes;
    }

    private int howManyAgentsToAdd() {
        int count = 0;
        for ( int i = 0; i < this.callsTimes.size(); i++ ) {
            List<Integer> interval = this.callsTimes.get(i);
            int s1 = this.callsTimes.get(i).get(0);
            int s2 = this.callsTimes.get(i).get(1);
            int index = i + 1;
            while ( index < this.callsTimes.size() ) {
                int f1 = this.callsTimes.get(index).get(0);
                int f2 = this.callsTimes.get(index).get(1);
                if ( betweenInterval(s1, f1, f2) || (betweenInterval(s2, f1, f2)) ||
                     betweenInterval(f1, s1, s2) || (betweenInterval(f2, s1, s2))) {
                    count++;
                }

                index++;
            }
        }

        return noOfCurrentAgents > count ? 0 : count-noOfCurrentAgents;
    }

    //check interval
    private boolean betweenInterval(int value, int rangeStart, int rangeFinish) {
        boolean status = false;
        if ( value >= rangeStart && value <= rangeFinish ) {
            status = true;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        int result = howManyAgentsToAdd();
        String info = result <= 1 ? result + " agent" : result + " agents";
        return "It's necessary " + info;
    }


    public static void main(String[] args) {
        int agents = 1;
        List<List<Integer>> callsTimes = new ArrayList<>();
        callsTimes.add(new ArrayList<>(Arrays.asList(1481122000, 1481122020)));
        callsTimes.add(new ArrayList<>(Arrays.asList(1481122000, 1481122040)));
        callsTimes.add(new ArrayList<>(Arrays.asList(1481122030, 1481122035)));
        System.out.println(new CustomerServiceCapacity(agents,callsTimes).toString());
    }
}
