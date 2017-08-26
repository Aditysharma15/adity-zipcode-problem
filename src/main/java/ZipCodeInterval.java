import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ZipCodeInterval {

    public static List<Range> ranges(List<Range> inputList) {

        //Checks if input list is valid or not
        if (!validate(inputList)) {
            return null;
        }
        // Returns List is of size of list is 0 or 1
        if (inputList.size() == 0 || inputList.size() == 1)
            return inputList;

        //Sorts Array on the Basis of First Pin Code in descending Order
        Collections.sort(inputList);

        //Declaring the Result Arraylist which will be returned from the function
        ArrayList<Range> result = new ArrayList<>();


        int start = inputList.get(0).getStart();
        int end = inputList.get(0).getEnd();

        for (int i = 1; i < inputList.size(); i++) {
            Range current = inputList.get(i);
            if (current.getStart() <= end) {
                end = Math.max(current.getEnd(), end);
            } else {
                result.add(new Range(start, end));
                start = current.getStart();
                end = current.getEnd();
            }
        }

        result.add(new Range(start, end));
        return result;
    }

    public static boolean validate(List<Range> list) {
        if (list == null) {
            System.out.println("List is null");
            return false;
        }
        boolean isValidRange = true;

        for (int i = 0; i < list.size(); i++) {
            int start = list.get(i).getStart();
            int end = list.get(i).getEnd();
            if (start <= 9999 || end <= 9999 || start > 99999 || end > 99999) {
                System.out.println("Invalid input(Not of 5 Digits) at index " + i + "Starts from " + start + "Ends at" + end);
                isValidRange = false;
                return isValidRange;

            }
            if (startBiggerThanEnd(start, end)) {
                System.out.println("Invalid input at index" + i + " start is " + start + "grater than end " + end);
                isValidRange = false;
                return isValidRange;
            }
        }
        return isValidRange;
    }

    public static boolean startBiggerThanEnd(int start, int end) {
        if (start > end) {
            return true;
        } else {
            return false;
        }
    }
}
