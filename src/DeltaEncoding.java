import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*

----------------------------------------------------------- CHALLENGE ------------------------------------------------------------

»»»»»»»»»» DELTA ENCODING

Given a list of numbers as input, e.g. :
Sample input:
25626
25757
24367
24267
16
100
2
7277

Output a delta encoding for the sequence. In a delta encoding, the first element is reproduced as-is. Each subsequent element is
represented as the numeric difference from the element before it. E.g. for the sequence above, the delta encoding would be:
25626
131
-1390
-100
-24251
84
-98
7275
However, if a difference value does not fit in a single signed byte, i.e. -127 <= x <= 127 , then instead of the difference, we
would like to print an escape token before the out-of-range value. This will denote that the value following the escape token is a
full four-byte absolute value, rather than a one-byte difference value. For this exercise, we'll declare -128 as the escape token.
Following the same example above, the final output would be:

Sample output:
25626
-128
131
-128
-1390
-100
-128
-24251
84
-98
-128
7275
*/

public class DeltaEncoding {

    private List<Integer> numbers;
    private List<Integer> deltaListResult;

    public DeltaEncoding(List<Integer> numbers) {
        this.numbers = numbers;
        delta_encode();
    }

    private void delta_encode() {
       deltaListResult = new ArrayList<>();
       int count = 0;
       int preview = numbers.get(0);
       deltaListResult.add(preview);
       for ( int i = 1; i < numbers.size(); i++ ) {
           int difference = numbers.get(i) - preview;
           if (Math.abs(difference) > Byte.MAX_VALUE )
               deltaListResult.add((int)Byte.MIN_VALUE);
           deltaListResult.add(difference);
           preview = numbers.get(i);
       }
    }

    @Override
    public String toString() {
        return "Delta Encode Result {" + deltaListResult + '}';
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(25626,25757,24367,24267,16,100,2,7277));
        DeltaEncoding deltaEncoding = new DeltaEncoding(list);
        System.out.println(deltaEncoding.toString());
    }
}