package metrics;

import java.util.LinkedHashMap;
import java.util.Map;

import model.DataModel;
import model.Globals;
import model.Item;
import model.User;

/**
 * This static class generates test data based on Prof. Ricci "Evaluation"
 * lecture note page 31
 * 
 * @author FBM
 *
 */
public class TestDataGenerator {
    public static
            User createUser() {
        Globals.setMaxRating(5);
        Globals.setMinRating(0);
        final User user = new User(1);
        user.addItemRating(1, 5);
        user.addItemRating(2, 5);
        user.addItemRating(3, 5);
        user.addItemRating(4, 1);
        user.addItemRating(5, 1);
        user.addItemRating(6, 1);
        user.addItemRating(7, 1);
        user.addItemRating(8, 1);
        user.addItemRating(9, 1);
        user.addItemRating(10, 1);
        user.addItemRating(11, 4);
        return user;
    }

    public static
            Map<Integer, Float> generateList2() {
        final Map<Integer, Float> list2 = new LinkedHashMap<>();
        list2.put(1, 4f);
        list2.put(2, 4f);
        list2.put(4, 4f);
        list2.put(5, 4f);
        list2.put(6, 4f);
        list2.put(3, 4f);
        list2.put(11, 4f);
        list2.put(7, 4f);
        list2.put(8, 4f);
        list2.put(10, 4f);
        return list2;
    }

    public static
            Map<Integer, Float> generateList1() {
        final Map<Integer, Float> list1 = new LinkedHashMap<>();
        list1.put(1, 4f);
        list1.put(4, 4f);
        list1.put(2, 4f);
        list1.put(5, 4f);
        list1.put(6, 4f);
        list1.put(7, 4f);
        list1.put(3, 4f);
        list1.put(8, 4f);
        list1.put(9, 4f);
        list1.put(10, 4f);
        return list1;
    }

    /**
     * 
     */
    public static
            DataModel getTrainData() {
        DataModel train = new DataModel();
        final User user1 = new User(1);
        final User user2 = new User(2);
        final User user3 = new User(3);

        final Item item1 = new Item(1);
        final Item item2 = new Item(2);
        final Item item3 = new Item(3);
        final Item item11 = new Item(11);

        user1.addItemRating(1, 5);
        user2.addItemRating(2, 5);
        user3.addItemRating(3, 5);
        user3.addItemRating(11, 5);

        item1.addUserRating(1, 5);
        item2.addUserRating(2, 5);
        item3.addUserRating(3, 5);
        item11.addUserRating(3, 5);

        train.addItem(item1);
        train.addItem(item2);
        train.addItem(item3);
        train.addItem(item11);

        train.addUser(user1);
        train.addUser(user2);
        train.addUser(user3);

        return train;
    }
}
