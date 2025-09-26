

public class GLProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Welcome to project 1");
        // Test MyHashMap
        System.out.println("\n=== Testing MyHashMap ===");
        MyHashMap<String> map = new MyHashMap<>("key1", "value1");
        map.put("key2", "value2");
        map.put("Cozmo", "robotValue"); // This should hash to index 0

        System.out.println("Size: " + map.size());
        System.out.println("Contains 'key1': " + map.contains("key1"));
        System.out.println("Get 'Cozmo': " + map.get("Cozmo"));
        System.out.println("Replace 'key1': " + map.replace("key1", "newValue1"));
        System.out.println("Get 'key1' after replace: " + map.get("key1"));

    }
}
