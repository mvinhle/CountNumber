package number;

import java.util.Scanner;

public class CoutNumber {

    //AiPlay = true | false; hệ thống sẽ tự chơi với cách tìm kiếm hay
    //Khả năng tìm kiếm của AI là x lượt, với giá trị: 2^x = MAX_RESULT
    private static final boolean AiPlay = true;
    //Max result tối đa chỉ nên đặt là: 1073741823 ((int) (Integer.MAX_VALUE / 2)) do phạm vi của interger là 2147483647
    private static final int MAX_RESULT = (int) (Integer.MAX_VALUE / 2);
    //result đặt < 0 hệ thống sẽ sinh số ngẫu nhiên trong [0,maxResult)
    private static int result = -1;
    //support = true | false; sẽ hỗ trợ ghi nhớ kết quả đã nhập
    private static boolean support = true;

    private static int point = 0;
    private static String resultString = "Bạn đã thất bại !!";
    private static int nearNumber[] = {0, MAX_RESULT};

    //Dùng để nhập và trả về 1 số kiểu int (kiễm tra lỗi và tự đệ quy)
    private static int inputNumber() {
        //đây là AI
        //System.out.println("[" + (int) ((nearNumber[0] + nearNumber[1]) / 2) + "]");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("(" + (point + 1) + ") Vui lòng nhập số nguyên từ 0 -> " + MAX_RESULT + ", nhập -1 để thoát: ");
            int input = scanner.nextInt();
            if (input < -1) {
                return inputNumber();
            }
            if (support) {
                testNumber(input);
            }
            return input;
        } catch (Exception e) {
            return inputNumber();
        }
    }

    private static int inputNumber(boolean AiPlay) {
        int inputNumber = (int) ((nearNumber[0] + nearNumber[1]) / 2);
        if (AiPlay) {
            System.out.print("(" + (point + 1) + ") Vui lòng nhập số nguyên từ 0 -> " + MAX_RESULT + ", nhập -1 để thoát: ");
            System.out.println(inputNumber);
        } else {
            return inputNumber();
        }
        testNumber(inputNumber);
        return inputNumber;
    }

    private static int testNumber(int input) {
        if (input <= result && input > nearNumber[0]) {
            nearNumber[0] = input;
        }
        if (input >= result && input < nearNumber[1]) {
            nearNumber[1] = input;
        }
        if (support) {
            System.out.println("Support: (" + nearNumber[0] + ", " + nearNumber[1] + ")");
        }
        return input;
    }

    //Sinh ra ngẫu nhiên 1 số từ 0 -> maxResult
    private static void randomResult() {
        if (result < 0) {
            result = (int) (Math.random() * MAX_RESULT);
        }
    }

    public static void main(String[] args) {
        randomResult();
        int input;
        while ((input = inputNumber(AiPlay)) != -1) {
            //Đây là AI
            //inputNumber(input);
            point++;
            if (input < result) {
                System.out.println("Số bạn vừa nhập nhỏ hơn kết quả !\n");
            } else if (input > result) {
                System.out.println("Số bạn vừa nhập lớn hơn kết quả !\n");
            } else {
                resultString = "Chúc mừng, bạn đã chiến thắng trò chơi !!";
                break;
            }
            if (AiPlay){sleepTime(300);}
        }
        System.out.println("\n=================================================");
        System.out.println(resultString + ", Kết quả là: " + result);
        System.out.println("Số lần trả lời là: " + point);
        System.out.println("-------------------------------------------------");
        System.out.println("Đã thoát trò chơi !!!");
    }

    private static void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ie) {}
    }
}
