import java.util.*;
import java.util.stream.Collectors;


/*

----------------------------------------------------------- CHALLENGE ------------------------------------------------------------

»»»»»»»»»» SORT HOTEL LIST

Given a set of hotel identifiers and some number of reviews from guests about a hotel, sort the hotel identifiers based on a list
of keywords of interest (these will be given) The criteria to sort the hotels should be how many times the interesting keywords
are mentioned in any reviews about a given hotel.

Input:
There are 3 groups of input: keywords, hotel_ids, and reviews.
The first input contains keywords, as a single line of a space-separated words.
(If you use the pre-generated input code stubs, this will be the first string argument to your function. If you are parsing input
yourself, this string of keywords will be the first line of input.)
The second line contains one integer M , which means the number of lines in the following group. Then followed by M lines of
integers, which mean the list of hotel IDs. Then followed by one integer N , which means the number of lines in the following
group. Then followed by N lines of strings, which means the list of reviews. Each review is a single string, on a single line.
The list of hotel IDs and the list of reviews are parallel arrays. These two lists are always the same length ( N == M ).
For example, if they are put into 2 arrays called hotel_ids and reviews , respectively, then reviews[3] is a review for hotel
hotel_id[3]. (A hotel can have more than one review).

Output:
A list of hotel IDs. It should be sorted, in descending order, by how many mentions they have of the keywords across all of the
reviews for a given hotel.
(If you are not using the pre-generated output code stubs, you should print each hotel ID on a separate line.)

Notes:
* The words to be find will always be single words like 'breakfast' or 'noise'. Never double words like 'swimming pool'.
* Hotel ID is a 4-byte integer.
* Matching should be case-insensitive.
* Dots and commas should be ignored.
* If a word appears in a review twice, it should count twice.
* If two hotels have the same number of mentions, they should be sorted in the output based on their ID, smallest ID first.
* In case one or more test cases time out, consider revisiting the runtime complexity of your algorithms.

*/

public class SortHotelList {

    private final String keywords;
    private final List<Integer> hotelIds;
    private List<String> reviewsList;

    public SortHotelList(String keywords, List<Integer> hotelIds, List<String> reviewsList) {
        this.keywords = keywords;
        this.hotelIds = hotelIds;
        this.reviewsList = reviewsList;
        sort_hotels();
    }

    //Slice keywords inside the list
    private List<String> getKeywordsList() {
        return new ArrayList<>(Arrays.asList(keywords.toLowerCase().split(" ")));
    }

    //Verify and return if text review has characters not allowed
    private List<String> getReviewListWithAllowedWords() {
        for ( int i = 0; i < this.reviewsList.size(); i++ ) {
            String review = this.reviewsList.get(i).toLowerCase();
            String updateReview = "";
            for ( int x = 0; x < review.length(); x++) {
                int dec = (char) review.charAt(x);
                if ( dec == 32 || (dec >= 97 && dec <= 122)) {
                    updateReview += String.valueOf(review.charAt(x));
                }
            }

            this.reviewsList.set(i, updateReview);
        }

        return this.reviewsList;
    }

    //get reviews from index
    private List<String> getReviewsList(int index) {
        String review = getReviewListWithAllowedWords().get(index);
        return new ArrayList<>(Arrays.asList(review.split(" ")));
    }

    private void sort_hotels() {
        Map<Integer, Integer> hotelMap = new HashMap<>();

        for ( int x = 0; x < this.hotelIds.size(); x++) {
            int id = this.hotelIds.get(x);
            int total = getKeywordsList().stream().filter(getReviewsList(x)::contains).collect(Collectors.toList()).size();
            Iterator<Map.Entry<Integer, Integer>> iterator = hotelMap.entrySet().iterator();
            while ( iterator.hasNext() ) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                if ( entry.getKey() == id ) {
                    total = total + entry.getValue();
                }
            }
            hotelMap.put(id, total);
        }

        //print hotel ID by descending order
        hotelMap.entrySet().stream().
                sorted(Comparator.comparing((Map.Entry<Integer, Integer> byValue)-> byValue.getValue()).
                        reversed().
                        thenComparing(Map.Entry::getKey)).
                forEach((values)-> {
                    System.out.println("Hotel ID: " + values.getKey() + " - Interesting words: " + values.getValue());
                });

    }

    public static void main(String[] args) {
        String keywords = "Love location heart comfortable perfect close heineken airport budget";
        List<Integer> hotelIdList = new ArrayList<>(Arrays.asList(20,32,12,34,19,20,33,33,34,19,34,82));
        List<String> guestReviews = new ArrayList<>(
                Arrays.asList(
                    "Love Porto and this apartment. Will come back and stay again.",                                         //20
                    "Great stay in the heart of the old town!",                                                              //32
                    "The best location and a very comfortable apartment.",                                                   //12
                    "Clean, comfortable apartment at a great location!!!",                                                   //34
                    "Good hotel. Close to Schiphol Airport",                                                                 //19
                    "Vibrant hotel. Interesting location. Would definitely stay again!",                                     //20
                    "Very pleasant, I would stay there again.",                                                              //33
                    "Great short stop stay on a budget",                                                                     //33
                    "Heineken Experience!!! It's so close!",                                                                 //34
                    "Perfect, near to Albert Cuyp Market and Heineken Experience, metro and tram is just few stone away.",   //19
                    "Heineken Experience!",                                                                                  //20
                    "I liked how it was right near almost everything. Walking distance to most places!"                      //82
                ));

        SortHotelList sortHotelList = new SortHotelList(keywords, hotelIdList, guestReviews);
    }
}
