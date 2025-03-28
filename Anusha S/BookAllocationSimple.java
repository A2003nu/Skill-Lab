public class BookAllocationSimple {

    public static int allocateBooks(int[] books, int students) {
        if (books == null || books.length == 0 || students <= 0 || students > books.length) {
            return -1;
        }

        int totalPages = 0;
        int maxPage = 0;

        for (int pages : books) {
            totalPages += pages;
            maxPage = Math.max(maxPage, pages);
        }

        int low = maxPage;
        int high = totalPages;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(books, students, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private static boolean isPossible(int[] books, int students, int maxPages) {
        int studentCount = 1;
        int currentPageSum = 0;

        for (int pages : books) {
            if (currentPageSum + pages > maxPages) {
                studentCount++;
                currentPageSum = pages;
                if (studentCount > students || pages > maxPages) {
                    return false;
                }
            } else {
                currentPageSum += pages;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] books = {10, 20, 30, 40};
        int students = 2;
        int minMaxPages = allocateBooks(books, students);
        System.out.println("Minimum maximum pages allocated: " + minMaxPages);
}
}