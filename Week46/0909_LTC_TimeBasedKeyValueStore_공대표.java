import java.util.*;

class TimeMap {

    Map<String, List<Data>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());

        map.get(key).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<Data> list = map.get(key);

        int left = 0;
        int right = list.size() - 1;
        String answer = "";

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid).timestamp <= timestamp) {
                answer = list.get(mid).value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    class Data {
        String value;
        int timestamp;

        Data(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
