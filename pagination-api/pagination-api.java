import java.util.*;

class ExternalPaginatedApi {
    private List<List<String>> pageContents;

    public ExternalPaginatedApi(List<List<String>> pageContents) {
        this.pageContents = pageContents != null ? pageContents : new ArrayList<>();
    }

    public List<String> getExternalPaginatedData(int pageNum) {
        if (pageNum < 1 || pageNum > pageContents.size()) {
            return new ArrayList<>();
        }
        return pageContents.get(pageNum - 1);
    }
}

public class PaginationAPI {
    private ExternalPaginatedApi ext;
    private List<String> currPage;
    private int idx;
    private int pageNum;


    public PaginationAPI(ExternalPaginatedApi externalApi) {
        // TODO: Implement PaginationAPI constructor logic.
        this.ext = externalApi; // extrernal api
        this.pageNum = 0; // idx of the current page
        this.currPage = ext.getExternalPaginatedData(pageNum + 1); // current unexhausted page
        this.idx = 0; // idex within current page
    }

    public List<String> getNext(int n) {
        // TODO: Implement getNext logic.
        List<String> res = new ArrayList<>();
        while(res.size() < n){

            // advance currPage to the next non-empty page if the current one is exhausted
            while(currPage != null && idx == currPage.size()){
                pageNum++;
                currPage = ext.getExternalPaginatedData(pageNum + 1);
                idx = 0; // new page -> reset index
                if(currPage == null || currPage.isEmpty()) return res;
            }
            res.add(currPage.get(idx++));
        }
        return res;
    }

    public static void main(String[] args) {
        // Example 1
        List<List<String>> pageContents = Arrays.asList(
            Arrays.asList("apple", "banana", "cherry", "date"),
            Arrays.asList("elderberry", "fig", "grape", "honeydew", "kiwi", "lemon"),
            Arrays.asList("mango", "nectarine", "orange", "pear", "quince")
        );
        ExternalPaginatedApi externalApi = new ExternalPaginatedApi(pageContents);
        PaginationAPI api = new PaginationAPI(externalApi);
        System.out.println("========== Example 1 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["apple", "banana", "cherry", "date", "elderberry"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["fig", "grape", "honeydew", "kiwi", "lemon", "mango", "nectarine", "orange"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: ["pear", "quince"]
        System.out.println();

        // Example 2
        pageContents = Arrays.asList(
            Arrays.asList("paris", "london", "tokyo"),
            Arrays.asList("berlin", "rome", "madrid", "amsterdam", "moscow", "dubai", "singapore"),
            Arrays.asList("cairo", "sydney", "toronto", "bangkok"),
            Arrays.asList("lima", "oslo")
        );
        externalApi = new ExternalPaginatedApi(pageContents);
        api = new PaginationAPI(externalApi);
        System.out.println("========== Example 2 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["paris", "london", "tokyo", "berlin", "rome"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["madrid", "amsterdam", "moscow", "dubai", "singapore", "cairo", "sydney", "toronto"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: ["bangkok", "lima", "oslo"]
        System.out.println();

        // Example 3
        pageContents = Arrays.asList(
            Arrays.asList("red", "blue", "green", "yellow", "purple"),
            Arrays.asList("black", "white", "gray", "pink", "brown"),
            Arrays.asList("violet", "indigo", "teal", "magenta", "cyan")
        );
        externalApi = new ExternalPaginatedApi(pageContents);
        api = new PaginationAPI(externalApi);
        System.out.println("========== Example 3 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["red", "blue", "green", "yellow", "purple"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["black", "white", "gray", "pink", "brown", "violet", "indigo", "teal"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: ["magenta", "cyan"]
        System.out.println();

        // Example 4
        pageContents = Arrays.asList(
            Arrays.asList("dog", "cat"),
            Arrays.asList("elephant", "tiger", "lion"),
            Arrays.asList("giraffe", "zebra", "monkey", "panda", "koala", "penguin")
        );
        externalApi = new ExternalPaginatedApi(pageContents);
        api = new PaginationAPI(externalApi);
        System.out.println("========== Example 4 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["dog", "cat", "elephant", "tiger", "lion"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["giraffe", "zebra", "monkey", "panda", "koala", "penguin"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: []
        System.out.println();

        // Example 5
        pageContents = Arrays.asList(
            Arrays.asList("monday", "tuesday", "wednesday", "thursday"),
            Arrays.asList("friday", "saturday"),
            Arrays.asList("january", "february", "march", "april", "may"),
            Arrays.asList("june", "july", "august")
        );
        externalApi = new ExternalPaginatedApi(pageContents);
        api = new PaginationAPI(externalApi);
        System.out.println("========== Example 5 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["monday", "tuesday", "wednesday", "thursday", "friday"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["saturday", "january", "february", "march", "april", "may", "june", "july"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: ["august"]
        System.out.println();

        // Example 6
        pageContents = Arrays.asList(
            Arrays.asList("soccer", "basketball", "tennis", "baseball", "volleyball", "golf"),
            Arrays.asList("piano", "guitar", "violin"),
            Arrays.asList("pizza", "pasta", "burger", "sushi"),
            Arrays.asList("coffee", "tea", "juice", "water", "soda")
        );
        externalApi = new ExternalPaginatedApi(pageContents);
        api = new PaginationAPI(externalApi);
        System.out.println("========== Example 6 ==========");
        System.out.println("Next 5: " + api.getNext(5)); // Expected: ["soccer", "basketball", "tennis", "baseball", "volleyball"]
        System.out.println("Next 8: " + api.getNext(8)); // Expected: ["golf", "piano", "guitar", "violin", "pizza", "pasta", "burger", "sushi"]
        System.out.println("Next 3: " + api.getNext(3)); // Expected: ["coffee", "tea", "juice"]
        System.out.println();
    }
};
