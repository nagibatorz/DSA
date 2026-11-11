import java.util.ArrayList;
import java.util.List;

class Stream {
    String s;
    int stop;

    public Stream(String s) {
        this.s = s;
        this.stop = 0;
    }

    public String read(int n) {
        if (stop >= s.length()) {
            return "";
        }
        int end = Math.min(stop + n, s.length());
        String sub = s.substring(stop, end);
        stop += n;
        return sub;
    }
}

class MultiStream {
    List<Stream> streams;

    public MultiStream() {
        this.streams = new ArrayList<>();
    }

    public void add(Stream stream) {
        // TODO: Implement add logic.
        streams.add(stream);
    }

    public void remove(Stream stream) {
        // TODO: Implement remove logic.
        streams.remove(stream);
    }

    public String read(int n) {
        // TODO: Implement read logic.
        int remaining = n;
        StringBuilder res = new StringBuilder();
        while(!this.streams.isEmpty() && remaining > 0){
            Stream curr = streams.get(0);
            String chunk = curr.read(remaining);
            if(chunk.isEmpty()){
                this.streams.remove(0);
                continue;
            }
            remaining -= chunk.length();
            res.append(chunk);
        }
        return res.toString();
    }
}

class Solution {
    public static void main(String[] args) {
        // test case 1
        System.out.println("========= Test 1 =========");
        Stream stream1 = new Stream("123");
        Stream stream2 = new Stream("456");
        MultiStream multiStream = new MultiStream();
        multiStream.add(stream1);
        multiStream.add(stream2);
        System.out.println("Output: \"" + multiStream.read(2) + "\", expected: \"12\".");
        multiStream.remove(stream2);
        System.out.println("Output: \"" + multiStream.read(2) + "\", expected: \"3\".");

        // test case 2
        System.out.println("========= Test 2 =========");
        stream1 = new Stream("abc");
        stream2 = new Stream("defg");
        Stream stream3 = new Stream("hij");
        multiStream = new MultiStream();
        multiStream.add(stream1);
        multiStream.add(stream2);
        System.out.println("Output: \"" + multiStream.read(2) + "\", expected: \"ab\".");
        multiStream.add(stream3);
        System.out.println("Output: \"" + multiStream.read(3) + "\", expected: \"cde\".");
        System.out.println("Output: \"" + multiStream.read(5) + "\", expected: \"fghij\".");
        multiStream.remove(stream3);
        System.out.println("Output: \"" + multiStream.read(2) + "\", expected: \"\".");

        // test case 3
        System.out.println("========= Test 3 =========");
        stream1 = new Stream("hello");
        stream2 = new Stream("hack");
        stream3 = new Stream("2hire");
        multiStream = new MultiStream();
        multiStream.add(stream1);
        multiStream.add(stream2);
        System.out.println("Output: \"" + multiStream.read(7) + "\", expected: \"helloha\".");
        multiStream.remove(stream2);
        multiStream.add(stream3);
        System.out.println("Output: \"" + multiStream.read(5) + "\", expected: \"2hire\".");
    }
}
